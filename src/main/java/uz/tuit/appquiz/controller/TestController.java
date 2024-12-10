package uz.tuit.appquiz.controller;


import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.tuit.appquiz.dto.*;
import uz.tuit.appquiz.exceptions.ApiResult;

import java.util.List;

import static uz.tuit.appquiz.controller.TestController.BASE_PATH;

@RequestMapping(BASE_PATH)
public interface TestController {

    String BASE_PATH = "/api-test";

    @GetMapping
    HttpEntity<ApiResult<List<TestDTO>>> getAllTests();

    @GetMapping("/{id}")
    HttpEntity<ApiResult<TestDTO>> getById(@PathVariable Long id);

    @GetMapping("/subject/{id}")
    HttpEntity<ApiResult<List<TestDTO>>> getTestBySubjectId(@PathVariable Long id);

    @PostMapping("/{testId}/start")
    HttpEntity<ApiResult<TestSessionDTO>> startTest(@PathVariable Long testId, @RequestParam Long userId);


    @PostMapping("/{testId}/finish")
    HttpEntity<ApiResult<ResultDTO>> finishTest(@PathVariable Long testId,
                                                @RequestParam Long userId,
                                                @RequestBody List<AnswerDTO> answers);

    @PostMapping("/add")
    HttpEntity<ApiResult<TestDTO>> createTest(@RequestBody CreateTestDTO createTestDTO);

    @PutMapping("/edit/{id}")
    HttpEntity<ApiResult<TestDTO>> edit(@PathVariable Long id, @RequestBody CreateTestDTO createTestDTO);

    @DeleteMapping("/delete/{id}")
    HttpEntity<?> deleteById(@PathVariable Long id);

    @DeleteMapping("/delete")
    HttpEntity<?> deleteAll();

}
