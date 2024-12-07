package uz.tuit.appquiz.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.tuit.appquiz.dto.CreateSubjectDTO;
import uz.tuit.appquiz.dto.SubjectDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import java.util.List;

import static uz.tuit.appquiz.controller.SubjectController.BASE_PATH;

@RequestMapping(BASE_PATH)
public interface SubjectController {

    String BASE_PATH = "/api-subject";

    @GetMapping
    HttpEntity<ApiResult<List<SubjectDTO>>> getAll();

    @GetMapping("/{id}")
    HttpEntity<ApiResult<SubjectDTO>> getById(@PathVariable Long id);

    @PostMapping("/add")
    HttpEntity<ApiResult<SubjectDTO>> add(@Valid @RequestBody CreateSubjectDTO createSubjectDTO);

    @PutMapping("/edit/{id}")
    HttpEntity<ApiResult<SubjectDTO>> edit(@PathVariable Long id,
                                           @Valid @RequestBody CreateSubjectDTO createSubjectDTO);

    @DeleteMapping("/delete/{id}")
    HttpEntity<?> deleteById(@PathVariable Long id);

    @DeleteMapping("/delete")
    HttpEntity<?> deleteAll();

}
