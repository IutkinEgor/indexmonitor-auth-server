package org.indexmonitor.user.adapter.in.api.controllers.roles;

import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.adapter.in.api.utils.OAuthExtractUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.role.RoleRegisterUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RoleRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roles")
@AllArgsConstructor
public class RoleRegisterController {
    private static String[] REQUIRED_ROLES = {"ROLE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"CREATE"};
    private final RoleRegisterUseCase roleRegisterUseCase;

    @PostMapping
    public ResponseEntity<BaseResponse> register(@RequestBody RoleRegisterRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setCreatedBy(OAuthExtractUtil.getSub());
        BaseResponse response = roleRegisterUseCase.register(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
