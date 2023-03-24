package org.indexmonitor.client.application.ports.out.scope;

import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;

import java.util.Optional;
import java.util.Set;

public interface ScopeLoadPort extends Port {
    Set<Scope> findAll(Integer offset, Integer limit);
    Optional<Scope> findById(BaseId id);
    Optional<Scope> findByName(String name);
    Boolean isExist(Scope scope);
    Boolean isExistMoreThanOne(Scope scope);
    Boolean isExistById(BaseId id);
}
