package co.istad.dara.ebanking.dto;

import lombok.Builder;

@Builder
public record ProfileResponse(
        String username,
        String fullName
) {
}
