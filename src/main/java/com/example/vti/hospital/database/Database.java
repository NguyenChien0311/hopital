package com.example.vti.hospital.database;

import com.example.vti.hospital.models.ERole;
import com.example.vti.hospital.models.Role;
import com.example.vti.hospital.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(RoleRepository repository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Role role1 = new Role();
                role1.setName(ERole.ROLE_ADMIN);
                role1.setId(1);
                Role role2 = new Role();
                role2.setId(2);
                role2.setName(ERole.ROLE_MODERATOR);
                Role role3 = new Role();
                role3.setName(ERole.ROLE_USER);

                role3.setId(3);

                repository.save(role1);
                repository.save(role2);
                repository.save(role3);
            }
        };
    }
}