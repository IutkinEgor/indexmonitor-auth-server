package org.indexmonitor.user.adapter.in.api.controllers.users;

import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserAuthoritiesRemoveUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserAuthoritiesUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users/{userId}/authorities/{authorityId}")
@AllArgsConstructor
public class UserAuthoritiesRemoveController {
    private static String[] REQUIRED_ROLES = {"USER-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    private final UserAuthoritiesRemoveUseCase userAuthoritiesRemoveUseCase;

    @DeleteMapping
    public ResponseEntity<BaseResponse> remove(@PathVariable(name = "userId") String userId, @PathVariable(name = "authorityId") String authorityId){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse response = userAuthoritiesRemoveUseCase.remove(new UserAuthoritiesUpdateRequest(userId, authorityId));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
