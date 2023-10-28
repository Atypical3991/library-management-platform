package com.example.library_management_platform.services;

import com.example.library_management_platform.models.services.absract.CreateIssuanceModel;
import com.example.library_management_platform.models.services.absract.UpdateIssuanceModel;
import com.example.library_management_platform.services.interfaces.IssuanceManagerInterface;

public class IssuanceManagerService implements IssuanceManagerInterface<Long> {
    @Override
    public Boolean createIssuance(CreateIssuanceModel issuance) {
        return null;
    }

    @Override
    public Boolean updateIssuance(UpdateIssuanceModel issuance) {
        return null;
    }

    @Override
    public Boolean deleteIssuance(Long aLong) {
        return null;
    }
}
