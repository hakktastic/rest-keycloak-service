package nl.hakktastic.rest_keycloack_service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    public static final String CLAIM_REALM_ACCESS = "realm_access";
    public static final String CLAIM_PREFERRED_USERNAME = "preferred_username";
    public static final String ROLES = "roles";
    public static final String JWT_HEADER_KID = "kid";
    public static final String JWT_HEADER_ALG = "alg";
    public static final String JWT_HEADER_TYP = "typ";
    public static final String PREFIX_ROLE_ = "ROLE_";

    private static void logJwtInfo(Jwt jsonWebToken) {
        val kid = jsonWebToken.getHeaders().get(JWT_HEADER_KID);
        val alg = jsonWebToken.getHeaders().get(JWT_HEADER_ALG);
        val typ = jsonWebToken.getHeaders().get(JWT_HEADER_TYP);
        val issuedAt = jsonWebToken.getIssuedAt();
        val expiresAt = jsonWebToken.getExpiresAt();
        log.debug("Converting to GrantedAuthority from JWT with kid='{}', typ='{}', alg='{}' issuedAt='{}', expiresAt='{}'", kid, alg, typ, issuedAt, expiresAt);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<GrantedAuthority> convert(Jwt jsonWebToken) {
        logJwtInfo(jsonWebToken);
        val realmAccessClaim = (Map<String, Object>) jsonWebToken.getClaim(CLAIM_REALM_ACCESS);
        val preferredUsername = jsonWebToken.getClaim(CLAIM_PREFERRED_USERNAME);

        if (realmAccessClaim == null || realmAccessClaim.isEmpty()) {
            log.debug("realmAccessClaim='NULL' for prinicpal='{}'", preferredUsername);
            return Collections.emptyList();
        }
        if (realmAccessClaim.get(ROLES) == null) {
            log.debug("no roles found for realmAccessClaim='{}', principal='{}'", realmAccessClaim, preferredUsername);
            return Collections.emptyList();
        }

        val roles = (Collection<String>) realmAccessClaim.get(ROLES);
        log.debug("found nrOfRoles='{}' for principal='{}'", roles.size(), preferredUsername);

        return roles.stream()
                .map(role -> PREFIX_ROLE_ + role.toUpperCase())
                .peek(grantedAuthority -> log.debug("Converting role='{}' for principal='{}' to grantedAuthority='{}'", grantedAuthority.replace(PREFIX_ROLE_, Strings.EMPTY), preferredUsername, grantedAuthority))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}