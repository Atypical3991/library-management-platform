package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.AddLibraryManagerRequestModelToLibraryManagerEntityConvertor;
import com.example.library_management_platform.convertors.LibraryManagerEntityModelToGetLibraryManagerByIdResponseDataObjConvertor;
import com.example.library_management_platform.models.api.request.AddLibraryManagerRequestModel;
import com.example.library_management_platform.models.api.response.GetLibraryManagerByIdResponseModel;
import com.example.library_management_platform.models.entities.LibraryManager;
import com.example.library_management_platform.models.entities.User;
import com.example.library_management_platform.repositories.LibraryManagerRepository;
import com.example.library_management_platform.services.interfaces.UserManagerInterface;
import com.example.library_management_platform.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
public class LibraryManagerService implements UserManagerInterface<Long, AddLibraryManagerRequestModel, Object, GetLibraryManagerByIdResponseModel.LibraryManagerByIdDetailsData> {

    @Autowired
    AddLibraryManagerRequestModelToLibraryManagerEntityConvertor addLibraryManagerRequestModelToLibraryManagerEntityConvertor;

    @Autowired
    LibraryManagerEntityModelToGetLibraryManagerByIdResponseDataObjConvertor libraryManagerEntityModelToGetLibraryManagerByIdResponseDataObjConvertor;

    @Autowired
    LibraryManagerRepository libraryManagerRepository;

    @Override
    public Boolean createUser(AddLibraryManagerRequestModel addLibraryManagerRequestModel) {
        try {
            LibraryManager libraryManager = addLibraryManagerRequestModelToLibraryManagerEntityConvertor.convert(addLibraryManagerRequestModel);
            libraryManagerRepository.save(libraryManager);
            return true;
        } catch (Exception e) {
            log.error("LibraryManagerService, createUser exception raised!! payload:{}", addLibraryManagerRequestModel, e);
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
    public GetLibraryManagerByIdResponseModel.LibraryManagerByIdDetailsData getUserById(Long id, String authToken) {
        try {
            Optional<LibraryManager> libraryManager = libraryManagerRepository.findById(id);
            if (libraryManager.isEmpty()) {
                throw new RuntimeException("Library manager doesn't exist.");
            }
            Map<String, Object> claims = JwtTokenUtil.parseJwt(authToken);
            if (claims.get("role") == null || (User.RoleEnum) claims.get("role") != User.RoleEnum.library_manager || claims.get("username") != libraryManager.get().getUsername()) {
                throw new RuntimeException("User un-authorized!!");
            }
            return libraryManagerEntityModelToGetLibraryManagerByIdResponseDataObjConvertor.convert(libraryManager.get());
        } catch (Exception e) {
            log.error("LibraryManagerService, getUserById exception raised!! id: {}", id, e);
            return null;
        }
    }
}
