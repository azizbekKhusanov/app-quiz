package uz.tuit.appquiz.service;


import uz.tuit.appquiz.dto.CreateQuestionDTO;
import uz.tuit.appquiz.dto.CreateTestDTO;
import uz.tuit.appquiz.dto.QuestionDTO;
import uz.tuit.appquiz.dto.TestDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import java.util.List;

public interface TestService {
    ApiResult<List<TestDTO>> getTestList();
    ApiResult<TestDTO> getTestById(Long id);
    ApiResult<List<TestDTO>> getTestBySubjectId(Long id);
    ApiResult<TestDTO> createTest(CreateTestDTO createTestDTO);
    ApiResult<TestDTO> update(Long id, CreateTestDTO createTestDTO);
    void deleteById(Long id);
    void deleteAll();

}
