package com.example.server;

import com.example.server.enumeration.Status;
import com.example.server.model.Server;
import com.example.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ServerRepo serverRepo) {
        return args -> {
            serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16GB", "Personal PC",
                    "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));

            serverRepo.save(new Server(null, "192.168.1.58", "Fedora Linux", "16GB", "Telesur",
                    "http://localhost:8080/server/image/server2.png", Status.SERVER_UP));

            serverRepo.save(new Server(null, "192.168.1.21", "MS 2008", "64GB", "Digicel",
                    "http://localhost:8080/server/image/server3.png", Status.SERVER_DOWN));

            serverRepo.save(new Server(null, "192.168.1.14", "Red Hat Enterprise Linux", "64GB", "Qualogy",
                    "http://localhost:8080/server/image/server4.png", Status.SERVER_DOWN));

            serverRepo.save(new Server(null, "10.1.11.63", "Local", "16GB", "Think",
                    "http://localhost:8080/server/image/server3.png", Status.SERVER_DOWN));
        };
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
//                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
//                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
//        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
//                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }

}
