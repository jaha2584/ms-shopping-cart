package com.shopping.config;

import feign.Client;
import feign.okhttp.OkHttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import okhttp3.OkHttpClient.Builder;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Client feignClient() {
        try {
            // Configure SSLContext to ignore certificates
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        // No implementation
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        // No implementation
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
            }, new java.security.SecureRandom());

            // Create OkHttpClient with SSL configuration and without proxy
            okhttp3.OkHttpClient okHttpClient = new Builder()
                    .sslSocketFactory(sslContext.getSocketFactory(), new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    })
                    .hostnameVerifier((hostname, session) -> true) // Disable hostname verification
                    .build();

            return new OkHttpClient(okHttpClient);
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to create OkHttpClient", e);
        }
    }
}
