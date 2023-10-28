package com.example.library_management_platform.services.interfaces;

import com.example.library_management_platform.models.services.absract.CreateItemModel;
import com.example.library_management_platform.models.services.absract.ItemModel;
import com.example.library_management_platform.models.services.absract.ItemSearchModel;
import com.example.library_management_platform.models.services.absract.UpdateItemModel;

import java.util.List;
import java.util.Objects;

public interface ItemManagerInterface<T,K,L,M,N,O>{
    List<K> getAllItems(O itemSearchModel);
    List<K> getAllItemsWithoutSearchCriteria();

    L getItemById(T t);
    Boolean addItem( N itemModel);
    Boolean removeItem(T t);
    Boolean updateItem(M itemUpdateModel, T id);
}
