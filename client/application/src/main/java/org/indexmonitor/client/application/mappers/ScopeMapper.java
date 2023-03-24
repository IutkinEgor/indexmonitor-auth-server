package org.indexmonitor.client.application.mappers;

import org.indexmonitor.client.application.ports.in.scope.requests.ScopeRegisterRequest;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeUpdateRequest;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.valueObjects.BaseId;

public interface ScopeMapper {
    Scope mapRegisterRequest(ScopeRegisterRequest request, BaseId id);
    Scope mapUpdateRequest(Scope scope, ScopeUpdateRequest request);
}
