package org.generation.muebleria.config;

import lombok.RequiredArgsConstructor;
import org.generation.muebleria.repository.UsuariosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsuariosRepository usersRepository;

    //Bean es un objeto que va aser gestionado por Spring

    @Bean
    UserDetailsService userDetailsService() {
        return username -> usersRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }

    //creacion del metodo para encriptar
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //con este metodo, ya no es necesario el otro metodo de validar usuario en el servicio
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(
//                auth -> auth
//                        .requestMatchers("/api/**").permitAll()
//                        .anyRequest().permitAll()
//        ).csrf(csrf -> csrf.disable());
//        return httpSecurity.build();
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite cualquier origen, método y cabecera
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Importante: permite el envío de credenciales (que incluye el token Authorization)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    // 3. INYECTAMOS EL FILTRO AQUÍ, COMO PARÁMETRO DEL MÉTODO
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtAuthFilter) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html","/css/**", "/js/**", "/images/**").permitAll()
                        //Rutas Privadas (solo administradores)
                        .requestMatchers(HttpMethod.POST,"/api/categorias/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT,"/api/categorias/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/categorias/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/api/productos/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT,"/api/productos/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/productos/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/api/imagenes/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT,"/api/imagenes/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/imagenes/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET,"/api/pedidos/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET,"/api/proveedores/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/api/proveedores/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT,"/api/proveedores/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/proveedores/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET,"/api/resenas/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/resenas/admin/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET,"/api/roles/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/api/roles/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT,"/api/roles/**").hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/roles/**").hasAnyAuthority("ADMINISTRADOR")
                        //Rutas Publicas
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/productos/**").permitAll() // Permitimos ver productos
                        .requestMatchers(HttpMethod.GET,"/api/categorias/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/imagenes/**").permitAll()
                        .requestMatchers("/api/directions/**").permitAll()
                        .requestMatchers("/api/facturas/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/pedidos/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/pedidos/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/pedidos/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/proveedores/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/resenas/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/resenas/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/resenas/**").permitAll()
                        .requestMatchers("/api/users/**").authenticated()
                        .anyRequest().permitAll()// El resto, protegido
                )
                .authenticationProvider(authenticationProvider())
                // 4. Usamos el filtro que Spring inyectó al método
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //para evitar cors
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
