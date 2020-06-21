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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyCheckController {

    /**
     * Replace with the valid value for the hostname.
     */
    private static final String hostname = "hostname";
    /**
     * Replace with valid value for the URL string.
     */
    private static final String urlString = "urlString";

    @GetMapping("/proxycheck")
    public ResponseEntity<StringBuilder> ProxyCheck() {
        StringBuilder content = new StringBuilder();

        try {
            URLConnection connection = OpenConnection();
            content = ReadStream(content, connection);
        } catch (Exception exception) {
            System.out.println((exception.getMessage()));
            String exceptionStackTrace = GetExceptionStackTrace(exception);
            System.out.println(exceptionStackTrace);
        }

        return new ResponseEntity<StringBuilder>(content, HttpStatus.OK);
    }

    private Proxy InitiateProxy() {
        int port = 8080;
        Type proxyType = Proxy.Type.HTTP;

        SocketAddress socketAddress = new InetSocketAddress(hostname, port);
        Proxy proxy = new Proxy(proxyType, socketAddress);
        return proxy;
    }

    private URLConnection OpenConnection() throws MalformedURLException, IOException {
        Proxy proxy = InitiateProxy();
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection(proxy);
        return connection;
    }

    private StringBuilder ReadStream(StringBuilder content, URLConnection connection) throws IOException {
        String lineOfText;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        while ((lineOfText = bufferedReader.readLine()) != null) {
            content.append(lineOfText + "\n");
        }

        bufferedReader.close();

        return content;
    }

    private String GetExceptionStackTrace(Exception exception) {
        StackTraceElement[] stack = exception.getStackTrace();
        String exceptionStackTrace = "";
        for (StackTraceElement s : stack) {
            exceptionStackTrace = exceptionStackTrace + s.toString() + "\n\t\t";
        }
        return exceptionStackTrace;
    }
}
