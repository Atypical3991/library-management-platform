package com.example.library_management_platform.services.interfaces;

import com.example.library_management_platform.models.services.CreateItemModel;
import com.example.library_management_platform.models.services.UpdateItemModel;

public interface ItemManagerInterface<T>{
    Boolean addItem(CreateItemModel itemModel);
    Boolean removeItem(T t);
    Boolean updateItem(UpdateItemModel itemModel);
}
