package com.wiily.pscosmeticos.PsAPI.infra.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private Storage storage;
    private Api api;

    @Getter
    @Setter
    public static class Storage {
        private String imageRoot;
    }

    @Getter
    @Setter
    public static class Api {
        private String externalServiceUrl;
        private String baseIp;
    }

}
