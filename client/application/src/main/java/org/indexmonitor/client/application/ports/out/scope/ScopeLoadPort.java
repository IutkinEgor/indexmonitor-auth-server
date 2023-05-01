package org.indexmonitor.client.application.ports.out.scope;

import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ScopeLoadPort extends Port {
    BasePage<Scope> findAll(Integer page, Integer size);
    Optional<Scope> findById(BaseId id);
    Optional<Scope> findByName(String name);
    Boolean isExist(Scope scope);
    Boolean isExistMoreThanOne(Scope scope);
    Boolean isExistById(BaseId id);
}
