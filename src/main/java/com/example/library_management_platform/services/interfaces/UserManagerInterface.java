package com.example.library_management_platform.services.interfaces;

import java.util.List;

public interface UserManagerInterface<T,K,L,M,N> {
    Boolean createUser(K user);
    Boolean updateUser(L user);
    Boolean removeUser(T t);
    List<M> getMyAllIssuanceItems(T t);

    N getUserById(T t);
}
