package org.indexmonitor.client.adapter.in.api.controllers.client;
import org.indexmonitor.client.application.ports.in.client.ClientRegisterUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientRegisterRequest;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/clients")
@AllArgsConstructor
public class ClientRegisterController {
    private static String[] REQUIRED_ROLES = {"CLIENT-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"CREATE"};
    private final ClientRegisterUseCase clientRegisterUseCase;

    @PostMapping
    public ResponseEntity<BaseResponse> register(@RequestBody ClientRegisterRequest request){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse response = clientRegisterUseCase.register(request);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
