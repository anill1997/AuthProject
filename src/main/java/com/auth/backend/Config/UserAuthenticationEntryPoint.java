package com.auth.backend.Config;

import com.auth.backend.dto.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

   public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

   @Override
   public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
      OBJECT_MAPPER.writeValue(response.getOutputStream(), new ErrorDTO("Unauthorized path"));
   }
}
