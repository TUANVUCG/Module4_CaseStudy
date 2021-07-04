package com.codegym.service;

import com.codegym.model.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IItemsService extends IGeneralService<Items> {
    Page<Items> findAll(Pageable pageable);
}
