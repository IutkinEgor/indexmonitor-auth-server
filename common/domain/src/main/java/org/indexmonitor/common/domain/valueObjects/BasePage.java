package org.indexmonitor.common.domain.valueObjects;
import java.util.Collection;

public class BasePage<MODEL>  {
    private Collection<MODEL> elements;
    private Long totalCount;
    private Integer currentPage;
    private Integer currentSize;
    private BasePage(Builder builder) {
        elements = builder.elements;
        totalCount = builder.totalCount;
        currentPage = builder.currentPage;
        currentSize = builder.currentSize;
    }
    public Collection<MODEL> getElements() {
        return elements;
    }
    public Long getTotalCount() {
        return totalCount;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder<MODEL> {
        private Collection<MODEL> elements;
        private Long totalCount;
        private Integer currentPage;
        private Integer currentSize;

        private Builder() {
        }
        public Builder<MODEL> elements(Collection<MODEL> val) {
            elements = val;
            return this;
        }
        public Builder<MODEL> totalCount(Long val) {
            totalCount = val;
            return this;
        }
        public Builder<MODEL> currentPage(Integer val) {
            currentPage = val;
            return this;
        }
        public Builder<MODEL> currentSize(Integer val) {
            currentSize = val;
            return this;
        }
        public BasePage<MODEL> build() {
            return new BasePage(this);
        }
    }
}
