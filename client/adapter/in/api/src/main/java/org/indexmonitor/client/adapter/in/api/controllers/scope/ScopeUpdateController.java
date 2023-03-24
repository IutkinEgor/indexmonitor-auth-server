package org.indexmonitor.client.adapter.in.api.controllers.scope;

import org.indexmonitor.client.application.ports.in.scope.ScopeUpdateUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeUpdateRequest;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/scopes/{id}")
@AllArgsConstructor
public class ScopeUpdateController {
    private static String[] REQUIRED_ROLES = {"SCOPE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    private final ScopeUpdateUseCase scopeUpdateUseCase;

    @PutMapping
    public ResponseEntity<BaseResponse> register(@PathVariable(name = "id") String id, @RequestBody ScopeUpdateRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setId(id);
        BaseResponse response = scopeUpdateUseCase.update(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }

}
