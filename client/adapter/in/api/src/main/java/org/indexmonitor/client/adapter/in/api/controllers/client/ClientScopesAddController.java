package org.indexmonitor.client.adapter.in.api.controllers.client;

import org.indexmonitor.client.application.ports.in.client.ClientScopeAddUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateScopeRequest;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clients/{id}/scopes")
@AllArgsConstructor
public class ClientScopesAddController {
    private static String[] REQUIRED_ROLES = {"CLIENT-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    private final ClientScopeAddUseCase clientScopeAddUseCase;

    @PutMapping
    public ResponseEntity<BaseResponse> add(@PathVariable(name = "id") String id, @RequestBody ClientUpdateScopeRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setId(id);
        BaseResponse response = clientScopeAddUseCase.add(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
