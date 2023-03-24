package org.indexmonitor.user.adapter.in.api.controllers.roles;

import org.indexmonitor.common.adapter.in.api.models.BaseController;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.user.application.ports.in.role.RolePageLoadUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RolePageLoadRequest;
import org.indexmonitor.user.application.ports.in.role.responses.RolePageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roles")
@AllArgsConstructor
public class RolePageLoadController extends BaseController {
    private static String[] REQUIRED_ROLES = {"ROLE-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"READ"};
    private final RolePageLoadUseCase rolePageLoadUseCase;

    @GetMapping
    public ResponseEntity<BasePageResponse<RolePageResponse>> findAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BasePageResponse<RolePageResponse> response = rolePageLoadUseCase.load(new RolePageLoadRequest(
                page != null ? page : RolePageLoadRequest.DEFAULT_PAGE_VALUE,
                size != null ? size :  RolePageLoadRequest.DEFAULT_SIZE_VALUE));
        return responsePage(response);
    }
}
