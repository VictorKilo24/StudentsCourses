package com.students.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(title = "Students API",
                description = "Course selection service",
                version = "1.0.0",
                contact = @Contact(name = "Vladimir Kushhov")))
public class OpenApiConfig {
}
