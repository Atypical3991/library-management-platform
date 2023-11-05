package com.example.library_management_platform.services;

import com.example.library_management_platform.models.api.request.BorrowerLoginRequestModel;
import com.example.library_management_platform.models.component.UserDetails;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.services.interfaces.LoginInterface;
import com.example.library_management_platform.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@Slf4j
public class BorrowerLoginService implements LoginInterface<BorrowerLoginRequestModel> {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(BorrowerLoginRequestModel borrowerLoginRequestModel) {
        try{
            Borrower borrower = borrowerRepository.findTopByUsernameAndPassword(borrowerLoginRequestModel.getUsername(),borrowerLoginRequestModel.getPassword());
            if(borrower == null) {
                log.error("BorrowerLoginService, login borrower not found. payload: {}", borrowerLoginRequestModel);
                return null;
            }
            return jwtTokenUtil.generateJwt(borrower.getUsername());
        }catch (Exception e){
            log.error("BorrowerLoginService, login exception raised!! payload: {}",borrowerLoginRequestModel,e);
            return null;
        }
    }
}
