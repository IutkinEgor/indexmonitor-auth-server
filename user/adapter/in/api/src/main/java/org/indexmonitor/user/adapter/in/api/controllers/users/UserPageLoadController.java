package org.indexmonitor.user.adapter.in.api.controllers.users;

import org.indexmonitor.common.adapter.in.api.models.BaseController;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.user.application.ports.in.role.requests.RolePageLoadRequest;
import org.indexmonitor.user.application.ports.in.user.UserPageLoadUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserPageLoadRequest;
import org.indexmonitor.user.application.ports.in.user.responses.UserPageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserPageLoadController extends BaseController {
    private static String[] REQUIRED_ROLES = {"USER-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"READ"};
    private final UserPageLoadUseCase userPageLoadUseCase;

    @GetMapping
    public ResponseEntity<BasePageResponse<UserPageResponse>> findAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BasePageResponse<UserPageResponse> response = userPageLoadUseCase.load(new UserPageLoadRequest(
                page != null ? page : RolePageLoadRequest.DEFAULT_PAGE_VALUE,
                size != null ? size :  RolePageLoadRequest.DEFAULT_SIZE_VALUE));
        return responsePage(response);
    }
}
