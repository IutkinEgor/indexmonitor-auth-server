package org.indexmonitor.client.adapter.in.api.controllers.client;
import org.indexmonitor.client.application.ports.in.client.ClientDeleteUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientDeleteRequest;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/clients/{id}")
@AllArgsConstructor
public class ClientDeleteController {
    private static String[] REQUIRED_ROLES = {"CLIENT-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"DELETE"};
    private final ClientDeleteUseCase clientDeleteUseCase;

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") String id){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse response = clientDeleteUseCase.deleteById(new ClientDeleteRequest(id));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
