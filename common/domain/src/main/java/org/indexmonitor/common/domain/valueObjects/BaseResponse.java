package org.indexmonitor.common.domain.valueObjects;

import org.indexmonitor.common.domain.models.ValueObject;
import java.time.Instant;

public class BaseResponse<Data> extends ValueObject {

    private final static String FAILURE_DEFAULT_MESSAGE = "Wrong request";
    private final Instant createdAt;
    private final Boolean isSuccess;
    private final String message;
    private final Data data;
    protected BaseResponse(Boolean isSuccess, String message, Data data) {
        this.createdAt = Instant.now();
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(Data data) {
        this(true, null, data);
    }
    public static BaseResponse success(){
        return new BaseResponse(true, null, null);
    }
    public static BaseResponse failure(){
        return new BaseResponse(false, FAILURE_DEFAULT_MESSAGE, null);
    }
    public static BaseResponse failure(String message){
        return new BaseResponse(false, message, null);
    }

    public long getCreatedAt() {
        return createdAt.toEpochMilli();
    }
    public Boolean isSuccess() {
        return isSuccess;
    }
    public Boolean isFailure() {
        return !isSuccess;
    }
    public String getMessage() {
        return message;
    }
    public Data getData() {
        return data;
    }
}
