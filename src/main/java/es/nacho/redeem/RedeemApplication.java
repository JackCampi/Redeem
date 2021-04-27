package es.nacho.redeem;

import es.nacho.redeem.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class RedeemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedeemApplication.class, args);
    }

}
