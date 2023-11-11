package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.AddBorrowerRequestModelToBorrowerEntityModel;
import com.example.library_management_platform.convertors.BorrowerEntityModelToBorrowerDetailsResponseDataObjConvertor;
import com.example.library_management_platform.models.api.request.AddBorrowerRequestModel;
import com.example.library_management_platform.models.api.response.GetBorrowerDetailsResponseModel;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.services.interfaces.UserManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BorrowerManagerService implements UserManagerInterface<Long, AddBorrowerRequestModel, Object, GetBorrowerDetailsResponseModel.BorrowerDetails> {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    AddBorrowerRequestModelToBorrowerEntityModel addBorrowerRequestModelToBorrowerEntityModel;

    @Autowired
    BorrowerEntityModelToBorrowerDetailsResponseDataObjConvertor borrowerEntityModelToBorrowerDetailsResponseDataObjConvertor;

    @Override
    public Boolean createUser(AddBorrowerRequestModel addBorrowerRequestModel) {
        Borrower borrower = addBorrowerRequestModelToBorrowerEntityModel.convert(addBorrowerRequestModel);
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
    public GetBorrowerDetailsResponseModel.BorrowerDetails getUserById(Long borrowerId) {
        Optional<Borrower> borrower=  borrowerRepository.findById(borrowerId);
        return borrowerEntityModelToBorrowerDetailsResponseDataObjConvertor.convert(borrower.get());
    }

}
