package com.auth_app;

import com.auth_app.Auth.Config.AppConstants;
import com.auth_app.Auth.entities.Role;
import com.auth_app.Auth.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class AuthAppApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthAppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //we will create some defaut roles

        roleRepository.findByName("ROLE_" + AppConstants.ADMIN_ROLE).ifPresentOrElse(role -> {
            System.out.println("Admin role already exists: " + role.getName());
        }, () -> {
            Role role = new Role();
            role.setName("ROLE_" + AppConstants.ADMIN_ROLE);
            role.setId(UUID.randomUUID());
            roleRepository.save(role);
        });

        roleRepository.findByName("ROLE_" + AppConstants.GUEST_ROLE).ifPresentOrElse(role -> {
            System.out.println("Guset role already exists: " + role.getName());

        }, () -> {
            Role role = new Role();
            role.setName("ROLE_" + AppConstants.GUEST_ROLE);
            role.setId(UUID.randomUUID());
            roleRepository.save(role);
        });

    }
}
