package com.chat.auth;

import com.chat.utils.JwtUtil;
import com.chat.config.SecurityConstant;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * En esta clase se comprueba que las peticiones de acceso a los recursos estan autorizadas.
 * Para ello, en cada peticion se comprueba el token enviado por el usuario y se valida.
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Buscamos el token en la cabecera "Authorization" (HEADER_AUTHORIZATION_KEY)
        String tokenInHeader = request.getHeader(SecurityConstant.HEADER_AUTHORIZACION_KEY);

        // 1. La peticion no contiene la cabecera "AUTHORIZATION" por lo que no tiene token firmado
        // 2. Contiene la cabecera "AUTHORIZATION" pero el formato no es correcto
        if (tokenInHeader == null || !tokenInHeader.startsWith(SecurityConstant.TOKEN_BEARER_PREFIX)) {
            // Esta peticion no se autorizara por no cumplir el formato asi que pasamos la peticion
            // tal como esta por el siguiente filtro al no estar autorizada automaticamente arrojara error 403
            logger.info("Cabecera no encontrada o tiene formato incorrecto.");
            chain.doFilter(request, response);
            return;
        }

        // El header contiene el token con el formato correcto ... comenzamos la validacion del token
        try {
            UsernamePasswordAuthenticationToken authentication;

            // Obtenemos el nombre de usuario del token cifrado
            String user = JwtUtil.extractUsernameFromToken(tokenInHeader);

            // Reconstruir el objeto "Authentication" a partir del token
            authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

            // Autorizamos al usuario
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (MalformedJwtException e) {
            // El formato del token es invalido
            logger.info("Token mal formado");
        } catch (SignatureException e) {
            // La firma del token es invalidad (token modificado)
            logger.info("Firma de token incorrecta");
        } catch (ExpiredJwtException e) {
            // El token expiro ya que el tiempo de vida ha pasado.
            logger.info("Token expirado");
        }

        // Si no se produjo ninguna excepcion nuestra peticion quedara autorizada.
        // Pasamos la peticion al siguiente filtro
        chain.doFilter(request, response);
    }
}
