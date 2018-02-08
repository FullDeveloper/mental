package com.emed.mental.common.result.auth;


import com.emed.mental.common.constant.RestCodeConstants;
import com.emed.mental.common.result.BaseResponse;

/**
 * Created by ace on 2017/8/23.
 */
public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
