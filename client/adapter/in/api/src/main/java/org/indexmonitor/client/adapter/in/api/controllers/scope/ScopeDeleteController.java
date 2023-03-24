package org.indexmonitor.client.adapter.in.api.controllers.scope;

import org.indexmonitor.client.application.ports.in.scope.ScopeDeleteUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeDeleteRequest;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/scopes/{id}")
@AllArgsConstructor
public class ScopeDeleteController {
    private static String[] REQUIRED_ROLES = {"SCOPE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"DELETE"};
    private final ScopeDeleteUseCase scopeDeleteUseCase;

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") String id){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse response = scopeDeleteUseCase.delete(new ScopeDeleteRequest(id));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
