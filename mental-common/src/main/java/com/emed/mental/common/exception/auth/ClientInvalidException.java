package com.emed.mental.common.exception.auth;


import com.emed.mental.common.constant.CommonConstants;
import com.emed.mental.common.exception.BaseException;


public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
