package com.example.library_management_platform.services.interfaces;

import com.example.library_management_platform.models.services.absract.CreateIssuanceModel;
import com.example.library_management_platform.models.services.absract.UpdateIssuanceModel;

public interface IssuanceManagerInterface<T> {
    Boolean createIssuance(CreateIssuanceModel issuance);
    Boolean updateIssuance(UpdateIssuanceModel issuance);
    Boolean deleteIssuance(T t);
}
