package uz.tuit.appquiz.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.tuit.appquiz.dto.CreateQuestionDTO;
import uz.tuit.appquiz.dto.QuestionDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import static uz.tuit.appquiz.controller.QuestionController.BASE_PATH;

@RequestMapping(BASE_PATH)
public interface QuestionController {

    String BASE_PATH = "/api-question";

    @PostMapping("/{testId}/questions")
    HttpEntity<ApiResult<QuestionDTO>> addQuestionToTest(@PathVariable Long testId,
                                                         @RequestBody CreateQuestionDTO createQuestionDTO);
}
