package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.request.AddBorrowerRequest;
import com.example.library_management_platform.models.entities.Borrower;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddBorrowerRequestModelToBorrowerEntityModel implements Converter<AddBorrowerRequest, Borrower> {
    @Override
    public Borrower convert(AddBorrowerRequest source) {
        Borrower borrower =  new Borrower();
        borrower.setFirstName(source.getFirstName());
        borrower.setLastName(source.getLastName());
        borrower.setDob(source.getDob());
        borrower.setAddress(source.getAddress());
        borrower.setGender(source.getGender());
        borrower.setStatus(Borrower.StatusEnum.ACTIVE);
        borrower.setPassword(source.getPassword());
        borrower.setContactEmail(source.getContactEmail());
        borrower.setContactNumber(source.getContactNumber());
        return borrower;
    }
}
