package uz.tuit.appquiz.controller;


import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.tuit.appquiz.dto.CreateQuestionDTO;
import uz.tuit.appquiz.dto.CreateTestDTO;
import uz.tuit.appquiz.dto.QuestionDTO;
import uz.tuit.appquiz.dto.TestDTO;
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

    @PostMapping("/add")
    HttpEntity<ApiResult<TestDTO>> createTest(@RequestBody CreateTestDTO createTestDTO);

    @PutMapping("/edit/{id}")
    HttpEntity<ApiResult<TestDTO>> edit(@PathVariable Long id, @RequestBody CreateTestDTO createTestDTO);

    @DeleteMapping("/delete/{id}")
    HttpEntity<?> deleteById(@PathVariable Long id);

    @DeleteMapping("/delete")
    HttpEntity<?> deleteAll();

}
