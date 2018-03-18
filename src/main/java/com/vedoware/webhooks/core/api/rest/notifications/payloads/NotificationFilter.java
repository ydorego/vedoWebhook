package com.vedoware.webhooks.core.api.rest.notifications.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class NotificationFilter {

    @NotNull(message = "Matching filter can not be null")
    private List<FILTER_ITEM> matchingFilter;

    public NotificationFilter() {
        matchingFilter = new ArrayList<FILTER_ITEM>();
    }

    public enum FILTER_ITEM {

        VNF_, VNF_CREATED, VNF_INSTANTIATED, VNF_UPDATED, VNF_DELETED, VNF_SCALED_OUT, VNF_SCALED_IN, VNF_REBOOTED, VNF_RESTARTED, VNF_HEALED, VNF_TERMINATED,

        VNF_VM_, VNF_VM_CREATED, VNF_VM_STARTED, VNF_VM_REBOOTED, VNF_VM_OVERLOADED, VNF_VM_UNDERLOADED, VNF_VM_TERMINATED

    }

    public List<FILTER_ITEM> getMatchingFilter() {
        return matchingFilter;
    }

    public void setMatchingFilter(List<FILTER_ITEM> matchingFilter) {
        this.matchingFilter = matchingFilter;
    }

    public void addMatchingFilter(FILTER_ITEM filterEntry) {

        if (filterEntry != null) {
            if (!matchingFilter.contains(filterEntry)) {
                matchingFilter.add(filterEntry);
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matchingFilter == null) ? 0 : matchingFilter.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NotificationFilter other = (NotificationFilter) obj;
        if (matchingFilter == null) {
            if (other.matchingFilter != null)
                return false;
        } else if (!matchingFilter.equals(other.matchingFilter))
            return false;
        return true;
    }

}
