package org.indexmonitor.common.domain.valueObjects;

import java.util.Collection;

public class BasePageResponse<Data> extends BaseResponse<Collection<Data>>{
    private final static String FAILURE_DEFAULT_MESSAGE = "Wrong request";
    private Long totalCount;
    private Integer currentPage;
    private Integer currentSize;

    private BasePageResponse(Boolean isSuccess, String message, Collection<Data> data) {
        super(isSuccess,message,data);
        totalCount = null;
        currentPage = null;
        currentSize = null;
    }
    public BasePageResponse(BasePage<Data> data) {
        super(data.getElements());
        totalCount = data.getTotalCount();
        currentPage = data.getCurrentPage();
        currentSize = data.getCurrentSize();
    }
    public static BasePageResponse failure(){
        return new BasePageResponse(false, FAILURE_DEFAULT_MESSAGE, null);
    }
    public static BasePageResponse failure(String message){
        return new BasePageResponse(false, message, null);
    }

    public Long getTotalCount() {
        return totalCount;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }
    public Integer getCurrentSize() {
        return currentSize;
    }
}
