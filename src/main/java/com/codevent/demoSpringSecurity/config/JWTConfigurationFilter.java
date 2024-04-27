package com.codevent.demoSpringSecurity.config;

import com.codevent.demoSpringSecurity.service.JwtService;
import com.codevent.demoSpringSecurity.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JWTConfigurationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Validate header status
        if (StringUtils.isEmpty(header) || header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get Jwt Token and Validate
        final String token = header.substring(7);
        final String email = jwtService.extractUsername(token);

        if (StringUtils.isEmpty(email)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);
            if (jwtService.isTokenValid(token, userDetails)) {
                SecurityContext emptyContext =
                        SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                        );
                usernamePasswordAuthenticationToken.setDetails
                        (new WebAuthenticationDetailsSource().buildDetails(request));
                emptyContext.setAuthentication(usernamePasswordAuthenticationToken);
                SecurityContextHolder.setContext(emptyContext);
            }
        }


    }
}
