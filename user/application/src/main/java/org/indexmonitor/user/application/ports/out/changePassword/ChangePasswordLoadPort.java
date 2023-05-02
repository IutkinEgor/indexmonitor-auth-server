package org.indexmonitor.user.application.ports.out.changePassword;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.aggregates.ChangePassword;
import org.indexmonitor.user.domain.aggregates.ResetPassword;

import java.util.Optional;

public interface ChangePasswordLoadPort extends Port {

    Optional<ChangePassword> findById(BaseId id);

    Optional<ChangePassword> findByUserId(BaseId userId);

    Optional<ChangePassword> findByToken(String token);

}
