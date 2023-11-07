package com.example.library_management_platform.services;

import com.example.library_management_platform.models.api.request.LoginRequestModel;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.models.entities.LibraryAdmin;
import com.example.library_management_platform.repositories.LibraryAdminRepository;
import com.example.library_management_platform.services.interfaces.LoginLogoutInterface;
import com.example.library_management_platform.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class LibraryAdminLoginLogoutService implements LoginLogoutInterface<LoginRequestModel> {

    @Autowired
    LibraryAdminRepository libraryAdminRepository;

    @Override
    public String login(LoginRequestModel loginRequestModel) {

        try{
            LibraryAdmin libraryAdmin = libraryAdminRepository.findTopByUsernameAndPassword(loginRequestModel.getUsername(), loginRequestModel.getPassword());
            if(libraryAdmin == null) {
                log.error("LibraryAdminLoginLogoutService, login, library admin not found. payload: {}", loginRequestModel);
                return null;
            }
            Map<String,String> claims = new HashMap<>();
            claims.put("username",libraryAdmin.getUsername());
            claims.put("role","admin");
            return JwtTokenUtil.generateJwt(claims);
        }catch (Exception e){
            log.error("LibraryAdminLoginLogoutService, login, exception raised!! payload: {}", loginRequestModel,e);
            return null;
        }
    }

    @Override
    public String logout(Long id) {
        return null;
    }
}
