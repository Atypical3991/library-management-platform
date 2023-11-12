package com.example.library_management_platform.services.interfaces;


import java.util.List;

public interface IssuanceManagerInterface<T, K, M, O, N, P> {
    P createIssuance(K issuance);

    Boolean updateIssuance(M issuance);

    Boolean deleteIssuance(T t);

    List<O> getAllIssuance(N n);

}
