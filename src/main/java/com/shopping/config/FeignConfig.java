/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.config;
import feign.Client;
import feign.okhttp.OkHttpClient;
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
            // Configurar el SSLContext para ignorar certificados
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        // No implementar verificación
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        // No implementar verificación
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
            }, new java.security.SecureRandom());

            // Crear OkHttpClient con la configuración de SSL personalizada
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
                    .hostnameVerifier((hostname, session) -> true) // Deshabilitar la verificación del nombre de host
                    .build();

            return new OkHttpClient(okHttpClient);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create OkHttpClient", e);
        }
    }
}
