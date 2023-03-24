package org.indexmonitor.user.adapter.in.api.controllers.authorities;

import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.authority.AuthoritySettingsUpdateUseCase;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthoritySettingsUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authorities/{id}")
@AllArgsConstructor
public class AuthoritySettingsUpdateController {
    private static String[] REQUIRED_ROLES = {"AUTHORITY-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    private final AuthoritySettingsUpdateUseCase authoritySettingsUpdateUseCase;
    @PutMapping
    public ResponseEntity<BaseResponse> update(@PathVariable("id") String id, @RequestBody AuthoritySettingsUpdateRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setId(id);
        BaseResponse response = authoritySettingsUpdateUseCase.update(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
