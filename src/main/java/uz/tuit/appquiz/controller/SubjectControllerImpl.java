package uz.tuit.appquiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import uz.tuit.appquiz.dto.CreateSubjectDTO;
import uz.tuit.appquiz.dto.SubjectDTO;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.service.SubjectService;
import uz.tuit.appquiz.utils.AppConstants;

import java.util.List;

@RestController
@CrossOrigin(AppConstants.REACT_URL)
@RequiredArgsConstructor
public class SubjectControllerImpl implements SubjectController {

    private final SubjectService subjectService;

    @Override
    public HttpEntity<ApiResult<List<SubjectDTO>>> getAll() {
        return ResponseEntity.ok(subjectService.getAll());
    }

    @Override
    public HttpEntity<ApiResult<SubjectDTO>> getById(Long id) {
        return ResponseEntity.ok(subjectService.getById(id));
    }

    @Override
    public HttpEntity<ApiResult<SubjectDTO>> add(CreateSubjectDTO createSubjectDTO) {
        return ResponseEntity.ok(subjectService.create(createSubjectDTO));
    }

    @Override
    public HttpEntity<ApiResult<SubjectDTO>> edit(Long id, CreateSubjectDTO createSubjectDTO) {
        return ResponseEntity.ok(subjectService.edit(id, createSubjectDTO));
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        subjectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public HttpEntity<?> deleteAll() {
        subjectService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
