package com.example.demo.Exception;

import org.springframework.http.HttpStatus;

public class BusinessValidationException extends BusinessException {
    public BusinessValidationException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY; // Trả về mã lỗi 422
    }
}
