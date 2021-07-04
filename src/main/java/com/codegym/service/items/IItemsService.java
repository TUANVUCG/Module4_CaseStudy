package com.codegym.service.items;

import com.codegym.model.Items;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IItemsService extends IGeneralService<Items> {
    Page<Items> findAll(Pageable pageable);
    Page<Items> findItemsByCart(Long cartId, Pageable pageable);
}
