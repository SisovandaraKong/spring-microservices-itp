package co.istad.dara.pipeline_service.client;

import co.istad.dara.pipeline_service.client.dto.UserResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface JsonPlaceholderClient {

    @GetExchange("/users")
    List<UserResponse> getAllUsers();
}
