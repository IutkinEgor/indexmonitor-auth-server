package org.indexmonitor.user.adapter.in.api.controllers.users;

import lombok.AllArgsConstructor;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserProfileUpdateUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserProfileUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/{id}/profile")
@AllArgsConstructor
public class UserProfileUpdateController {
    private static String[] REQUIRED_ROLES = {"USER-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    private final UserProfileUpdateUseCase userProfileUpdateUseCase;

    @PutMapping
    public ResponseEntity<BaseResponse> register(@PathVariable(name = "id") String id, @RequestBody UserProfileUpdateRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setId(id);
        BaseResponse response = userProfileUpdateUseCase.update(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
