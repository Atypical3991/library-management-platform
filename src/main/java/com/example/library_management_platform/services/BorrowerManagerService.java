package com.example.library_management_platform.services;

import com.example.library_management_platform.models.services.absract.CreateUserModel;
import com.example.library_management_platform.models.services.absract.IssuanceItemsModel;
import com.example.library_management_platform.models.services.absract.UpdateUserModel;
import com.example.library_management_platform.services.interfaces.UserManagerInterface;

import java.util.List;

public class BorrowerManagerService implements UserManagerInterface<Long> {
    @Override
    public Boolean createUser(CreateUserModel user) {
        return null;
    }

    @Override
    public Boolean updateUser(UpdateUserModel user) {
        return null;
    }

    @Override
    public Boolean removeUser(Long o) {
        return null;
    }

    @Override
    public List<IssuanceItemsModel> getMyAllIssuanceItems(Long aLong) {
        return null;
    }

}
