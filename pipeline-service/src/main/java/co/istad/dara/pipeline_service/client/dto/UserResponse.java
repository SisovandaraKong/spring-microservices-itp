package co.istad.dara.pipeline_service.client.dto;

public record UserResponse(
        Integer id,
        String name,
        String username,
        String email,
        String phone,
        String website
) {
}
