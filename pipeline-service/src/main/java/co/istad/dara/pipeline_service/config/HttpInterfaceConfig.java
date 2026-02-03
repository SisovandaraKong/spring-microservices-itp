package co.istad.dara.pipeline_service.config;

import co.istad.dara.pipeline_service.client.AccountClient;
import co.istad.dara.pipeline_service.client.JsonPlaceholderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpInterfaceConfig {

    // For request by hostname
    @Bean
    public JsonPlaceholderClient JsonPlaceholderService(HttpInterfaceFactory factory) {
        return factory.createNormalClient("https://jsonplaceholder.typicode.com", JsonPlaceholderClient.class);
    }

    // For request by loadbalance
    @Bean
    public AccountClient accountClientService(HttpInterfaceFactory factory){
        return factory.createLoadBalanedClient("http://account", AccountClient.class);
    }


}

