package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.response.GetBorrowerDetailsResponseModel;
import com.example.library_management_platform.models.entities.Borrower;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BorrowerEntityModelToBorrowerDetailsResponseDataObjConvertor implements Converter<Borrower, GetBorrowerDetailsResponseModel.BorrowerDetails> {
    @Override
    public GetBorrowerDetailsResponseModel.BorrowerDetails convert(Borrower source) {
        return new GetBorrowerDetailsResponseModel.BorrowerDetails(
                source.getContactEmail(),
                source.getContactNumber(),
                source.getUsername(),
                source.getFirstName(),
                source.getLastName()
        );
    }
}
