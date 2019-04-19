package com.hancai.demo.resource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diaohancai
 */
@Component
@ConfigurationProperties(prefix = "dhc-security")
@Data
public class DhcSecurityProperties {

    private Jackson jackson = new Jackson();

    @Data
    public static class Jackson {

        private List<String> dateFormat = new ArrayList<>();

    }
}
