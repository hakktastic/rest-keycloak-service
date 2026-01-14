package nl.hakktastic.rest_keycloak_service;

import lombok.val;
import org.slf4j.MDC;

import java.time.LocalDateTime;

public record ApiResponse(
        String status,
        String message,
        LocalDateTime timestamp,
        String traceId) {

    public static ApiResponse of(String status, String message) {
        val now = LocalDateTime.now();
        val traceId1 = MDC.get("traceId");

        return new ApiResponse(status, message, now, traceId1);
    }
}