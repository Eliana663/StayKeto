package com.ucam.springboot.stay_keto_spring_boot.filters;

import com.ucam.springboot.stay_keto_spring_boot.services.JwtService;
import com.ucam.springboot.stay_keto_spring_boot.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        // ---------------------------
        // Rutas públicas (no necesitan token)
        // ---------------------------
        if (path.startsWith("/auth/") || path.startsWith("/images/")
                || path.startsWith("/food/") || path.startsWith("/uploads/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ---------------------------
        // Rutas privadas: requieren JWT
        // Ejemplos de rutas privadas:
        // /habit/**, /api/users/**, /api/daily-measurements/**, /api/weight/**, /api/calories/**, /api/daily-food/**
        // ---------------------------

        final String authHeader = request.getHeader("Authorization");

        // Si no hay token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token requerido");
            return;
        }

        final String jwt = authHeader.substring(7); // quitar "Bearer "
        final String email = jwtService.extractUsername(jwt);

        // Validar token y setear autenticación
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido o expirado");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
