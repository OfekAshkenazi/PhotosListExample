package com.ofek.photosexam.common.errors;

public interface BaseError {


    void handle(BaseErrorHandleProtocol protocol);

    interface BaseErrorHandleProtocol {

    }
}
