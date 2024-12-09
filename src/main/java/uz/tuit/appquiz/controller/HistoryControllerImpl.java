package uz.tuit.appquiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.tuit.appquiz.dto.HistoryDTO;
import uz.tuit.appquiz.exceptions.ApiResult;
import uz.tuit.appquiz.service.HistoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HistoryControllerImpl implements HistoryController {

    private final HistoryService historyService;

    @Override
    public HttpEntity<ApiResult<List<HistoryDTO>>> getAllHistories() {
        return ResponseEntity.ok(historyService.getAllHistories());
    }

    @Override
    public HttpEntity<ApiResult<List<HistoryDTO>>> getUserHistory(Long userId) {
        return ResponseEntity.ok(historyService.getUserHistory(userId));
    }

    @Override
    public HttpEntity<ApiResult<List<HistoryDTO>>> getTestHistory(Long testId) {
        return ResponseEntity.ok(historyService.getTestHistory(testId));
    }
}
