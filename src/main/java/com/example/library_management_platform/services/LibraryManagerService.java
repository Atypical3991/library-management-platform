package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.AddLibraryManagerRequestModelToLibraryManagerEntityConvertor;
import com.example.library_management_platform.convertors.LibraryManagerEntityModelToGetLibraryManagerByIdResponseDataObjConvertor;
import com.example.library_management_platform.models.api.request.AddLibraryManagerRequestModel;
import com.example.library_management_platform.models.api.response.GetLibraryManagerByIdResponseModel;
import com.example.library_management_platform.models.entities.LibraryManager;
import com.example.library_management_platform.repositories.LibraryManagerRepository;
import com.example.library_management_platform.services.interfaces.UserManagerInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class LibraryManagerService implements UserManagerInterface<Long,AddLibraryManagerRequestModel,Object, GetLibraryManagerByIdResponseModel.DataObj> {

    @Autowired
    AddLibraryManagerRequestModelToLibraryManagerEntityConvertor addLibraryManagerRequestModelToLibraryManagerEntityConvertor;

    @Autowired
    LibraryManagerEntityModelToGetLibraryManagerByIdResponseDataObjConvertor libraryManagerEntityModelToGetLibraryManagerByIdResponseDataObjConvertor;

    @Autowired
    LibraryManagerRepository libraryManagerRepository;

    @Override
    public Boolean createUser(AddLibraryManagerRequestModel addLibraryManagerRequestModel) {
        try{
            LibraryManager libraryManager = addLibraryManagerRequestModelToLibraryManagerEntityConvertor.convert(addLibraryManagerRequestModel);
            libraryManagerRepository.save(libraryManager);
            return true;
        }catch (Exception e) {
            log.error("LibraryManagerService, createUser exception raised!! paylaod:{}",addLibraryManagerRequestModel,e);
            return false;
        }
    }

    @Override
    public Boolean updateUser(Object user) {
        return null;
    }

    @Override
    public Boolean removeUser(Long o) {
        return null;
    }

    @Override
    public GetLibraryManagerByIdResponseModel.DataObj getUserById(Long id) {
        try{
            Optional<LibraryManager> libraryManager = libraryManagerRepository.findById(id);
            if(libraryManager.isEmpty()){
                throw  new RuntimeException("Library manager doesn't exist.");
            }
            return libraryManagerEntityModelToGetLibraryManagerByIdResponseDataObjConvertor.convert(libraryManager.get());
        }catch (Exception e) {
            log.error("LibraryManagerService, getUserById exception raised!! id: {}", id, e);
            return null;
        }
    }
}
