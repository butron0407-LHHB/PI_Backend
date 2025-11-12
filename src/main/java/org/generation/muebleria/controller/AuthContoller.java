package org.generation.muebleria.controller;

import lombok.AllArgsConstructor;
import org.generation.muebleria.dto.request.AuthRequest;
import org.generation.muebleria.dto.request.UsuarioRequest;
import org.generation.muebleria.dto.response.AuthResponse;
import org.generation.muebleria.dto.response.UsuarioResponse;
import org.generation.muebleria.model.Usuarios;
import org.generation.muebleria.service.JwtService;
import org.generation.muebleria.service.UsuarioService;
import org.generation.muebleria.service.interfaces.IUsuariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/auth")
@AllArgsConstructor
public class AuthContoller {
    //inyectar el servicio
//    private final IUsuariosService usersService;
//
//    @PostMapping(path = "/register")
//    public UsuarioResponse registerUser(@RequestBody UsuarioRequest user){
//        return usersService.addUser(user);
//    }
//
//    @PostMapping(path = "/login")
//    public boolean loginUser(@RequestBody UsuarioRequest user){
//        return usersService.validateUser(user);
//    }

    //inyectar el servicio
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usersServiceImpl;
    private final JwtService jwtService;

    @PostMapping(path = "/register")//http://localhost:8080/api/auth/register
    public UsuarioResponse registerUser(@RequestBody UsuarioRequest user){
        return usersServiceImpl.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            // 1. Autenticar usando el email y password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.correo(),
                            request.password()
                    )
            );

            // 2. Si es exitoso, cargar UserDetails
            ///final UserDetails userDetails = usersServiceImpl.loadUserByUsername(request.correo());
            final Usuarios userDetails = (Usuarios) usersServiceImpl.loadUserByUsername(request.correo());

            // 3. Generar el token JWT
            final String jwt = jwtService.generateToken(userDetails);

            //Extraer el rol para la respuesta del Login (de forma limpia)
            String userRole = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("CLIENTE");

            // 4. Devolver el token
            return ResponseEntity.ok(new AuthResponse(jwt,userDetails.getCorreo(), userRole));

        } catch (BadCredentialsException e) {
            // Si las credenciales son incorrectas
            return ResponseEntity.status(401).body(new AuthResponse("Email o contraseña incorrectos",null,null));
        }
    }
}
