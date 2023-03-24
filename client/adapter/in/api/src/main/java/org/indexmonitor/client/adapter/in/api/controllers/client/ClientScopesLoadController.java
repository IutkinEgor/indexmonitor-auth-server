package org.indexmonitor.client.adapter.in.api.controllers.client;
import org.indexmonitor.client.application.ports.in.client.ClientScopeLoadUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientLoadRequest;
import org.indexmonitor.client.application.ports.in.client.responses.ClientScopesPageResponse;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/clients/{id}/scopes")
@AllArgsConstructor
public class ClientScopesLoadController {
    private static String[] REQUIRED_ROLES = {"CLIENT-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"READ"};
    ClientScopeLoadUseCase clientScopeLoadUseCase;

    @GetMapping
    public ResponseEntity<BaseResponse<Set<ClientScopesPageResponse>>> findById(@PathVariable("id") String id){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse<Set<ClientScopesPageResponse>> response = clientScopeLoadUseCase.findById(new ClientLoadRequest(id));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
