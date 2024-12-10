package uz.tuit.appquiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.*;
import uz.tuit.appquiz.entity.Answer;
import uz.tuit.appquiz.entity.History;
import uz.tuit.appquiz.entity.Question;
import uz.tuit.appquiz.entity.Test;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.exceptions.RestException;
import uz.tuit.appquiz.repository.*;
import uz.tuit.appquiz.service.mapper.AnswerMapper;
import uz.tuit.appquiz.service.mapper.QuestionMapper;
import uz.tuit.appquiz.service.mapper.TestMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final SubjectRepository subjectRepository;
    private final QuestionRepository questionRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final QuestionMapper questionMapper;
    private final TestMapper testMapper;
    private final AnswerMapper answerMapper;

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
    public ApiResult<TestSessionDTO> startTest(Long testId, Long userId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> RestException
                        .restThrow("Test not found !!! ", HttpStatus.BAD_REQUEST));
        List<QuestionDTO> questionDTOS = questionRepository.findByTestId(testId).stream()
                .map(questionMapper::convertToDTO)
                .toList();
        History history = History.builder()
                .test(test)
                .user(userRepository.findById(userId)
                        .orElseThrow(() -> RestException.
                                restThrow("Test not found !!! ")))
                .createdAt(LocalDateTime.now())
                .totalScore(0)
                .build();
        historyRepository.save(history);
        return ApiResult.successResponse(
                TestSessionDTO.builder()
                        .testId(test.getId())
                        .questions(questionDTOS)
                        .build());
    }

    @Override
    public ApiResult<ResultDTO> finishTest(Long testId, Long userId, List<AnswerDTO> answers) {
        History history = historyRepository.findTopByUserIdAndTestIdOrderByCreatedAt(userId, testId)
                .orElseThrow(() -> RestException.restThrow("History not found !!! "));

        List<Question> questions = questionRepository.findByTestId(testId);
        List<AnswerDTO> correctAnswers = questions.stream()
                .flatMap(question -> question.getAnswers().stream())
                .filter(Answer::isCorrect)
                .map(answerMapper::convertToDTO)
                .toList();

        int correctAnswerCount = 0;
        int totalScore = 0;
        int allTotalScore = 0;
        for (int i = 0; i < correctAnswers.size(); i++) {
            AnswerDTO userAnswerDTO = answers.get(i);
            AnswerDTO correctAnswerDTO = correctAnswers.get(i);

            if (userAnswerDTO != null && Objects.equals(userAnswerDTO.getText(), correctAnswerDTO.getText())) {
                correctAnswerCount++;
                totalScore += questionRepository.findById(userAnswerDTO.getQuestionId())
                        .orElseThrow(() -> RestException
                                .restThrow("Question not found !!! ", HttpStatus.BAD_REQUEST))
                        .getScore();
            }
            history.setTotalScore(totalScore);
            allTotalScore += questionRepository.findById(correctAnswerDTO.getQuestionId())
                    .orElseThrow(() -> RestException
                            .restThrow("Question not found !!! ", HttpStatus.BAD_REQUEST))
                    .getScore();
        }

        return ApiResult.successResponse(ResultDTO.builder()
                .correctAnswers(correctAnswerCount)
                .totalScore(totalScore)
                .totalQuestions(questionRepository.findByTestId(testId).size())
                .percentage(totalScore * 100 / allTotalScore)
                .build());
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
