package com.memija.demo.configurations;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ProxyConnectionWithAuthorization {

    /**
     * Replace with the valid value for the Proxy Host.
     */
    private static final String proxyHost = "Default value";
    /**
     * Replace with valid value for the Proxy Port.
     */
    private static final String proxyPort = "Default value";
    /**
     * Replace with valid value for the Username.
     */
    private static final String username = "Default value";
    /**
     * Replace with valid value for the Password.
     */
    private static final String password = "Default value";

    public void SetProxyConnectionWithAuthorization() {
        System.setProperty("https.ProxyHost", proxyHost);
        System.setProperty("https.ProxyPort", proxyPort);

        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        });
    }

}
