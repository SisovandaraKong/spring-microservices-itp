package co.istad.itpidentityservice.features.role;

import co.istad.itpidentityservice.domain.Role;
import co.istad.itpidentityservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
