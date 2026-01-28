package co.istad.dara.ebanking.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ProfileResponse(
        String uuid,
        String username,
        String email,
        String fullName,
        String familyName,
        String givenName,
        String phoneNumber,
        String profileImage,
        String gender,
        List<String> authorities
) {
}

