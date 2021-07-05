package com.chat.authentication;

import com.chat.security.SecurityConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
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

// En esta clase se comprueban que las peticiones tienen acceso
// a los recursos. Cada peticion comprueba el token.
public class AuthorizationFilter extends BasicAuthenticationFilter {
    public AuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(SecurityConstant.HEADER_AUTHORIZACION_KEY);
        if (header == null || !header.startsWith(SecurityConstant.TOKEN_BEARER_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Token Invalid
        if(authentication == null) {
            //res.getWriter().append("TOKEN INVALID");
        }

        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.HEADER_AUTHORIZACION_KEY);
        if (token == null) {
            return null;
        }

        try {
            // Se procesa el token y se recupera el usuario.
            String user = Jwts.parser()
                    .setSigningKey(SecurityConstant.SECRET_KEY)
                    .parseClaimsJws(token.replace(SecurityConstant.TOKEN_BEARER_PREFIX, ""))
                    .getBody()
                    .getSubject();
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        } catch (MalformedJwtException e) {
            return null;
        }
    }
}
