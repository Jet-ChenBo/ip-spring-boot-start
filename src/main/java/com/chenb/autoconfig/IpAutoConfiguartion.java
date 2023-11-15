package com.chenb.autoconfig;

import com.chenb.properties.IpProperties;
import com.chenb.service.IpCountService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
//@EnableConfigurationProperties(IpProperties.class)
@Import({IpProperties.class})
public class IpAutoConfiguartion {

    @Bean
    public IpCountService ipCountService() {
        return new IpCountService();
    }
}
