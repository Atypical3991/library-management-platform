package com.example.library_management_platform.services.interfaces;

public interface LoginLogoutInterface<T> {

    String login(T t);

    String logout(Long id);
}
