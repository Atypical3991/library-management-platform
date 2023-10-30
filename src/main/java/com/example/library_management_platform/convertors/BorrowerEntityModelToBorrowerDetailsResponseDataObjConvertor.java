package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.response.BorrowerDetailsResponse;
import com.example.library_management_platform.models.entities.Borrower;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BorrowerEntityModelToBorrowerDetailsResponseDataObjConvertor implements Converter<Borrower, BorrowerDetailsResponse.DataObj> {
    @Override
    public BorrowerDetailsResponse.DataObj convert(Borrower source) {
        return new BorrowerDetailsResponse.DataObj(
                source.getContactEmail(),
                source.getContactNumber(),
                source.getUsername(),
                source.getFirstName(),
                source.getLastName()
        );
    }
}
