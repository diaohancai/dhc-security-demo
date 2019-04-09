package com.hancai.demo.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diaohancai
 */
@Component
@ConfigurationProperties(prefix = "dhc-security")
public class DhcSecurityProperties {

    private Jackson jackson = new Jackson();

    public Jackson getJackson() {
        return jackson;
    }

    public void setJackson(Jackson jackson) {
        this.jackson = jackson;
    }

    public static class Jackson {

        private List<String> dateFormat = new ArrayList<String>();

        public List<String> getDateFormat() {
            return dateFormat;
        }

        public void setDateFormat(List<String> dateFormat) {
            this.dateFormat = dateFormat;
        }
    }
}
