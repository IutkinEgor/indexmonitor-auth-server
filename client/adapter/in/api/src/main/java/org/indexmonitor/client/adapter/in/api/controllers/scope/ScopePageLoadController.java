package org.indexmonitor.client.adapter.in.api.controllers.scope;

import lombok.AllArgsConstructor;
import org.indexmonitor.client.application.ports.in.scope.ScopePageLoadUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopePageLoadRequest;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopePageResponse;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/scopes")
@AllArgsConstructor
public class ScopePageLoadController {
    private static String[] REQUIRED_ROLES = {"SCOPE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"READ"};
    private final ScopePageLoadUseCase scopePageLoadUseCase;

    @GetMapping
    public ResponseEntity<BasePageResponse<ScopePageResponse>> findAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BasePageResponse<ScopePageResponse> response = scopePageLoadUseCase.load(new ScopePageLoadRequest(
                page != null ? page : ScopePageLoadRequest.DEFAULT_PAGE_VALUE,
                size != null ? size :  ScopePageLoadRequest.DEFAULT_SIZE_VALUE));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
