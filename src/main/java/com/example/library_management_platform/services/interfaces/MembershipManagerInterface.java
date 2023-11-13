package com.example.library_management_platform.services.interfaces;

public interface MembershipManagerInterface<T, M, N> {

    Boolean createMembership(N createMembershipRequestModel);

    Boolean cancelMembership(T id);

    Boolean renewMembership(M renewMembershipRequestModel);
}
