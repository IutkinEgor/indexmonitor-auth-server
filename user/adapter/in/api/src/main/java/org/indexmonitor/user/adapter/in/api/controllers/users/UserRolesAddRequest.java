package org.indexmonitor.user.adapter.in.api.controllers.users;

import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserRolesAddUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserRolesUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/{userId}/roles")
@AllArgsConstructor
public class UserRolesAddRequest {
    private static String[] REQUIRED_ROLES = {"USER-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    private final UserRolesAddUseCase userRolesAddUseCase;

    @PutMapping
    public ResponseEntity<BaseResponse> add(@PathVariable("userId") String userId, @RequestBody UserRolesUpdateRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setId(userId);
        BaseResponse response = userRolesAddUseCase.add(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
