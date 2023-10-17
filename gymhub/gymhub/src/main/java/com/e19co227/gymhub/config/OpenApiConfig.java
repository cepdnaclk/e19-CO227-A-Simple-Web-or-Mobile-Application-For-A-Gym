package com.e19co227.gymhub.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

// OpenAPI definition for GymHub API documentation.
@OpenAPIDefinition(
        info = @Info(

                // Information about the API.
                contact = @Contact(
                        name = "Hirushi",
                        email = "hirushig1@gmail.com",
                        url = "https://www.gymhub.com"
                ),
                description = "OpenApi documentation for GymHub",
                title = "OpenApi specification - GymHub",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),

        // Servers where the API is hosted.
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "AWS Server",
                        url = "https://www.gymhub.com"
                )
        },

        // Security requirement specifying the need for bearer token authentication.
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)

// Security scheme definition for JWT authentication.
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}