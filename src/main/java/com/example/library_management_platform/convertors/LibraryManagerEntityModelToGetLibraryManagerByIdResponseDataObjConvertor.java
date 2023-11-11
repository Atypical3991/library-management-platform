package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.response.GetLibraryManagerByIdResponseModel;
import com.example.library_management_platform.models.entities.LibraryManager;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LibraryManagerEntityModelToGetLibraryManagerByIdResponseDataObjConvertor implements Converter<LibraryManager, GetLibraryManagerByIdResponseModel.LibraryManagerByIdDetailsData> {

    @Override
    public GetLibraryManagerByIdResponseModel.LibraryManagerByIdDetailsData convert(LibraryManager source) {
        return new GetLibraryManagerByIdResponseModel.LibraryManagerByIdDetailsData(
                source.getUsername(),
                source.getFirstName(),
                source.getLastName(),
                source.getContactEmail(),
                source.getContactNumber()
        );
    }
}
