package com.example.library_management_platform.services;

import com.example.library_management_platform.models.api.request.CreateMembershipRequestModel;
import com.example.library_management_platform.models.api.request.RenewMembershipRequestModel;
import com.example.library_management_platform.models.entities.Borrower;
import com.example.library_management_platform.models.entities.LibraryMembership;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.repositories.LibraryMembershipRepository;
import com.example.library_management_platform.services.interfaces.MembershipManagerInterface;
import com.example.library_management_platform.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class LibraryMembershipService implements MembershipManagerInterface<Long, RenewMembershipRequestModel, CreateMembershipRequestModel> {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    LibraryMembershipRepository libraryMembershipRepository;


    @Override
    public Boolean createMembership(CreateMembershipRequestModel createMembershipRequestModel) {
        try {
            Optional<Borrower> borrowerOpt = borrowerRepository.findById(createMembershipRequestModel.getBorrowerId());
            if (borrowerOpt.isEmpty()) {
                log.error("LibraryMembershipService, createMembership, Borrower not found, payload: {}", createMembershipRequestModel);
                return false;
            }
            if (borrowerOpt.get().getLibraryMembership() != null) {
                log.error("LibraryMembershipService, createMembership, Membership already created, payload: {}", createMembershipRequestModel);
                return false;
            }
            LibraryMembership libraryMembership = new LibraryMembership();
            libraryMembership.setBorrower(borrowerOpt.get());
            libraryMembership.setStatus(LibraryMembership.StatusEnum.ACTIVE);
            libraryMembership.setIssueDate(DateUtil.convertToDate(createMembershipRequestModel.getIssuedAt()));
            libraryMembership.setExpiryDate(DateUtil.convertToDate(createMembershipRequestModel.getExpiredAt()));
            libraryMembershipRepository.save(libraryMembership);
            borrowerOpt.get().setLibraryMembership(libraryMembership);
            borrowerRepository.save(borrowerOpt.get());
            return true;

        } catch (Exception e) {
            log.error("LibraryMembershipService, createMembership, exception raised!! payload: {}", createMembershipRequestModel, e);
            return false;
        }
    }

    @Override
    public Boolean cancelMembership(Long membershipId) {
        Optional<LibraryMembership> membershipOpt = libraryMembershipRepository.findById(membershipId);
        if (membershipOpt.isEmpty()) {
            log.error("LibraryMembershipService, cancelMembership, Membership not found!! id: {}", membershipId);
        }
        membershipOpt.get().setStatus(LibraryMembership.StatusEnum.IN_ACTIVE);
        libraryMembershipRepository.save(membershipOpt.get());
        return true;
    }

    @Override
    public Boolean renewMembership(RenewMembershipRequestModel renewMembershipRequestModel) {
        try {
            Optional<LibraryMembership> membershipOpt = libraryMembershipRepository.findById(renewMembershipRequestModel.getMembershipId());
            if (membershipOpt.isEmpty()) {
                log.error("LibraryMembershipService, renewMembership, Membership not found, payload: {}", renewMembershipRequestModel);
                return false;
            }
            membershipOpt.get().setExpiryDate(DateUtil.convertToDate(renewMembershipRequestModel.getExpiredAt()));
            membershipOpt.get().setIssueDate(DateUtil.convertToDate(renewMembershipRequestModel.getIssuedAt()));
            membershipOpt.get().setStatus(LibraryMembership.StatusEnum.ACTIVE);
            libraryMembershipRepository.save(membershipOpt.get());
            return true;
        } catch (Exception e) {
            log.error("LibraryMembershipService, renewMembership exception raised!! payload :{}", renewMembershipRequestModel, e);
            return false;
        }
    }
}
