package uz.tuit.appquiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.CreateSubjectDTO;
import uz.tuit.appquiz.dto.SubjectDTO;
import uz.tuit.appquiz.entity.Subject;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.exceptions.RestException;
import uz.tuit.appquiz.service.mapper.SubjectMapper;
import uz.tuit.appquiz.repository.SubjectRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public ApiResult<List<SubjectDTO>> getAll() {
        List<Subject> all = subjectRepository.findAll();
        if (all.isEmpty()) {
            throw RestException.restThrow("This list is empty !!! ");
        }
        return ApiResult.successResponse(all
                .stream()
                .map(subjectMapper::convertToDTO)
                .toList());
    }

    @Override
    public ApiResult<SubjectDTO> getById(Long id) {
        if (id <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> RestException
                        .restThrow("Subject Not Found !!! ", HttpStatus.BAD_REQUEST));
        return ApiResult.successResponse(subjectMapper.convertToDTO(subject));
    }

    @Override
    public ApiResult<SubjectDTO> create(CreateSubjectDTO createSubjectDTO) {
        if (subjectRepository.existsByName(createSubjectDTO.getName())) {
            throw RestException.restThrow("This Subject already exists");
        }
        Subject save = subjectRepository.save(subjectMapper.toSubject(createSubjectDTO));
        return ApiResult.successResponse(subjectMapper.convertToDTO(save));
    }

    @Override
    public ApiResult<SubjectDTO> edit(Long id, CreateSubjectDTO createSubjectDTO) {
        if (id <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }
        if (subjectRepository.existsByName(createSubjectDTO.getName())) {
            throw RestException.restThrow("This Subject already exists");
        }
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> RestException
                        .restThrow("Subject Not Found !!! ", HttpStatus.BAD_REQUEST));
        subject.setName(createSubjectDTO.getName());
        return ApiResult.successResponse(subjectMapper.convertToDTO(subjectRepository.save(subject)));
    }

    @Override
    public void deleteById(Long id) {
        if (id <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }
        subjectRepository.findById(id)
                .orElseThrow(() -> RestException
                        .restThrow("Subject Not Found !!! ", HttpStatus.BAD_REQUEST));
        subjectRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        if (subjectRepository.findAll().isEmpty()) {
            throw RestException.restThrow("This list is empty !!! ", HttpStatus.BAD_REQUEST);
        }
        subjectRepository.deleteAll();
    }

}
