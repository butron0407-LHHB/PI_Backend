package org.generation.muebleria.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.generation.muebleria.service.JwtService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtRequestFilter  extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        //1. Validar que el header este bien
        //early return o clausa de guarda (busca primero el caso de fallo para finalizar la ejecuacion lo mas pronto posible)
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //2.extraer token
        // Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
        jwt = authHeader.substring(7);

        try{
            //3. extraer user name
            username = jwtService.extractUsername(jwt);
            //4. verificar username
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                //5. cargar el usuario dsde la base de datos
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //6. valida el token
                if(jwtService.isValidToken(jwt, userDetails)){
                    //7. vamos a crear la autenticacion y la guardamos en el contexto
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            filterChain.doFilter(request,response);

        }catch (Exception e){
            //Si el token no es valido, expiro, username no coincide, sign no coincide
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token invalido o expirado");
        }

    }
}
