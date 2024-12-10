package uz.tuit.appquiz.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.exceptions.ErrorData;
import uz.tuit.appquiz.utils.AppConstants;

import java.io.IOException;
import java.util.List;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ApiResult<List<ErrorData>> apiResult = ApiResult.errorResponse(authException.getMessage(),
                HttpServletResponse.SC_UNAUTHORIZED);
        String string = AppConstants.objectMapper.writeValueAsString(apiResult);
        response.getWriter().write(string);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
