package org.indexmonitor.client.adapter.in.api.controllers.scope;

import org.indexmonitor.client.application.ports.in.scope.ScopeRegisterUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeRegisterRequest;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.adapter.in.api.utils.OAuthExtractUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/scopes")
@AllArgsConstructor
public class ScopeRegisterController {
    private static String[] REQUIRED_ROLES = {"SCOPE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"CREATE"};
    private final ScopeRegisterUseCase scopeRegisterUseCase;

    @PostMapping
    public ResponseEntity<BaseResponse> register(@RequestBody ScopeRegisterRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setCreatedBy(OAuthExtractUtil.getSub());
        BaseResponse response = scopeRegisterUseCase.register(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
