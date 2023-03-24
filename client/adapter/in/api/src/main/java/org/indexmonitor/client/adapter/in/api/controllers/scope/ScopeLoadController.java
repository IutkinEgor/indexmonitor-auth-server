package org.indexmonitor.client.adapter.in.api.controllers.scope;

import org.indexmonitor.client.application.ports.in.scope.ScopeLoadUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeLoadPageRequest;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeLoadRequest;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopePageResponse;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopeResponse;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/scopes")
@AllArgsConstructor
public class ScopeLoadController {
    private static String[] REQUIRED_ROLES = {"SCOPE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"READ"};
    private final ScopeLoadUseCase scopeLoadUseCase;

    @GetMapping
    public ResponseEntity<BaseResponse<Set<ScopePageResponse>>> findAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse<Set<ScopePageResponse>> response = scopeLoadUseCase.findAll(new ScopeLoadPageRequest(
                page != null ? page : ScopeLoadPageRequest.DEFAULT_PAGE_VALUE,
                size != null ? size :  ScopeLoadPageRequest.DEFAULT_SIZE_VALUE));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse<ScopeResponse>> findById(
            @PathVariable("id") String id){
        BaseResponse<ScopeResponse> response = scopeLoadUseCase.findById(new ScopeLoadRequest(id));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


}
