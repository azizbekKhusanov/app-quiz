package uz.tuit.appquiz.service;

import uz.tuit.appquiz.dto.HistoryDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import java.util.List;

public interface HistoryService {
    ApiResult<List<HistoryDTO>> getAllHistories();
    ApiResult<List<HistoryDTO>> getUserHistory(Long userId);
    ApiResult<List<HistoryDTO>> getTestHistory(Long testId);
}
