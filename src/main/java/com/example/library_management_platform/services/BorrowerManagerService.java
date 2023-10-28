package com.example.library_management_platform.services;

import com.example.library_management_platform.convertors.AddBorrowerRequestModelToBorrowerEntityModel;
import com.example.library_management_platform.convertors.BorrowerEntityModelToBorrowerDetailsResponseDataObjConvertor;
import com.example.library_management_platform.models.api.request.AddBorrowerRequest;
import com.example.library_management_platform.models.api.response.BorrowerDetailsResponse;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.services.interfaces.UserManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BorrowerManagerService implements UserManagerInterface<Long, AddBorrowerRequest, Object,Object, BorrowerDetailsResponse.DataObj> {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    AddBorrowerRequestModelToBorrowerEntityModel addBorrowerRequestModelToBorrowerEntityModel;

    @Autowired
    BorrowerEntityModelToBorrowerDetailsResponseDataObjConvertor borrowerEntityModelToBorrowerDetailsResponseDataObjConvertor;

    @Override
    public Boolean createUser(AddBorrowerRequest addBorrowerRequest) {
        Borrower borrower = addBorrowerRequestModelToBorrowerEntityModel.convert(addBorrowerRequest);
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
    public List<Object> getMyAllIssuanceItems(Long aLong) {
        return null;
    }

    @Override
    public  BorrowerDetailsResponse.DataObj getUserById(Long borrowerId) {
        Optional<Borrower> borrower=  borrowerRepository.findById(borrowerId);
        return borrowerEntityModelToBorrowerDetailsResponseDataObjConvertor.convert(borrower.get());
    }

}
