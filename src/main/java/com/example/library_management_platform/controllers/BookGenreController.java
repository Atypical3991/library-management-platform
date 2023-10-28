package com.example.library_management_platform.controllers;

import com.example.library_management_platform.models.api.request.AddBookGenre;
import com.example.library_management_platform.models.api.request.UpdateBookGenreRequestModel;
import com.example.library_management_platform.models.api.response.BaseResponse;
import com.example.library_management_platform.models.api.response.GetAllBookGenresResponse;
import com.example.library_management_platform.models.api.response.GetBookGenreById;
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
    public BaseResponse addGenre(@RequestBody @Valid AddBookGenre addBookGenrePayload, BindingResult result){
        if(result.hasErrors()){
          List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
          return  new BaseResponse(false,String.join(", ",errors),"");
        }
        try{
            Boolean success = bookGenreManagerService.addItem(addBookGenrePayload);
            if(success){
                return new BaseResponse(true,null,"Genre added successfully.");
            }else{
                return new BaseResponse(true,null,"Genre addition failed.");
            }
        }catch (Exception e){
            log.error("GenreController, addGenre exception raised!! payload : {}", addBookGenrePayload,e);
            return new BaseResponse(false,"something went wrong",null);
        }
    }

    @GetMapping("")
    public BaseResponse getAllGenres(){
        try{
            List<GetAllBookGenresResponse.GenreObj>  genresObjList = bookGenreManagerService.getAllItemsWithoutSearchCriteria();
            return new GetAllBookGenresResponse(true,null,"Genres fetched successfully.", new GetAllBookGenresResponse.DataObj(genresObjList));
        }catch (Exception e){
            log.error("GenreController, getAllGenres exception raised!!",e);
            return new BaseResponse(false,"something went wrong",null);
        }
    }

    @GetMapping("/{genreId}")
    public BaseResponse getGenreByID(@PathVariable long genreId){
        try{
            GetBookGenreById.GenreDetails genreDetails  = bookGenreManagerService.getItemById(genreId);
            return new GetBookGenreById(true,null,"Genre Details fetched successfully.", new GetBookGenreById.DataObj(genreDetails));
        }catch (Exception e){
            log.error("GenreController, getGenreByID exception raised!!",e);
            return new BaseResponse(false,"something went wrong",null);
        }
    }

    @DeleteMapping("/{genreId}")
    public BaseResponse removeGenreById(@PathVariable long genreId){
        try{
            Boolean success =  bookGenreManagerService.removeItem(genreId);
            return  new BaseResponse(success,"Genre removed successfully", null);
        }catch (Exception e){
            return  new BaseResponse(false,"something went wrong", null);
        }
    }

    @PutMapping("/{genreId}")
    public BaseResponse removeGenreById(@PathVariable long genreId, @RequestBody UpdateBookGenreRequestModel payload){
        try{
            Boolean success = bookGenreManagerService.updateItem(payload,genreId);
            return  new BaseResponse(success,"Genre updated successfully", null);
        }catch (Exception e){
            return  new BaseResponse(false,"something went wrong", null);
        }
    }



}
