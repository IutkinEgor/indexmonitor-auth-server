package org.indexmonitor.user.application.mappers;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterManualRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterRequest;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.Password;
import org.indexmonitor.user.domain.valueObjects.Recovery;


public interface UserMapper {

    User mapRegisterRequest(UserRegisterRequest request, Password password, Recovery recovery, Boolean isEnable);
    UserRegisterRequest mapRegisterRequest(User user);

    User mapRegisterManualRequest(UserRegisterManualRequest request, BaseId userId, Password password, Recovery recovery);

}
