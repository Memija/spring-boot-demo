package com.memija.demo.configurations;

public class ClientCertificate {

    /**
     * Replace with the valid value for the Trust Store.
     */
    private static final String trustStore = "pathToTheTrustStore/trustStore";
    /**
     * Replace with valid value for the Trust Store password.
     */
    private static final String trustStorePassword = "Trust store password";
    /**
     * Replace with valid value for the Key Store.
     */
    private static final String keyStore = "pathToTheClientCertificate/clientCertificate";
    /**
     * Replace with valid value for the Key Store password.
     */
    private static final String keyStorePassword = "Client certificate password";
    
    public void SetClientCertificate() {
        System.setProperty("javax.net.ssl.truststore", trustStore);
        System.setProperty("javax.net.ssl.truststorePassword", trustStorePassword);

        System.setProperty("javax.net.ssl.keyStore", keyStore);
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
    }

}
