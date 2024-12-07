package uz.tuit.appquiz.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.tuit.appquiz.dto.HistoryDTO;
import uz.tuit.appquiz.exceptions.ApiResult;

import java.util.List;

import static uz.tuit.appquiz.controller.HistoryController.BASE_PATH;

@RequestMapping(BASE_PATH)
public interface HistoryController {
    String BASE_PATH = "/api-history";

    @GetMapping("/all")
    HttpEntity<ApiResult<List<HistoryDTO>>> getAllHistories();

    @GetMapping("/by{userId}")
    HttpEntity<ApiResult<List<HistoryDTO>>> getUserHistory(@PathVariable Long userId);

    @GetMapping("/by/{testId}")
    HttpEntity<ApiResult<List<HistoryDTO>>> getTestHistory(@PathVariable Long testId);

}
