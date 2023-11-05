package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.response.GetLibraryManagerByIdResponseModel;
import com.example.library_management_platform.models.entities.LibraryManager;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LibraryManagerEntityModelToGetLibraryManagerByIdResponseDataObjConvertor implements Converter<LibraryManager, GetLibraryManagerByIdResponseModel.DataObj> {

    @Override
    public GetLibraryManagerByIdResponseModel.DataObj convert(LibraryManager source) {
        return new GetLibraryManagerByIdResponseModel.DataObj(
                source.getUsername(),
                source.getFirstName(),
                source.getLastName(),
                source.getContactEmail(),
                source.getContactNumber()
        );
    }
}
