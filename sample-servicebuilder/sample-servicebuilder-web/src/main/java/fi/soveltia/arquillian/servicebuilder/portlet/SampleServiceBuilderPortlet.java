package fi.soveltia.arquillian.servicebuilder.portlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry;
import fi.soveltia.arquillian.servicebuilder.service.GuestbookEntryLocalService;
import fi.soveltia.arquillian.util.EntryValidator;


@Component(immediate = true, property = { 
		
		"com.liferay.portlet.css-class-wrapper=sample-servicebuilder-portlet",
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true", 
		"javax.portlet.display-name=Sample Servicebuilder Portlet",
		"javax.portlet.init-param.template-path=/", 
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=sample_servicebuilder_portlet", 
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" 
		}, 
		service = Portlet.class
)
public class SampleServiceBuilderPortlet extends MVCPortlet {

	public void addEntry(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String author = ParamUtil.getString(actionRequest, "author");
		String text = ParamUtil.getString(actionRequest, "text");

		// Validate input
		
		EntryValidator.validate(actionRequest, author, text);
		
		if (SessionErrors.size(actionRequest) > 0) {
			return;
		}
		
		try {

			GuestbookEntry guestbookEntry = _guestbookEntryLocalService
					.createGuestbookEntry(CounterLocalServiceUtil.increment());
			guestbookEntry.setCompanyId(themeDisplay.getCompanyId());
			guestbookEntry.setGroupId(themeDisplay.getScopeGroupId());
			guestbookEntry.setUserId(themeDisplay.getUserId());
			guestbookEntry.setCreateDate(new Date());
			guestbookEntry.setAuthor(author);
			guestbookEntry.setText(text);

			_guestbookEntryLocalService.addGuestbookEntry(guestbookEntry);

			PortletURL portletURL = PortletURLFactoryUtil.create(actionRequest, "sample_servicebuilder_portlet",
					themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

			actionResponse.sendRedirect(portletURL.toString());
		} catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

		List<GuestbookEntry> entries = _guestbookEntryLocalService.getGuestbookEntries(0,
				_guestbookEntryLocalService.getGuestbookEntriesCount());
		request.setAttribute("entries", entries);

		super.render(request, response);
	}

	@Reference(unbind = "-")
	public void setGuestbookEntryLocalService(GuestbookEntryLocalService guestbookEntryLocalService) {
		_guestbookEntryLocalService = guestbookEntryLocalService;
	}

	private GuestbookEntryLocalService _guestbookEntryLocalService;
}