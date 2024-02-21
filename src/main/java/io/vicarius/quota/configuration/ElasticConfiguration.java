package io.vicarius.quota.configuration;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.util.List;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.message.BasicHeader;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfiguration {

    private static final String X_ELASTIC_PRODUCT_HEADER = "X-Elastic-Product";
    private static final String PRODUCT = "Elasticsearch";

    @Bean
    public RestClientBuilderCustomizer restClientBuilderCustomizer() {
        return builder -> builder
            .setHttpClientConfigCallback(httpClientBuilder ->
                httpClientBuilder
                    .setDefaultHeaders(List.of(new BasicHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)))
                    .addInterceptorLast(
                        (HttpResponseInterceptor)
                            (response, context) ->
                                response.addHeader(X_ELASTIC_PRODUCT_HEADER, PRODUCT)
                ));
    }

}
