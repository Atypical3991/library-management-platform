package com.example.library_management_platform.services.interfaces;

import com.example.library_management_platform.models.services.CreateIssuanceModel;
import com.example.library_management_platform.models.services.UpdateIssuanceModel;

public interface IssuanceManagerInterface<T> {
    Boolean createIssuance(CreateIssuanceModel issuance);
    Boolean updateIssuance(UpdateIssuanceModel issuance);
    Boolean deleteIssuance(T t);
}
