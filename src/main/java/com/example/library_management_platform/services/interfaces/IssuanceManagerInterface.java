package com.example.library_management_platform.services.interfaces;


import java.util.List;

//IssuanceManagerInterface :- An interface for classes responsible for Issuance management for e.g.  BookIssuanceManagerService
public interface IssuanceManagerInterface<T, K, M, O, N, P> {
    P createIssuance(K issuance);

    Boolean updateIssuance(M issuance);

    Boolean deleteIssuance(T t);

    List<O> getAllIssuance(N n);

}
