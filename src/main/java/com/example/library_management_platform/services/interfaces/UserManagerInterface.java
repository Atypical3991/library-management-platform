package com.example.library_management_platform.services.interfaces;

public interface UserManagerInterface<T, K, L, N> {
    Boolean createUser(K user);

    Boolean updateUser(L user);

    Boolean removeUser(T t);

    N getUserById(T t, String authToken);
}
