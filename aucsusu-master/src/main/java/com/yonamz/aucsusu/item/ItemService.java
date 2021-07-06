package com.yonamz.aucsusu.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    //상품등록 서비스
    @Transactional
    public void register(Item item) throws Exception {
        itemRepository.save(item);
    }
}
