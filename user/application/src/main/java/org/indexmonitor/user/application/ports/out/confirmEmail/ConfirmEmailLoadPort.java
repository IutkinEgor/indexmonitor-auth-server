package org.indexmonitor.user.application.ports.out.confirmEmail;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;

import java.util.Optional;

public interface ConfirmEmailLoadPort extends Port {

    Optional<ConfirmEmail> findById(BaseId id);

    Optional<ConfirmEmail> findByUserId(BaseId id);

    Optional<ConfirmEmail> findByToken(String token);

}
