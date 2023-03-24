package org.indexmonitor.client.adapter.in.api.controllers.client;
import org.indexmonitor.client.application.ports.in.client.ClientScopeRemoveUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateScopeRequest;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clients/{clientId}/scopes/{scopeId}")
@AllArgsConstructor
public class ClientScopesRemoveController {
    private static String[] REQUIRED_ROLES = {"CLIENT-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    ClientScopeRemoveUseCase clientScopeRemoveUseCase;

    @DeleteMapping
    public ResponseEntity<BaseResponse> remove(@PathVariable(name = "clientId") String clientId, @PathVariable(name = "scopeId") String scopeId){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse response = clientScopeRemoveUseCase.remove(new ClientUpdateScopeRequest(clientId, scopeId));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
