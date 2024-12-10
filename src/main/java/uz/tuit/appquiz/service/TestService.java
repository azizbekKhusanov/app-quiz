package uz.tuit.appquiz.service;


import uz.tuit.appquiz.dto.*;
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

    ApiResult<TestSessionDTO> startTest(Long testId, Long userId);

    ApiResult<ResultDTO> finishTest(Long testId, Long userId, List<AnswerDTO> answers);

}
