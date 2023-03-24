package org.indexmonitor.user.adapter.in.api.controllers.roles;

import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.role.RoleSettingsLoadUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RoleSettingsLoadRequest;
import org.indexmonitor.user.application.ports.in.role.responses.RoleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roles/{id}")
@AllArgsConstructor
public class RoleSettingsLoadController {
    private static String[] REQUIRED_ROLES = {"ROLE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"READ"};
    private final RoleSettingsLoadUseCase roleSettingsLoadUseCase;
    @GetMapping
    public ResponseEntity<BaseResponse<RoleResponse>> findById(
            @PathVariable("id") String id){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse<RoleResponse> response = roleSettingsLoadUseCase.load(new RoleSettingsLoadRequest(id));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
