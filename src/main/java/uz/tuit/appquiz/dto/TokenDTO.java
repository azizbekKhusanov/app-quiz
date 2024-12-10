package uz.tuit.appquiz.dto;

import lombok.Builder;
import lombok.Getter;
import uz.tuit.appquiz.utils.AppConstants;

@Builder
@Getter
public class TokenDTO {
    private String accessToken;

    private String refreshToken;

    @Builder.Default
    private String tokenType = AppConstants.BEARER_AUTH;

}
