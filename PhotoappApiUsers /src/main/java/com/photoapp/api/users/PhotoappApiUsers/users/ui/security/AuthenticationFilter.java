package com.photoapp.api.users.PhotoappApiUsers.users.ui.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.dto.UserDto;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.entity.User;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.model.request.LoginRequest;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;
    private final Environment environment;

    public AuthenticationFilter(UserService userService,
                                Environment environment,
                                AuthenticationManager authenticationManager){
        super(authenticationManager);
        this.userService = userService;
        this.environment = environment;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse resp){
        try {
            LoginRequest creds = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginRequest.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain filterChain,
                                            Authentication auth){
        String email = ((User)auth.getPrincipal()).getEmail();
        UserDto userDetails = userService.getUserDetailsByEmail(email);
        String tokenSecret = environment.getProperty("token.secret");
        byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecret.getBytes());
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());

        String token = Jwts.builder().setSubject(userDetails.getUserId())
                .setExpiration(Date.from(Instant.now().plusMillis(Long.parseLong(environment.getProperty("token.expiration_time")))))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
        res.addHeader("token", token);
        res.addHeader("userId", userDetails.getUserId());


    }
}
