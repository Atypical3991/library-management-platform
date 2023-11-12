package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.AddBorrowerRequestModelToBorrowerConvertor;
import com.example.library_management_platform.convertors.BorrowerToBorrowerDetailsConvertor;
import com.example.library_management_platform.models.api.request.AddBorrowerRequestModel;
import com.example.library_management_platform.models.api.response.GetBorrowerDetailsResponseModel;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.models.entities.User;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.services.interfaces.UserManagerInterface;
import com.example.library_management_platform.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
public class BorrowerManagerService implements UserManagerInterface<Long, AddBorrowerRequestModel, Object, GetBorrowerDetailsResponseModel.BorrowerDetails> {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    AddBorrowerRequestModelToBorrowerConvertor addBorrowerRequestModelToBorrowerConvertor;

    @Autowired
    BorrowerToBorrowerDetailsConvertor borrowerToBorrowerDetailsConvertor;

    @Override
    public Boolean createUser(AddBorrowerRequestModel addBorrowerRequestModel) {
        Borrower borrower = addBorrowerRequestModelToBorrowerConvertor.convert(addBorrowerRequestModel);
        borrowerRepository.save(borrower);
        return true;
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
    public GetBorrowerDetailsResponseModel.BorrowerDetails getUserById(Long borrowerId, String authToken) {
        Optional<Borrower> borrower = borrowerRepository.findById(borrowerId);
        if (borrower.isEmpty()) {
            return null;
        }
        Map<String, Object> claims = (Map<String, Object>) JwtTokenUtil.parseJwt(authToken);
        if (claims.get("role") == null || (User.RoleEnum) claims.get("role") != User.RoleEnum.borrower || (String) claims.get("username") != borrower.get().getUsername()) {
            throw new RuntimeException("User un-authorised!!");
        }
        return borrowerToBorrowerDetailsConvertor.convert(borrower.get());
    }

}
