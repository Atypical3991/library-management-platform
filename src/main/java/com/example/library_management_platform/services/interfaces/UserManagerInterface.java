package com.example.library_management_platform.services.interfaces;

import com.example.library_management_platform.models.services.absract.CreateUserModel;
import com.example.library_management_platform.models.services.absract.IssuanceItemsModel;
import com.example.library_management_platform.models.services.absract.UpdateUserModel;

import java.util.List;

public interface UserManagerInterface<T> {
    Boolean createUser(CreateUserModel user);
    Boolean updateUser(UpdateUserModel user);
    Boolean removeUser(T t);
    List<IssuanceItemsModel> getMyAllIssuanceItems(T t);
}
