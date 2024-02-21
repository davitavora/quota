package io.vicarius.quota;

import io.vicarius.quota.configuration.ContainerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestQuotaApplication {

  public static void main(String[] args) {
    SpringApplication.from(QuotaApplication::main)
        .with(ContainerConfiguration.class)
        .run(args);
  }

}

