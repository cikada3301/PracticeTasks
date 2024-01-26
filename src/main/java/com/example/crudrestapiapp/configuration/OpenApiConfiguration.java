package com.example.crudrestapiapp.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Vladislav",
                        email = "vladpalagin2013@yandex.by"
                ),
                description = "OpenApi documentation for ToDo API"
        ),
        servers = {
                @Server(description = "Local Env",
                        url = "https://localhost:8080"
                )
        }
)
public class OpenApiConfiguration {
}
