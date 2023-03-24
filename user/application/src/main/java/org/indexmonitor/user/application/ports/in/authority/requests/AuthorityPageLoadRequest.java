package org.indexmonitor.user.application.ports.in.authority.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityPageLoadRequest extends SelfValidator<AuthorityPageLoadRequest> {
    public static final int DEFAULT_PAGE_MIN_VALUE = 0;
    public static final int DEFAULT_SIZE_MIN_VALUE = 1;
    public static final int DEFAULT_PAGE_VALUE = 0;
    public static final int DEFAULT_SIZE_VALUE = 20;

    @NotNull(message = "Page can not be NULL.")
    @Min(value = DEFAULT_PAGE_MIN_VALUE, message = "Page minimal value is 0.")
    private Integer page = DEFAULT_PAGE_VALUE;
    @NotNull(message = "Size can not be NULL.")
    @Min(value = DEFAULT_SIZE_MIN_VALUE, message = "Size minimal value is 1.")
    private Integer size = DEFAULT_SIZE_VALUE;

}
