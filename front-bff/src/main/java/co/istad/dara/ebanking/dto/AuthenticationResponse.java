package co.istad.dara.ebanking.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        Boolean isAuthenticated
) {
}
