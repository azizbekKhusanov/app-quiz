package uz.tuit.appquiz.service;

import uz.tuit.appquiz.dto.CreateQuestionDTO;
import uz.tuit.appquiz.dto.QuestionDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

public interface QuestionService {

    ApiResult<QuestionDTO> addQuestionToTest(Long testId, CreateQuestionDTO createQuestionDTO);

}
