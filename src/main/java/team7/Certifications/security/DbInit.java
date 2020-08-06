package team7.Certifications.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team7.Certifications.entity.User;
import team7.Certifications.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        Optional<User> user=this.userRepository.findByName("admin");
        if(user.isEmpty())
        {
            User admin = new User();

            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("admin");
            admin.setPermissions("ADMIN_ACCESS");
            admin.setActive(1);

            this.userRepository.save(admin);
        }




    }
}
