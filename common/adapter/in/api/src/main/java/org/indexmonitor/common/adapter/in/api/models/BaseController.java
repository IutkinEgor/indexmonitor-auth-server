package org.indexmonitor.common.adapter.in.api.models;

import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    private static String PAGE_HEADER = "X-Page";
    private static String SIZE_HEADER = "X-Page-Size";
    private static String COUNT_HEADER = "X-Total-Count";
    private static String TOTAL_COUNT_HEADER = "X-Total-Records";

    public <MODEL> ResponseEntity<BasePageResponse<MODEL>> responsePage(BasePageResponse<MODEL> response){
        if(response.isFailure()){
            return ResponseEntity.badRequest().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(PAGE_HEADER, response.getCurrentPage().toString());
        headers.add(SIZE_HEADER, response.getCurrentSize().toString());
        headers.add(COUNT_HEADER,String.valueOf(response.getData().size()));
        headers.add(TOTAL_COUNT_HEADER, response.getTotalCount().toString());
        return ResponseEntity.ok().headers(headers).body(response);
    }
}
