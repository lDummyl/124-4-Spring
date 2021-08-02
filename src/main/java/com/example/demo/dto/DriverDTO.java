package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.*;

/**
 * @author chervinko <br>
 * 29.07.2021
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverDTO {
    public static class Request {
        @Data
        @NoArgsConstructor
        public static class Create {
            @NotNull
            @JsonProperty("first_name")
            String firstName;
            @JsonProperty("middle_name")
            String middleName;
            @NotNull
            @JsonProperty("last_name")
            String lastName;
            @NotNull
            @JsonProperty("mobile_phone")
            String mobilePhone;
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
            @JsonProperty("first_name")
            String firstName;
            @JsonProperty("middle_name")
            String middleName;
            @JsonProperty("last_name")
            String lastName;
            @JsonProperty("mobile_phone")
            String mobilePhone;
            Long[] car;

            public Public(Long id, String firstName, String middleName, String lastName, String mobilePhone) {
                this.id = id;
                this.firstName = firstName;
                this.middleName = middleName;
                this.lastName = lastName;
                this.mobilePhone = mobilePhone;
            }
        }
    }
}
