package co.istad.dara.pipeline_service.config;

import co.istad.dara.pipeline_service.client.AccountClient;
import co.istad.dara.pipeline_service.client.JsonPlaceholderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpInterfaceConfig {

    @Bean
    public JsonPlaceholderClient JsonPlaceholderService(HttpInterfaceFactory factory) {
        return factory.createClient("https://jsonplaceholder.typicode.com", JsonPlaceholderClient.class);
    }

    @Bean
    public AccountClient accountService(HttpInterfaceFactory factory){
        return factory.createClient("http://localhost:20261", AccountClient.class);
    }


}

