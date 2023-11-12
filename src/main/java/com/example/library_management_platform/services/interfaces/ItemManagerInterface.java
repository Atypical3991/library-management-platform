package com.example.library_management_platform.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemManagerInterface<T, K, L, M, N> {
    Page<K> getAllItems(Pageable pageable);

    L getItemById(T t);

    Boolean addItem(N itemModel);

    Boolean removeItem(T t);

    Boolean updateItem(M itemUpdateModel, T id);
}
