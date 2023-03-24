package org.indexmonitor.common.application.models;

import org.indexmonitor.common.application.exception.AppConstraintValidationException;
import org.indexmonitor.common.application.exception.AppValidationException;
import org.indexmonitor.common.domain.exceptions.AdapterException;
import org.indexmonitor.common.domain.exceptions.ApplicationException;
import org.indexmonitor.common.domain.exceptions.DomainException;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

import java.util.Set;

public abstract class Interactor {

    protected <T> BaseResponse<T> onRequestSuccess(){
        return new BaseResponse<T>(null);
    }
    protected <T> BaseResponse<T> onRequestSuccess(T data){ return new BaseResponse<T>(data); }
    protected <T> BasePageResponse<T> onPageRequestSuccess(BasePage<T> data){ return new BasePageResponse<T>(data); }

    protected <T> BaseResponse<T> onRequestFailure(){
        return BaseResponse.failure();
    }

    protected <T> BaseResponse<T> onRequestFailure(Exception e){
        if(e instanceof AppConstraintValidationException){
            Set<String> validationErrors = ((AppConstraintValidationException) e).getErrors();
            return BaseResponse.failure(validationErrors.iterator().next());
        }
        if(e instanceof AppValidationException){
            Set<String> validationErrors = ((AppValidationException) e).getErrors();
            return BaseResponse.failure(validationErrors.iterator().next());
        }
        if(e instanceof ApplicationException){
            return BaseResponse.failure(e.getMessage());
        }
        if(e instanceof AdapterException){
            return BaseResponse.failure(e.getMessage());
        }
        if(e instanceof DomainException){
            return BaseResponse.failure(e.getMessage());
        }
        return BaseResponse.failure();
    }

    protected <T> BasePageResponse<T> onPageRequestFailure(Exception e){
        if(e instanceof AppConstraintValidationException){
            Set<String> validationErrors = ((AppConstraintValidationException) e).getErrors();
            return BasePageResponse.failure(validationErrors.iterator().next());
        }
        if(e instanceof AppValidationException){
            Set<String> validationErrors = ((AppValidationException) e).getErrors();
            return BasePageResponse.failure(validationErrors.iterator().next());
        }
        if(e instanceof ApplicationException){
            return BasePageResponse.failure(e.getMessage());
        }
        if(e instanceof AdapterException){
            return BasePageResponse.failure(e.getMessage());
        }
        if(e instanceof DomainException){
            return BasePageResponse.failure(e.getMessage());
        }
        return BasePageResponse.failure();
    }
}
