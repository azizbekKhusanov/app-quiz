package uz.tuit.appquiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import uz.tuit.appquiz.dto.*;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.service.TestService;
import uz.tuit.appquiz.utils.AppConstants;

import java.util.List;

@RestController
@CrossOrigin(AppConstants.REACT_URL)
@RequiredArgsConstructor
public class TestControllerImpl implements TestController {

    private final TestService testService;

    @Override
    public HttpEntity<ApiResult<List<TestDTO>>> getAllTests() {
        return ResponseEntity.ok(testService.getTestList());
    }

    @Override
    public HttpEntity<ApiResult<TestDTO>> getById(Long id) {
        return ResponseEntity.ok(testService.getTestById(id));
    }

    @Override
    public HttpEntity<ApiResult<List<TestDTO>>> getTestBySubjectId(Long id) {
        return ResponseEntity.ok(testService.getTestBySubjectId(id));
    }

    @Override
    public HttpEntity<ApiResult<TestSessionDTO>> startTest(Long testId, Long userId) {
        return ResponseEntity.ok(testService.startTest(testId, userId));
    }

    @Override
    public HttpEntity<ApiResult<ResultDTO>> finishTest(Long testId, Long userId, List<AnswerDTO> answers) {
        return ResponseEntity.ok(testService.finishTest(testId, userId, answers));
    }

    @Override
    public HttpEntity<ApiResult<TestDTO>> createTest(CreateTestDTO createTestDTO) {
        return ResponseEntity.ok(testService.createTest(createTestDTO));
    }

    @Override
    public HttpEntity<ApiResult<TestDTO>> edit(Long id, CreateTestDTO createTestDTO) {
        return ResponseEntity.ok(testService.update(id, createTestDTO));
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        testService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public HttpEntity<?> deleteAll() {
        testService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
