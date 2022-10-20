package com.yair.couponproject.security;

import com.yair.couponproject.entities.Company;
import com.yair.couponproject.entities.Customer;
import com.yair.couponproject.entities.User;
import com.yair.couponproject.enums.Role;
import com.yair.couponproject.errors.exceptions.AppException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtil {

    // ------------- util for token, extract, generate...----------

    private static final int ONE_HOUR_IN_MILLIS = 1000 * 60 * 60;
    private static final int TWO_HOURS_IN_MILLIS = 2000 * 60 * 60;
    public static final String SECRET_KEY = "secret";

    public static String extractEmail(final String token) {
            return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(final String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(final String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static String generateToken(final String email) {
        final Map<String, Object> claims = new HashMap<>();
        User user = new User(email, Role.Admin);
        claims.put("user", user);
        return createToken(claims, email);
    }

    public static String generateToken(final Customer customer) {
        final Map<String, Object> claims = new HashMap<>();
        customer.setPassword(null);
        customer.setRole(Role.Customer);
        customer.setCoupons(null);
        claims.put("user", customer);
        return createToken(claims, customer.getEmail());
    }

    public static String generateToken(final Company company) {
        final Map<String, Object> claims = new HashMap<>();
        company.setPassword(null);
        company.setRole(Role.Company);
        claims.put("user", company);
        return createToken(claims, company.getEmail());
    }

    private static String createToken(final Map<String, Object> claims, final String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TWO_HOURS_IN_MILLIS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static boolean validateToken(final String token, final UserDetails user) {
        final String email = extractEmail(token);
        return email.equals(user.getUsername()) && !isTokenExpired(token);
    }

    private static boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }
}