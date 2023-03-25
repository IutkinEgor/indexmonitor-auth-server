package org.indexmonitor.user.adapter.in.api.controllers.users;

import lombok.AllArgsConstructor;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserSettingsUpdateUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserSettingsUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/{id}/settings")
@AllArgsConstructor
public class UserSettingsUpdateController {
    private static String[] REQUIRED_ROLES = {"USER-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    private final UserSettingsUpdateUseCase userSettingsUpdateUseCase;

    @PutMapping
    public ResponseEntity<BaseResponse> update(@PathVariable(name = "id") String id, @RequestBody UserSettingsUpdateRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setUserId(id);
        BaseResponse response = userSettingsUpdateUseCase.update(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
