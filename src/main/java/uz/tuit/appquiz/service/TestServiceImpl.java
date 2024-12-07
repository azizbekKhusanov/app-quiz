package uz.tuit.appquiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.CreateTestDTO;
import uz.tuit.appquiz.dto.TestDTO;
import uz.tuit.appquiz.entity.Test;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.exceptions.RestException;
import uz.tuit.appquiz.repository.SubjectRepository;
import uz.tuit.appquiz.repository.TestRepository;
import uz.tuit.appquiz.service.mapper.TestMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final SubjectRepository subjectRepository;
    private final TestMapper testMapper;

    @Override
    public ApiResult<List<TestDTO>> getTestList() {
        List<Test> all = testRepository.findAll();
        if (all.isEmpty()) {
            throw RestException.restThrow("This list is empty !!! ");
        }
        return ApiResult.successResponse(all
                .stream()
                .map(testMapper::convertToDTO)
                .toList());
    }

    @Override
    public ApiResult<TestDTO> getTestById(Long id) {
        if (id <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }
        Test test = testRepository.findById(id)
                .orElseThrow(() -> RestException
                        .restThrow("Test Not Found !!! ", HttpStatus.BAD_REQUEST));
        return ApiResult.successResponse(testMapper.convertToDTO(test));
    }

    @Override
    public ApiResult<List<TestDTO>> getTestBySubjectId(Long id) {
        if (id <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }
        List<Test> bySubjectId = testRepository.findBySubjectId(id);
        if (bySubjectId.isEmpty()) {
            throw RestException.restThrow("This list is empty", HttpStatus.BAD_REQUEST);
        }
        return ApiResult.successResponse(bySubjectId
                .stream()
                .map(testMapper::convertToDTO)
                .toList());
    }

    @Override
    public ApiResult<TestDTO> createTest(CreateTestDTO createTestDTO) {
        if (testRepository.existsByNameAndSubjectId(createTestDTO.getName(), createTestDTO.getSubjectId())) {
            throw RestException.restThrow("This Test is already exists");
        }
        if (!subjectRepository.existsById(createTestDTO.getSubjectId())) {
            throw RestException.restThrow("Subject not found", HttpStatus.BAD_REQUEST);
        }
        Test save = testRepository.save(testMapper.convertToEntity(createTestDTO));
        return ApiResult.successResponse(testMapper.convertToDTO(save));
    }

    @Override
    public ApiResult<TestDTO> update(Long id, CreateTestDTO createTestDTO) {
        if (id <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }
        if (testRepository.existsByNameAndSubjectId(createTestDTO.getName(), createTestDTO.getSubjectId())) {
            throw RestException.restThrow("This Test is already exists");
        }
        Test test = testRepository.findById(id)
                .orElseThrow(() -> RestException
                        .restThrow("Test Not Found !!! ", HttpStatus.BAD_REQUEST));
        test.setName(createTestDTO.getName());
        test.setDescription(createTestDTO.getDescription());

        test.setSubject(subjectRepository.findById(createTestDTO.getSubjectId())
                .orElseThrow(() -> RestException.restThrow("Subject not found", HttpStatus.BAD_REQUEST)));
        return ApiResult.successResponse(testMapper.convertToDTO(testRepository.save(test)));
    }

    @Override
    public void deleteById(Long id) {
        if (id <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }
        testRepository.findById(id)
                .orElseThrow(() -> RestException
                        .restThrow("Test Not Found !!! ", HttpStatus.BAD_REQUEST));
        testRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        if (testRepository.findAll().isEmpty()) {
            throw RestException.restThrow("This list is empty !!! ");
        }
        testRepository.deleteAll();
    }
}
