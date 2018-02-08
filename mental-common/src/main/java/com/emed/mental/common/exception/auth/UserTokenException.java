package com.emed.mental.common.exception.auth;


import com.emed.mental.common.constant.CommonConstants;
import com.emed.mental.common.exception.BaseException;


public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
