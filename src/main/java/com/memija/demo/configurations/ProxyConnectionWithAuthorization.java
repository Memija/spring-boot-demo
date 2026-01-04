package com.memija.demo.configurations;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProxyConnectionWithAuthorization {

    @Value("${proxy.hostname}")
    private String proxyHost;

    @Value("${proxy.port}")
    private String proxyPort;

    @Value("${proxy.username}")
    private String username;

    @Value("${proxy.password}")
    private String password;

    @PostConstruct
    public void setProxyConnectionWithAuthorization() {
        System.setProperty("https.ProxyHost", proxyHost);
        System.setProperty("https.ProxyPort", proxyPort);

        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        });
    }

}
