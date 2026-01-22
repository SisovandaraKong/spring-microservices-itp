package co.istad.dara.account_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/public")
public class UnsecuredController {

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>(
                Map.of("Name","Dara"), HttpStatus.OK
        );
    }
}
