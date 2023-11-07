package com.example.library_management_platform.services;

import com.example.library_management_platform.models.api.request.LoginRequestModel;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.services.interfaces.LoginLogoutInterface;
import com.example.library_management_platform.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class BorrowerLoginLogoutService implements LoginLogoutInterface<LoginRequestModel> {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Override
    public String login(LoginRequestModel loginRequestModel) {
        try{
            Borrower borrower = borrowerRepository.findTopByUsernameAndPassword(loginRequestModel.getUsername(), loginRequestModel.getPassword());
            if(borrower == null) {
                log.error("BorrowerLoginService, login borrower not found. payload: {}", loginRequestModel);
                return null;
            }
            Map<String,String> claims = new HashMap<>();
            claims.put("username",borrower.getUsername());
            claims.put("role","user");
            return JwtTokenUtil.generateJwt(claims);
        }catch (Exception e){
            log.error("BorrowerLoginService, login exception raised!! payload: {}", loginRequestModel,e);
            return null;
        }
    }

    @Override
    public String logout(Long id) {
        return null;
    }
}
