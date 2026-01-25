package co.istad.dara.ebanking.controller;

import co.istad.dara.ebanking.dto.AuthenticationResponse;
import co.istad.dara.ebanking.dto.ProfileResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @GetMapping("/me")
    public ProfileResponse me(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println(principal);
        System.out.println(principal.getName());
        return ProfileResponse.builder()
                .username(principal.getName())
                .fullName("ISTAD")
                .build();
    }


    @GetMapping("/me2")
    public Mono<Map<String, Object>> me(Authentication auth) {
        return Mono.just(
                Map.of(
                        "authenticated", auth != null,
                        "name", auth != null ? auth.getName() : null
                )
        );
    }

    @GetMapping("/is-authenticated")
    public AuthenticationResponse isAuthenticated (Authentication authentication){
    return AuthenticationResponse.builder()
            .isAuthenticated(authentication != null)
            .build();
    }

}
