package org.indexmonitor.user.application.ports.in.user.responses;

import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.domain.aggregates.User;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;


@Getter
@Builder
public class UserPageResponse {
    private String id;
    private String userName;
    private String givenName;
    private String familyName;
    private String email;
    private Long createdAt;

    public static BasePage<UserPageResponse> map(BasePage<User> users){
        return BasePage.<UserPageResponse>builder()
                .elements(users.getElements().stream()
                        .map(user -> UserPageResponse.builder()
                                .id(user.getId().getValueAsString())
                                .userName(user.getUserName())
                                .givenName(user.getProfile().getGivenName())
                                .familyName(user.getProfile().getFamilyName())
                                .email(user.getProfile().getEmail() == null ? null : user.getProfile().getEmail())
                                .createdAt(user.getCreatedAt().toEpochMilli())
                                .build())
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .totalCount(users.getTotalCount())
                .currentPage(users.getCurrentPage())
                .currentSize(users.getCurrentSize())
                .build();
    }
}
