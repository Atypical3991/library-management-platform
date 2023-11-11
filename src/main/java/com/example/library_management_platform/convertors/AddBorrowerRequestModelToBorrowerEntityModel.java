package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.request.AddBorrowerRequestModel;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.models.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddBorrowerRequestModelToBorrowerEntityModel implements Converter<AddBorrowerRequestModel, Borrower> {
    @Override
    public Borrower convert(AddBorrowerRequestModel source) {
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
        borrower.setUsername(source.getUserName());
        borrower.setRole(User.RoleEnum.borrower);
        return borrower;
    }
}
