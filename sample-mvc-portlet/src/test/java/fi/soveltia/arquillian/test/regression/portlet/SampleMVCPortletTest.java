package fi.soveltia.arquillian.test.regression.portlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.liferay.arquillian.portal.bundle.annotation.PortalURL;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.shrinkwrap.osgi.api.BndProjectBuilder;

import fi.soveltia.arquillian.portlet.SampleMVCPortlet;

@RunAsClient
@RunWith(Arquillian.class)
public class SampleMVCPortletTest {

	@Deployment
	public static JavaArchive create() {

		BndProjectBuilder bndProjectBuilder = ShrinkWrap.create(BndProjectBuilder.class);
		bndProjectBuilder.setBndFile(new File("test.bnd"));
		bndProjectBuilder.generateManifest(true);

		return bndProjectBuilder.as(JavaArchive.class);
	}

	@Test
	public void testAdd() throws IOException, PortalException {

		browser.get(_portlerURL.toExternalForm());

		firstParameter.clear();
		firstParameter.sendKeys("2");

		secondParameter.clear();
		secondParameter.sendKeys("3");

		add.click();

		Assert.assertEquals("5", result.getText());
	}

	@Test
	public void testInstallPortlet() throws IOException, PortalException {

		browser.get(_portlerURL.toExternalForm());

		String bodyText = browser.findElement(By.tagName("body")).getText();

		Assert.assertTrue("The portlet is not well deployed", bodyText.contains("Hello Arquillian"));
	}

	@PortalURL(SampleMVCPortlet.PORTLET_NAME)
	private URL _portlerURL;

	@FindBy(css = "button[type=submit]")
	private WebElement add;

	@Drone
	private WebDriver browser;

	@FindBy(css = "input[id$='firstParameter']")
	private WebElement firstParameter;

	@FindBy(css = "span[class='result']")
	private WebElement result;

	@FindBy(css = "input[id$='secondParameter']")
	private WebElement secondParameter;

}
