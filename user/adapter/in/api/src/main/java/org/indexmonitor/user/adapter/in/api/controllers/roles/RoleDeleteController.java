package org.indexmonitor.user.adapter.in.api.controllers.roles;

import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.role.RoleDeleteUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RoleDeleteRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roles/{id}")
@AllArgsConstructor
public class RoleDeleteController {
    private static String[] REQUIRED_ROLES = {"ROLE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"DELETE"};
    private final RoleDeleteUseCase roleDeleteUseCase;

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") String id){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse response = roleDeleteUseCase.delete(new RoleDeleteRequest(id));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
