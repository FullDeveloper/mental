package com.emed.mental.common.result.auth;
import com.emed.mental.common.constant.RestCodeConstants;
import com.emed.mental.common.result.BaseResponse;

/**
 * Created by ace on 2017/8/25.
 */
public class TokenForbiddenResponse  extends BaseResponse {
    public TokenForbiddenResponse(String message) {
        super(RestCodeConstants.TOKEN_FORBIDDEN_CODE, message);
    }
}
