package com.example.library_management_platform.services.interfaces;

import com.example.library_management_platform.models.services.CreateUserModel;
import com.example.library_management_platform.models.services.UpdateUserModel;

public interface UserManagerInterface<T> {
    Boolean createUser(CreateUserModel user);
    Boolean updateUser(UpdateUserModel user);
    Boolean removeUser(T t);
}
