//package org.indexmonitor.user.adapter.in.api.controllers;
//
//import org.indexmonitor.common.domain.valueObjects.BaseResponse;
//import org.indexmonitor.user.application.ports.in.user.UserSettingsLoadUseCase;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/users")
//@AllArgsConstructor
//public class UserAccountController {
//    private final UserSettingsLoadUseCase userSettingsLoadUseCase;
//
//    @GetMapping("id/{id}")
//    public ResponseEntity<BaseResponse> findById(@PathVariable UUID id){
//        //User account = queryUserAccountPort.findByUserId(id);
//
//        //command.constraintViolations();
//        return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
//    }
//
//    @GetMapping("username/{username}")
//    public ResponseEntity<BaseResponse> findByUserName(@PathVariable String username){
//        //User account = queryUserAccountPort.findByUserName(username);
//
//        //command.constraintViolations();
//        return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
//    }
//
////    @GetMapping("email/{email}")
////    public ResponseEntity<BaseResponse> findByEmail(@PathVariable String email){
////       // Optional<User> account = queryUserAccountPort.findByEmail(email);
////
////        //command.constraintViolations();
////        return new ResponseEntity<>(new BaseResponse<User>(account.get()), HttpStatus.OK);
////    }
//}
