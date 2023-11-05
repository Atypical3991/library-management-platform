package com.example.library_management_platform.convertors;

import com.example.library_management_platform.models.api.request.CreateBookIssuanceModel;
import com.example.library_management_platform.models.entities.BookIssuance;
import com.example.library_management_platform.repositories.BorrowerRepository;
import com.example.library_management_platform.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

import java.text.ParseException;

@Component
@Slf4j
public class CreateBookIssuanceModelToBookIssuanceEntityModelConvertor implements Converter<CreateBookIssuanceModel, BookIssuance> {

    @Autowired
    BorrowerRepository borrowerRepository;


    @Override
    public BookIssuance convert(CreateBookIssuanceModel source) {
        try{
            BookIssuance bookIssuance =  new BookIssuance();
            bookIssuance.setBookId(source.getBookId());
            bookIssuance.setStatus(BookIssuance.StatusEnum.ACTIVE);
            Date todayDate = new Date();
            Date startDate = DateUtil.convertToDate(source.getStartDate());
            Date endDate = DateUtil.convertToDate(source.getEndDate());
            if(startDate.before(todayDate) || endDate.before(startDate) || endDate.equals(startDate)){
                throw  new UnsupportedOperationException("Start date should be greater than or equal today's date, and end date should be greater than start date.");
            }
            bookIssuance.setIssuedAt(startDate);
            bookIssuance.setExpiredAt(endDate);
            bookIssuance.setBorrower(borrowerRepository.findById(source.getBorrowerId()).get());
            return bookIssuance;
        }catch (ParseException e){
            log.error("CreateBookIssuanceModelToBookIssuanceEntityModelConvertor, convert failed!! payload: {}",source, e);
            return null;
        }
    }
}
