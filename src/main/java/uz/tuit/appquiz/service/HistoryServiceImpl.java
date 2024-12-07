package uz.tuit.appquiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.tuit.appquiz.dto.HistoryDTO;
import uz.tuit.appquiz.entity.History;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.exceptions.RestException;
import uz.tuit.appquiz.repository.HistoryRepository;
import uz.tuit.appquiz.repository.TestRepository;
import uz.tuit.appquiz.repository.UserRepository;
import uz.tuit.appquiz.service.mapper.HistoryMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final TestRepository testRepository;
    private final HistoryMapper historyMapper;

    @Override
    public ApiResult<List<HistoryDTO>> getAllHistories() {
        List<History> allHistory = historyRepository.findAll();
        if (allHistory.isEmpty()) {
            throw RestException.restThrow("This list is empty !!!", HttpStatus.BAD_REQUEST);
        }
        return ApiResult.successResponse(allHistory.stream()
                .map(historyMapper::convertToDTO)
                .toList());
    }

    @Override
    public ApiResult<List<HistoryDTO>> getUserHistory(Long userId) {
        if (userId <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }

        if (!userRepository.existsById(userId)) {
            throw RestException.restThrow("User not found !!! ");
        }
        List<History> byUserId = historyRepository.findByUserId(userId);
        if (byUserId.isEmpty()) {
            throw RestException.restThrow("This list is Empty !!! ");
        }

        return ApiResult.successResponse(byUserId.stream()
                .map(historyMapper::convertToDTO)
                .toList());
    }

    @Override
    public ApiResult<List<HistoryDTO>> getTestHistory(Long testId) {
        if (testId <= 0) {
            throw RestException.restThrow("ID is INCORRECT");
        }

        if (!testRepository.existsById(testId)) {
            throw RestException.restThrow("Test not found !!! ");
        }
        List<History> byTestId = historyRepository.findByTestId(testId);
        if (byTestId.isEmpty()) {
            throw RestException.restThrow("This list is Empty !!! ");
        }

        return ApiResult.successResponse(byTestId.stream()
                .map(historyMapper::convertToDTO)
                .toList());
    }

}
