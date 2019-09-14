package com.javahack.smehelper.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Simple test using the WicketTester
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations={"classpath:applicationContext.xml"} )
public class TestOrderPage {
    private static final Logger LOG = LoggerFactory.getLogger(TestOrderPage.class);

	@Test
	public void homepageRendersSuccessfully()
	{
	}
}
