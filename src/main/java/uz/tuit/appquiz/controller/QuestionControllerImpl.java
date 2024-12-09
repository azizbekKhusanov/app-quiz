package uz.tuit.appquiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.tuit.appquiz.dto.CreateQuestionDTO;
import uz.tuit.appquiz.dto.QuestionDTO;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.service.QuestionService;

@RestController
@RequiredArgsConstructor
public class QuestionControllerImpl implements QuestionController {

    private final QuestionService questionService;

    @Override
    public HttpEntity<ApiResult<QuestionDTO>> addQuestionToTest(Long testId,
                                                                CreateQuestionDTO createQuestionDTO) {
        return ResponseEntity.ok(questionService.addQuestionToTest(testId, createQuestionDTO));
    }
}
