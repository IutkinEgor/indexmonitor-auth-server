package org.indexmonitor.user.adapter.in.api.controllers.authorities;

import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.authority.AuthorityDeleteUseCase;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityDeleteRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authorities/{id}")
@AllArgsConstructor
public class AuthorityDeleteController {
    private static String[] REQUIRED_ROLES = {"AUTHORITY-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"DELETE"};
    private final AuthorityDeleteUseCase authorityDeleteUseCase;

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") String id){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse response = authorityDeleteUseCase.delete(new AuthorityDeleteRequest(id));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
