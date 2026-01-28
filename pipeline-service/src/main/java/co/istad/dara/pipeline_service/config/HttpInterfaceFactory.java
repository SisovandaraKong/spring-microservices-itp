package co.istad.dara.pipeline_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Component
@RequiredArgsConstructor
public class HttpInterfaceFactory {
    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    public <T> T createClient(String baseUrl, Class<T> interfaceClass) {

        // Config to know client credentials
        var oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
        oAuth2AuthorizedClientManager
        );
        oauth2.setDefaultClientRegistrationId("itp-standard");
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .apply(oauth2.oauth2Configuration())
                .build();
        return createClient(webClient, interfaceClass);
    }

    public <T> T createClient(WebClient webClient, Class<T> interfaceClass) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return factory.createClient(interfaceClass);
    }
}
