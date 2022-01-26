package com.kryhowsky.shop.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class FilePropertiesConfig {

    @Value("${file.product}")
    private String product;

    @Value("${amazonProperties.endpointUrl}")
    private String s3Endpoint;

    @Value("${amazonProperties.bucketName}")
    private String s3BucketName;

}
