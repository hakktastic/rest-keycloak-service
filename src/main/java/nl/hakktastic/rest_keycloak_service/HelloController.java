package nl.hakktastic.rest_keycloak_service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @GetMapping("/public")
    public ResponseEntity<ApiResponse> publicEndpoint(HttpServletRequest httpServletRequest) {
        log.debug("Executing public endpoint uri='{}'", httpServletRequest.getRequestURI());
        val message = "This endpoint is public 🚀";
        val apiResponse = ApiResponse.of(HttpStatus.OK.toString(), message);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/private")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> privateEndpoint(HttpServletRequest httpServletRequest) {
        log.debug("🔒 Executing private user endpoint uri='{}'", httpServletRequest.getRequestURI());
        val message = "This endpoint is protected for USERS 🔒";
        val apiResponse = ApiResponse.of(HttpStatus.OK.toString(), message);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> adminEndpoint(HttpServletRequest httpServletRequest) {
        log.debug("🔒 Executing private admin endpoint uri='{}'", httpServletRequest.getRequestURI());
        val message = "This endpoint is protected for ADMINS 🔒";
        val apiResponse = ApiResponse.of(HttpStatus.OK.toString(), message);

        return ResponseEntity.ok(apiResponse);
    }
}