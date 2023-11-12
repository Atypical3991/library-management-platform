package com.example.library_management_platform.services.interfaces;

import java.awt.print.Pageable;
import java.util.List;

public interface ItemManagerInterface<T, K, L, M, N> {
    List<K> getAllItems(Pageable pageable);

    L getItemById(T t);

    Boolean addItem(N itemModel);

    Boolean removeItem(T t);

    Boolean updateItem(M itemUpdateModel, T id);
}
