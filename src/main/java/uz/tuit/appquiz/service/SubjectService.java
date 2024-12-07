package uz.tuit.appquiz.service;

import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.CreateSubjectDTO;
import uz.tuit.appquiz.dto.SubjectDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import java.util.List;

public interface SubjectService {
    ApiResult<List<SubjectDTO>> getAll();
    ApiResult<SubjectDTO> getById(Long id);
    ApiResult<SubjectDTO> create(CreateSubjectDTO createSubjectDTO);
    ApiResult<SubjectDTO> edit(Long id, CreateSubjectDTO createSubjectDTO);
    void deleteById(Long id);
    void deleteAll();
}
