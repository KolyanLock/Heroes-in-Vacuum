package com.kolyanlock.heroesinvacuum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HeroesInVacuumApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroesInVacuumApplication.class, args);
    }

}
