package org.indexmonitor.user.adapter.in.api.controllers.authorities;

import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.authority.AuthoritySettingsLoadUseCase;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthoritySettingsLoadRequest;
import org.indexmonitor.user.application.ports.in.authority.responses.AuthorityResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authorities/{id}")
@AllArgsConstructor
public class AuthoritySettingsLoadController {
    private static String[] REQUIRED_ROLES = {"AUTHORITY-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"READ"};
    private final AuthoritySettingsLoadUseCase authoritySettingsLoadUseCase;
    @GetMapping
    public ResponseEntity<BaseResponse<AuthorityResponse>> findById(@PathVariable("id") String id){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse<AuthorityResponse> response = authoritySettingsLoadUseCase.load(new AuthoritySettingsLoadRequest(id));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
