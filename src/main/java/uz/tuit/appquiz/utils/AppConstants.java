package uz.tuit.appquiz.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import uz.tuit.appquiz.controller.*;

public interface AppConstants {

    String AUTH_HEADER = "Authorization";
    String BASIC_AUTH = "Basic ";
    String BEARER_AUTH = "Bearer ";

    ObjectMapper objectMapper = new ObjectMapper();

    String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    String REACT_URL = "http://localhost:5173";


    String[] OPEN_PAGES = {
            AuthController.BASE_PATH + "/**",
            "/api-auth/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**"
    };

    String[] SUPER_ADMIN_PAGES = {"/**"};
    String[] ADMIN_PAGES = {
            SubjectController.BASE_PATH + "/**",
            TestController.BASE_PATH + "/**",
            QuestionController.BASE_PATH + "/**",
            HistoryController.BASE_PATH + "/**",
            UserController.BASE_PATH + "/**"
    };
    String[] USER_PAGES = {};
}
