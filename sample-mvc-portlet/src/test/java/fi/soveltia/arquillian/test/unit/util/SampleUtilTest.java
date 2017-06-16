package fi.soveltia.arquillian.test.unit.util;

import org.junit.Assert;
import org.junit.Test;

import fi.soveltia.arquillian.util.SampleUtil;

public class SampleUtilTest {

	@Test
	public void testSayHello() {
		Assert.assertEquals("Hello Arquillian", SampleUtil.sayHello());
	}
	
	@Test
	public void testSayHi() {
		Assert.assertEquals("Hi Arquillian", SampleUtil.sayHi());
	}
}
