package fi.soveltia.arquillian.test.integration.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;

import com.liferay.arquillian.containter.remote.enricher.Inject;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.shrinkwrap.osgi.api.BndProjectBuilder;

import fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry;
import fi.soveltia.arquillian.servicebuilder.service.GuestbookEntryLocalService;

/**
 * A simple service builder portlet Arquillian test
 * 
 * Note that standard @BeforeClass & @AfterClass cannot be used directly in
 * Arquillian tests as they are executed outside of Arquillian container.
 * 
 * @author peerkar
 *
 */
@RunWith(Arquillian.class)
public class SampleServiceBuilderTest {

	/**
	 * A sample of creating of test company for the test method.
	 */
	@Rule
	public final ExternalResource resource = new ExternalResource() {

		@Override
		protected void before() throws PortalException  {

			if (_testCompany == null) {
				_testCompany = CompanyLocalServiceUtil.addCompany("example.com", "example.com", "example.com", false, 0,
						true);
			}
		};

		@Override
		protected void after() {

			try {
				CompanyLocalServiceUtil.deleteCompany(_testCompany.getCompanyId());
			} catch (PortalException e) {
				e.printStackTrace();
			}
		};
	};

	@Deployment
	public static JavaArchive createTest() throws Exception {

		BndProjectBuilder bndProjectBuilder = ShrinkWrap.create(BndProjectBuilder.class);
		bndProjectBuilder.setBndFile(new File("test.bnd"));
		bndProjectBuilder.generateManifest(true);

		return bndProjectBuilder.as(JavaArchive.class);
	}

	@Test
	public void testAdd() throws IOException, PortalException {

		int entriesCount = _guestbookEntryLocalService.getGuestbookEntriesCount();

		GuestbookEntry guestbookEntry = createGuestBookEntry("testtext", "testauthor");

		_guestbookEntryLocalService.addGuestbookEntry(guestbookEntry);

		Assert.assertEquals(++entriesCount, _guestbookEntryLocalService.getGuestbookEntriesCount());
	}

	private GuestbookEntry createGuestBookEntry(String text, String author) throws PortalException {

		GuestbookEntry guestbookEntry = _guestbookEntryLocalService
				.createGuestbookEntry(CounterLocalServiceUtil.increment());
		guestbookEntry.setCompanyId(_testCompany.getCompanyId());
		guestbookEntry.setGroupId(getGuestGroupId());
		guestbookEntry.setUserId(getDefaultUserId());
		guestbookEntry.setCreateDate(new Date());
		guestbookEntry.setAuthor(author);
		guestbookEntry.setText(text);

		return guestbookEntry;
	}

	private long getDefaultUserId() throws PortalException {
		return UserLocalServiceUtil.getDefaultUserId(_testCompany.getCompanyId());
	}

	private long getGuestGroupId() throws PortalException {
		String groupName = GroupConstants.GUEST;
		return GroupLocalServiceUtil.getGroup(_testCompany.getCompanyId(), groupName).getGroupId();
	}

	@Inject
	private GuestbookEntryLocalService _guestbookEntryLocalService;

	private Company _testCompany;
}
