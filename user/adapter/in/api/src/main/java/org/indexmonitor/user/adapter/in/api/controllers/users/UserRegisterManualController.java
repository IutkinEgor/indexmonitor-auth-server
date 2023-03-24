package org.indexmonitor.user.adapter.in.api.controllers.users;

import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserRegisterManualUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterManualRequest;
import org.indexmonitor.user.application.ports.in.user.responses.UserProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserRegisterManualController {
    private static String[] REQUIRED_ROLES = {"USER-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"CREATE"};
    private final UserRegisterManualUseCase userRegisterManualUseCase;
    @PostMapping
    public ResponseEntity<BaseResponse<UserProfileResponse>> register(@RequestBody UserRegisterManualRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse<UserProfileResponse> response = userRegisterManualUseCase.register(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
