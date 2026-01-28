package co.istad.dara.ebanking.controller;

import co.istad.dara.ebanking.dto.AuthenticationResponse;
import co.istad.dara.ebanking.dto.ProfileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping("/me")
    public Mono<ProfileResponse> me(@AuthenticationPrincipal OidcUser principal) {
        if (principal == null) {
            log.warn("Principal is null in /me endpoint");
            return Mono.empty();
        }

        return Mono.just(ProfileResponse.builder()
                .uuid(principal.getAttribute("uuid"))
                .username(principal.getPreferredUsername() != null ? principal.getPreferredUsername() : principal.getName())
                .email(principal.getEmail())
                .fullName(principal.getFullName())
                .familyName(principal.getFamilyName())
                .givenName(principal.getGivenName())
                .phoneNumber(principal.getAttribute("phone_number"))
                .profileImage(principal.getAttribute("profile_image"))
                .gender(principal.getAttribute("gender"))
                .authorities(principal.getAttribute("authorities"))
                .build());
    }


    @GetMapping("/is-authenticated")
    public Mono<AuthenticationResponse> isAuthenticated(Authentication authentication) {
        return Mono.just(AuthenticationResponse.builder()
                .isAuthenticated(authentication != null)
                .build());
    }

    @GetMapping("/profile")
    public Mono<ProfileResponse> profile(@AuthenticationPrincipal OidcUser principal) {
        if (principal == null) {
            log.warn("Principal is null in /profile endpoint");
            return Mono.empty();
        }

        return Mono.just(ProfileResponse.builder()
                .uuid(principal.getAttribute("uuid"))
                .username(principal.getPreferredUsername() != null ? principal.getPreferredUsername() : principal.getName())
                .email(principal.getEmail())
                .fullName(principal.getFullName())
                .familyName(principal.getFamilyName())
                .givenName(principal.getGivenName())
                .phoneNumber(principal.getAttribute("phone_number"))
                .profileImage(principal.getAttribute("profile_image"))
                .gender(principal.getAttribute("gender"))
                .authorities(principal.getAttribute("authorities"))
                .build());
    }

}

