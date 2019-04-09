package com.hancai.demo.common.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.hancai.demo.resource.DhcSecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * RequestBody 注解，日期类转换器
 *
 * @author diaohancai
 */
@EnableConfigurationProperties(DhcSecurityProperties.class)
public class DateJacksonConverter extends JsonDeserializer<Date> {

    @Autowired
    private DhcSecurityProperties dhcSecurityProperties;

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Date targetDate = null;
        String originDate = jsonParser.getText();
        if (StringUtils.isNotEmpty(originDate)) {
            try {
                long longDate = Long.valueOf(originDate.trim());
                targetDate = new Date(longDate);
            } catch (NumberFormatException e) {
                try {
                    targetDate = DateUtils.parseDate(originDate,
                            dhcSecurityProperties.getJackson().getDateFormat().toArray(new String[dhcSecurityProperties.getJackson().getDateFormat().size()]));
                } catch (ParseException pe) {
                    throw new IOException(String.format(
                            "'%s' can not convert to type 'java.util.Date',just support timestamp(type of long) and following date format(%s)",
                            originDate,
                            StringUtils.join(dhcSecurityProperties.getJackson().getDateFormat().toArray(new String[dhcSecurityProperties.getJackson().getDateFormat().size()]),
                                    ",")));
                }
            }
        }

        return targetDate;
    }

    @Override
    public Class<?> handledType() {
        return Date.class;
    }
}
