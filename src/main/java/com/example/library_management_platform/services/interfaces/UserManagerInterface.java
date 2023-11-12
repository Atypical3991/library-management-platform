package com.example.library_management_platform.services.interfaces;

//UserManagerInterface :- An interface for classes responsible for User management for e.g.  BorrowerLoginLogoutService
public interface UserManagerInterface<T, K, L, N> {
    Boolean createUser(K user);

    Boolean updateUser(L user);

    Boolean removeUser(T t);

    N getUserById(T t, String authToken);
}
