package uz.tuit.appquiz.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.exceptions.ErrorData;
import uz.tuit.appquiz.utils.AppConstants;

import java.io.IOException;
import java.util.List;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        ApiResult<List<ErrorData>> apiResult = ApiResult.errorResponse(accessDeniedException.getMessage(),
                HttpServletResponse.SC_FORBIDDEN);
        String string = AppConstants.objectMapper.writeValueAsString(apiResult);
        response.getWriter().write(string);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
