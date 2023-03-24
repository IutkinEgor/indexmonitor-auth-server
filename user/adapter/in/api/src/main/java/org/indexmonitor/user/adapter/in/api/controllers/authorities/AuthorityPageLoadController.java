package org.indexmonitor.user.adapter.in.api.controllers.authorities;

import org.indexmonitor.common.adapter.in.api.models.BaseController;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.user.application.ports.in.authority.AuthorityPageLoadUseCase;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityPageLoadRequest;
import org.indexmonitor.user.application.ports.in.authority.responses.AuthorityPageResponse;
import org.indexmonitor.user.application.ports.in.role.requests.RolePageLoadRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authorities")
@AllArgsConstructor
public class AuthorityPageLoadController extends BaseController {
    private static String[] REQUIRED_ROLES = {"AUTHORITY-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"READ"};
    private final AuthorityPageLoadUseCase authorityPageLoadUseCase;

    @GetMapping
    public ResponseEntity<BasePageResponse<AuthorityPageResponse>> findAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BasePageResponse<AuthorityPageResponse> response = authorityPageLoadUseCase.load(new AuthorityPageLoadRequest(
                page != null ? page : RolePageLoadRequest.DEFAULT_PAGE_VALUE,
                size != null ? size :  RolePageLoadRequest.DEFAULT_SIZE_VALUE));
        return responsePage(response);
    }
}
