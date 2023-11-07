package com.example.library_management_platform.services;

import com.example.library_management_platform.models.api.request.LoginRequestModel;
import com.example.library_management_platform.models.entities.LibraryManager;
import com.example.library_management_platform.repositories.LibraryManagerRepository;
import com.example.library_management_platform.services.interfaces.LoginLogoutInterface;
import com.example.library_management_platform.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LibraryManagerLoginLogoutService implements LoginLogoutInterface<LoginRequestModel> {

    @Autowired
    LibraryManagerRepository libraryManagerRepository;

    @Override
    public String login(LoginRequestModel loginRequestModel) {
        try{
            LibraryManager libraryManager = libraryManagerRepository.findTopByUsernameAndPassword(loginRequestModel.getUsername(), loginRequestModel.getPassword());
            if(libraryManager == null) {
                log.error("LibraryManagerLoginLogoutService, login library_manager not found. payload: {}", loginRequestModel);
                return null;
            }
            Map<String,String> claims = new HashMap<>();
            claims.put("username",libraryManager.getUsername());
            claims.put("role","library_manager");
            return JwtTokenUtil.generateJwt(claims);
        }catch (Exception e){
            log.error("LibraryManagerLoginLogoutService, login exception raised!! payload: {}", loginRequestModel,e);
            return null;
        }
    }

    @Override
    public String logout(Long id) {
        return null;
    }
}
