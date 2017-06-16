package fi.soveltia.arquillian.test.integration.portlet;

import java.io.File;
import java.io.IOException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.liferay.arquillian.containter.remote.enricher.Inject;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.shrinkwrap.osgi.api.BndProjectBuilder;

import fi.soveltia.arquillian.service.SampleService;

@RunWith(Arquillian.class)
public class SampleMVCPortletTest {

	@Deployment
	public static JavaArchive create() throws Exception {

		BndProjectBuilder bndProjectBuilder = ShrinkWrap.create(BndProjectBuilder.class);
		bndProjectBuilder.setBndFile(new File("test.bnd"));
		bndProjectBuilder.generateManifest(true);

		return bndProjectBuilder.as(JavaArchive.class);
	}

	@Test
	public void testAdd() throws IOException, PortalException {
		int result = _sampleService.add(1, 3);
		Assert.assertEquals(4, result);
	}

	@Inject
	private SampleService _sampleService;
}