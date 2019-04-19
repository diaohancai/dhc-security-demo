package com.hancai.demo.password;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author diaohancai
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncodeTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String password = "222";

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testPasswordEncode() {
        String encodePassword = bCryptPasswordEncoder.encode(password);
        logger.info("原始密码:" + password);
        logger.info("BCrypt加密密码:" + encodePassword);

        Assert.assertNotNull(encodePassword);
    }

}
