package org.indexmonitor.common.adapter.in.api.advices;

import org.indexmonitor.common.adapter.in.api.exceptions.AccessControlException;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccessControlControllerAdvice {

        @ExceptionHandler({ AccessControlException.class })
    public ResponseEntity<BaseResponse> authorityException(AccessControlException ex) {
        return new ResponseEntity<BaseResponse>(BaseResponse.failure(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

}
