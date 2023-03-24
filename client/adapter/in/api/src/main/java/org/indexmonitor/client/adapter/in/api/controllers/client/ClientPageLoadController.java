package org.indexmonitor.client.adapter.in.api.controllers.client;

import org.indexmonitor.client.application.ports.in.client.ClientPageLoadUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientLoadPageRequest;
import org.indexmonitor.client.application.ports.in.client.responses.ClientPageModelResponse;
import org.indexmonitor.common.adapter.in.api.utils.AccessControlUtil;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/clients")
@AllArgsConstructor
public class ClientPageLoadController {

    private static String[] REQUIRED_ROLES = {"CLIENT-MANAGER"};
    private static String[] REQUIRED_AUTHORITIES = {"READ"};
    private final ClientPageLoadUseCase clientPageLoadUseCase;

    @GetMapping
    public ResponseEntity<BaseResponse<Set<ClientPageModelResponse>>> findAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size){
        AccessControlUtil.builder().hasAllRoles(REQUIRED_ROLES).hasAllAuthorities(REQUIRED_AUTHORITIES).validate();
        BaseResponse<Set<ClientPageModelResponse>> response = clientPageLoadUseCase.load(new ClientLoadPageRequest(
                page != null ? page : ClientLoadPageRequest.DEFAULT_PAGE_VALUE,
                size != null ? size :  ClientLoadPageRequest.DEFAULT_SIZE_VALUE));
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
