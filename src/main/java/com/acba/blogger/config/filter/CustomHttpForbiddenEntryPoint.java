package com.acba.blogger.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomForbiddenEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        Map<String,String> exceptionResponse = new HashMap<>();
        exceptionResponse.put("message",exception.getMessage());

        try(OutputStream outputStream = response.getOutputStream()){
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(outputStream,exceptionResponse);
            outputStream.flush();
        }
    }
}
