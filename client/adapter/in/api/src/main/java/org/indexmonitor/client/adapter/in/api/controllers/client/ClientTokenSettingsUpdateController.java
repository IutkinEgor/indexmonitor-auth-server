package org.indexmonitor.client.adapter.in.api.controllers.client;
import org.indexmonitor.client.application.ports.in.client.ClientTokenSettingsUpdateUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateTokenSettingsRequest;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clients/{id}/token-settings")
@AllArgsConstructor
public class ClientTokenSettingsUpdateController {
    private static String[] REQUIRED_ROLES = {"CLIENT-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    private final ClientTokenSettingsUpdateUseCase clientTokenSettingsUpdateUseCase;

    @PutMapping
    public ResponseEntity<BaseResponse> register(@PathVariable(name = "id") String id, @RequestBody ClientUpdateTokenSettingsRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setId(id);
        BaseResponse response = clientTokenSettingsUpdateUseCase.update(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
