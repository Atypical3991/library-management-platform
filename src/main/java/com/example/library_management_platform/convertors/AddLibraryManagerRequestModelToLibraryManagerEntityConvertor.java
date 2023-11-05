package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.request.AddLibraryManagerRequestModel;
import com.example.library_management_platform.models.entities.LibraryManager;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddLibraryManagerRequestModelToLibraryManagerEntityConvertor implements Converter<AddLibraryManagerRequestModel, LibraryManager> {
    @Override
    public LibraryManager convert(AddLibraryManagerRequestModel source) {
        LibraryManager libraryManager = new LibraryManager();
        libraryManager.setUsername(source.getUserName());
        libraryManager.setPassword(source.getPassword());
        libraryManager.setFirstName(source.getFirstName());
        libraryManager.setLastName(source.getLastName());
        libraryManager.setContactEmail(source.getContactEmail());
        libraryManager.setContactNumber(source.getContactNumber());
        return libraryManager;
    }
}
