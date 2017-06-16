package fi.soveltia.arquillian.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import fi.soveltia.arquillian.service.SampleService;

@Component(
		immediate = true, 
		property = { 
				"com.liferay.portlet.display-category=category.sample",
				"com.liferay.portlet.instanceable=false", 
				"javax.portlet.display-name=Sample MVC Portlet",
				"javax.portlet.init-param.template-path=/", 
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + SampleMVCPortlet.PORTLET_NAME,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		}, 
		service = Portlet.class
) 
public class SampleMVCPortlet extends MVCPortlet {
	
	public static final String PORTLET_NAME = "sample_mvc_portlet";

	public void add(ActionRequest actionRequest, ActionResponse actionResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		int firstParameter = ParamUtil.getInteger(actionRequest, "firstParameter");
		int secondParameter = ParamUtil.getInteger(actionRequest, "secondParameter");

		int result = _sampleService.add(firstParameter, secondParameter);

		PortletURL portletURL = PortletURLFactoryUtil.create(actionRequest, PORTLET_NAME,
				themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		portletURL.setParameter("firstParameter", String.valueOf(firstParameter));
		portletURL.setParameter("secondParameter", String.valueOf(secondParameter));
		portletURL.setParameter("result", String.valueOf(result));

		actionRequest.setAttribute(WebKeys.REDIRECT, portletURL.toString());
	}

	@Reference(unbind = "-")
	public void setSimpleService(SampleService sampleService) {
		_sampleService = sampleService;
	}

	private SampleService _sampleService;

}