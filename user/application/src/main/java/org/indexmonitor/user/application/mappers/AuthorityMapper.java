package org.indexmonitor.user.application.mappers;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityRegisterRequest;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthoritySettingsUpdateRequest;
import org.indexmonitor.user.domain.aggregates.Authority;

public interface AuthorityMapper {

    Authority mapRegisterRequest(AuthorityRegisterRequest request, BaseId authorityId);
    Authority mapUpdateRequest(Authority authority, AuthoritySettingsUpdateRequest request);
}
