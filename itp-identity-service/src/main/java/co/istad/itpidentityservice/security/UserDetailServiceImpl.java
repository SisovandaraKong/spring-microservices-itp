package co.istad.itpidentityservice.security;

import co.istad.itpidentityservice.domain.Role;
import co.istad.itpidentityservice.domain.User;
import co.istad.itpidentityservice.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    // Override default
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userLogged = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Build UserDetails object
        String[] roles = userLogged.getRoles().stream()
                .map(Role::getName)
                .toArray(String[]::new);

//        Set<GrantedAuthority> authorities = userLogged.getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//                .collect(Collectors.toSet());

        // Use forEach and get permission as well
        List<GrantedAuthority> authorities = new ArrayList<>();
        userLogged.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            role.getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            });
        });


        UserDetails userSecurity = org.springframework.security.core.userdetails.User.builder()
                .username(userLogged.getUsername())
                .password(userLogged.getPassword())
                //.roles(roles)
                .authorities(authorities)
                .build();
        log.info("UserDetailsServiceImpl loadUserByUsername = {}", userSecurity.getAuthorities());
        log.info("UserDetailsServiceImpl loadUserByUsername = {}", userSecurity.getUsername());
        return userSecurity;
    }
}
