package co.istad.dara.pipeline_service.controller;

import co.istad.dara.pipeline_service.client.JsonPlaceholderClient;
import co.istad.dara.pipeline_service.client.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json-placeholder")
@RequiredArgsConstructor
public class JsonPlaceholderController {
    private final JsonPlaceholderClient jsonPlaceholderClient;

    @GetMapping("/users")
    public List<UserResponse> getAllUsers (){
        return jsonPlaceholderClient.getAllUsers();
    }
}
