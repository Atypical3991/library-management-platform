package com.example.library_management_platform.services.interfaces;

import com.example.library_management_platform.models.api.response.BaseResponse;

public interface LoginInterface<T> {

    String login(T t);
}
