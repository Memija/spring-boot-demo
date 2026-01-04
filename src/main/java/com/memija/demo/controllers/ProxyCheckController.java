package com.memija.demo.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.Proxy.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyCheckController {

    private static final Logger logger = LoggerFactory.getLogger(ProxyCheckController.class);

    @Value("${proxy.hostname}")
    private String hostname;

    @Value("${proxy.url-string}")
    private String urlString;

    @Value("${proxy.port}")
    private int port;

    @GetMapping("/proxycheck")
    public ResponseEntity<String> proxyCheck() {
        StringBuilder content = new StringBuilder();

        try {
            URLConnection connection = openConnection();
            content = readStream(content, connection);
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(content.toString(), HttpStatus.OK);
    }

    private Proxy initiateProxy() {
        Type proxyType = Proxy.Type.HTTP;
        SocketAddress socketAddress = new InetSocketAddress(hostname, port);
        return new Proxy(proxyType, socketAddress);
    }

    private URLConnection openConnection() throws MalformedURLException, IOException {
        Proxy proxy = initiateProxy();
        URL url = new URL(urlString);
        return url.openConnection(proxy);
    }

    private StringBuilder readStream(StringBuilder content, URLConnection connection) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String lineOfText;
            while ((lineOfText = bufferedReader.readLine()) != null) {
                content.append(lineOfText).append("\n");
            }
        }
        return content;
    }
}
