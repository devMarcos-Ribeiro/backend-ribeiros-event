package com.marcosgrs.ribeirosevents.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ribeiros-events")
@Getter
@Setter
public class RibeirosEventsProperties {

    private int tokenExpiration;
    private String jwtExpiration;
    private String jwtSecret;

}
