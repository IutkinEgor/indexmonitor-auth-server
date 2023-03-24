package org.indexmonitor.user.adapter.in.api.controllers.roles;

import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.role.RoleSettingsUpdateUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RoleSettingsUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roles/{id}")
@AllArgsConstructor
public class RoleSettingsUpdateController {
    private static String[] REQUIRED_ROLES = {"ROLE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    private final RoleSettingsUpdateUseCase roleSettingsUpdateUseCase;
    @PutMapping
    public ResponseEntity<BaseResponse> update(@PathVariable("id") String id, @RequestBody  RoleSettingsUpdateRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setId(id);
        BaseResponse response = roleSettingsUpdateUseCase.update(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
