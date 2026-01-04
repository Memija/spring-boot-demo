package com.memija.demo.configurations;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClientCertificate {

    @Value("${ssl.trust-store}")
    private String trustStore;

    @Value("${ssl.trust-store-password}")
    private String trustStorePassword;

    @Value("${ssl.key-store}")
    private String keyStore;

    @Value("${ssl.key-store-password}")
    private String keyStorePassword;
    
    @PostConstruct
    public void setClientCertificate() {
        System.setProperty("javax.net.ssl.truststore", trustStore);
        System.setProperty("javax.net.ssl.truststorePassword", trustStorePassword);

        System.setProperty("javax.net.ssl.keyStore", keyStore);
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
    }

}
