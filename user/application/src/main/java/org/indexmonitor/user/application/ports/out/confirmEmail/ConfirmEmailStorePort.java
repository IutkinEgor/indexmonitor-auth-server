package org.indexmonitor.user.application.ports.out.confirmEmail;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;

public interface ConfirmEmailStorePort extends Port {
    BaseId generateId();
    void store(ConfirmEmail confirmEmail);
}
