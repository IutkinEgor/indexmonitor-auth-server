package org.indexmonitor.client.adapter.in.api.controllers.scope;

import lombok.AllArgsConstructor;
import org.indexmonitor.client.application.ports.in.scope.ScopeLoadUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeLoadRequest;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopeResponse;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/scopes/{id}")
@AllArgsConstructor
public class ScopeLoadController {
    private static String[] REQUIRED_ROLES = {"SCOPE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"READ"};
    private final ScopeLoadUseCase scopeLoadUseCase;

    @GetMapping
    public ResponseEntity<BaseResponse<ScopeResponse>> findById(
            @PathVariable("id") String id){
        BaseResponse<ScopeResponse> response = scopeLoadUseCase.findById(new ScopeLoadRequest(id));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
