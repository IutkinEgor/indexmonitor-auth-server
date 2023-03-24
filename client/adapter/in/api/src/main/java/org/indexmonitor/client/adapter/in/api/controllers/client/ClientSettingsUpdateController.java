package org.indexmonitor.client.adapter.in.api.controllers.client;
import org.indexmonitor.client.application.ports.in.client.ClientSettingsUpdateUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateRequest;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clients/{id}")
@AllArgsConstructor
public class ClientSettingsUpdateController {
    private static String[] REQUIRED_ROLES = {"CLIENT-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"UPDATE"};
    private final ClientSettingsUpdateUseCase clientSettingsUpdateUseCase;

    @PutMapping
    public ResponseEntity<BaseResponse> register(@PathVariable(name = "id") String id, @RequestBody ClientUpdateRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        request.setId(id);
        BaseResponse response = clientSettingsUpdateUseCase.update(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }

}
