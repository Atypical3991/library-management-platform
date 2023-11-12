package com.example.library_management_platform.services.interfaces;

//LoginLogoutInterface :- An interface for classes responsible for Login & Logout functionality for e.g.  BorrowerLoginLogoutService
public interface LoginLogoutInterface<T> {

    String login(T t);

    String logout(Long id);
}
