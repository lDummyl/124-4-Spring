package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author chervinko <br>
 * 29.07.2021
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDTO {
    public static class Request {
        @Data
        @NoArgsConstructor
        public static class Create {
            @NotNull
            String make;
            @NotNull
            String model;
            @NotNull
            @JsonProperty("registration_number")
            String registrationNumber;
            @JsonProperty("driver_id")
            Long driverId;
        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        @NoArgsConstructor
        public static class Update extends Create {
            @NonNull
            Long id;
        }
    }

    public static class Response {
        @Data
        @NoArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Public {
            Long id;
            String make;
            String model;
            @JsonProperty("registration_number")
            String registrationNumber;
            DriverDTO.Response.Public driver;
        }
    }
}