package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.AddBookGenreRequestModel;
import com.example.library_management_platform.models.api.request.UpdateBookGenreRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponseModel;
import com.example.library_management_platform.models.api.response.GetAllBookGenresResponseModel;
import com.example.library_management_platform.models.api.response.GetBookGenreByIdResponseModel;
import com.example.library_management_platform.services.BookGenreManagerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/genres")
@Slf4j
public class BookGenreController {

    @Autowired
    BookGenreManagerService bookGenreManagerService;

    @PostMapping("")
    public BaseResponseModel addGenre(@RequestBody @Valid AddBookGenreRequestModel addBookGenreRequestModelPayload, BindingResult result){
        if(result.hasErrors()){
          List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
          return  new BaseResponseModel(false,String.join(", ",errors),"");
        }
        try{
            Boolean success = bookGenreManagerService.addItem(addBookGenreRequestModelPayload);
            if(success){
                return new BaseResponseModel(true,null,"Genre added successfully.");
            }else{
                return new BaseResponseModel(true,null,"Genre addition failed.");
            }
        }catch (Exception e){
            log.error("GenreController, addGenre exception raised!! payload : {}", addBookGenreRequestModelPayload,e);
            return new BaseResponseModel(false,"something went wrong",null);
        }
    }

    @GetMapping("/get/all")
    public BaseResponseModel getAllGenres(){
        try{
            List<GetAllBookGenresResponseModel.GenreObj>  genresObjList = bookGenreManagerService.getAllItemsWithoutSearchCriteria();
            return new GetAllBookGenresResponseModel(true,null,"Woo hoo!! your genres fetched successfully.", new GetAllBookGenresResponseModel.DataObj(genresObjList));
        }catch (Exception e){
            log.error("GenreController, getAllGenres exception raised!!",e);
            return new GetAllBookGenresResponseModel(false,"Oops!! something went wrong",null,null);
        }
    }

    @GetMapping("/get/{genreId}")
    public BaseResponseModel getGenreByID(@PathVariable long genreId){
        try{
            GetBookGenreByIdResponseModel.GenreDetails genreDetails  = bookGenreManagerService.getItemById(genreId);
            return new GetBookGenreByIdResponseModel(true,null,"Woo hoo!! your genre details fetched successfully.", new GetBookGenreByIdResponseModel.DataObj(genreDetails));
        }catch (Exception e){
            log.error("GenreController, getGenreByID exception raised!!",e);
            return new GetBookGenreByIdResponseModel(false,"Oops!! something went wrong",null,null);
        }
    }

    @DeleteMapping("/delete/{genreId}")
    public BaseResponseModel removeGenreById(@PathVariable long genreId){
        try{
            Boolean success =  bookGenreManagerService.removeItem(genreId);
            return  new BaseResponseModel(success,"Woo hoo!! your genre removed successfully", null);
        }catch (Exception e){
            return  new BaseResponseModel(false,"Oops!! something went wrong", null);
        }
    }

    @PutMapping("/update/{genreId}")
    public BaseResponseModel removeGenreById(@PathVariable long genreId, @RequestBody UpdateBookGenreRequestModel payload){
        try{
            Boolean success = bookGenreManagerService.updateItem(payload,genreId);
            return  new BaseResponseModel(success,"Woo hoo!! your genre updated successfully", null);
        }catch (Exception e){
            return  new BaseResponseModel(false,"Oops!! something went wrong", null);
        }
    }

}
