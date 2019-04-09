package com.hancai.demo.user.rest;

import com.hancai.demo.resource.DhcSecurityProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author diaohancai
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties(DhcSecurityProperties.class)
public class DhcSecurityPropertiesTest {

    @Autowired
    private DhcSecurityProperties dhcSecurityProperties;

    @Test
    public void testProperties() {
        Assert.assertNotNull(dhcSecurityProperties.getJackson().getDateFormat());
        for(String dataFormat : dhcSecurityProperties.getJackson().getDateFormat()) {
            System.out.println(dataFormat);
        }
    }

}
