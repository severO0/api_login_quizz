package testedeapi.com.security;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import testedeapi.com.service.AuthorizationService;
import testedeapi.com.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AuthorizationService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if (isPublicRoute(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = recoverToken(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String registroAcademico = jwtService.extractUsername(token);
        if (registroAcademico != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(registroAcademico);

            if (jwtService.isTokenValid(token, userDetails)) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

   
    private boolean isPublicRoute(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        
        if (requestURI.equals("/api/auth/register") && method.equals("POST")) {
            return true;
        }
        return requestURI.startsWith("/api/auth/");
    }
    
    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "").trim();
    }
}
