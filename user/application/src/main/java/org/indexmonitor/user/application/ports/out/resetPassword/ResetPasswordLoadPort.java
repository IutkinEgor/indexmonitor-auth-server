package org.indexmonitor.user.application.ports.out.resetPassword;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.aggregates.ResetPassword;

import java.util.Optional;

public interface ResetPasswordLoadPort extends Port {

    Optional<ResetPassword> findById(BaseId id);

    Optional<ResetPassword> findByUserId(BaseId userId);

    Optional<ResetPassword> findByToken(String token);

}
