package com.yonamz.aucsusu.item;

import com.yonamz.aucsusu.File.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Long create(ItemForm itemForm,String sessionUser){
        return itemRepository.save(itemForm.toEntity(sessionUser)).getItem_no();
    }

    public String getWriter(Long itemNo) {
        return itemRepository.findByItem_no(itemNo).getWriter();
    }

    @Transactional
    public List<ItemForm> getItemList(){
        List<Item> items = itemRepository.findAll();
        List<ItemForm> itemForms = new ArrayList<>();

        for(Item item : items){
            ItemForm itemForm = ItemForm.builder()
                    .item_no(item.getItem_no())
                    .title(item.getTitle())
                    .content(item.getContent())
                    .writer(item.getWriter())
                    .deadline(item.getDeadline())
                    .starting_bid(item.getStarting_bid())
                    .winning_bid(item.getWinning_bid())
                    .reg_date(item.getReg_date())
                    .fileName(item.getFileName())
                    .build();

            itemForms.add(itemForm);
        }
        return itemForms;
    }

    @Transactional
    public List<ItemForm> getBiddingHistory(String uid) {
        List<Item> items = itemRepository.findBiddingItemsByUid(uid); //경매참여내역
        List<ItemForm> itemForms = new ArrayList<>();
        for(Item item : items){
            ItemForm itemForm = ItemForm.builder()
                    .item_no(item.getItem_no())
                    .title(item.getTitle())
                    .content(item.getContent())
                    .writer(item.getWriter())
                    .deadline(item.getDeadline())
                    .starting_bid(item.getStarting_bid())
                    .winning_bid(item.getWinning_bid())
                    .reg_date(item.getReg_date())
                    .fileName(item.getFileName())
                    .build();

            itemForms.add(itemForm);
        }
        return itemForms;
    }

    @Transactional
    public List<ItemForm> getItemHistory(String uid) {
        List<Item> items = itemRepository.findItemsByUid(uid); //물품등록내역
        List<ItemForm> itemForms = new ArrayList<>();
        for(Item item : items){
            ItemForm itemForm = ItemForm.builder()
                    .item_no(item.getItem_no())
                    .title(item.getTitle())
                    .content(item.getContent())
                    .writer(item.getWriter())
                    .deadline(item.getDeadline())
                    .starting_bid(item.getStarting_bid())
                    .winning_bid(item.getWinning_bid())
                    .reg_date(item.getReg_date())
                    .fileName(item.getFileName())
                    .build();

            itemForms.add(itemForm);
        }
        return itemForms;
    }

    @Transactional
    public ItemForm getPost(Long item_no,String sessionUser){

        Optional<Item> itemWrapper = itemRepository.findById(item_no);
        Item item = itemWrapper.get();

        ItemForm itemForm = ItemForm.builder()
                .item_no(item.getItem_no())
                .title(item.getTitle())
                .writer(sessionUser)
                .content(item.getContent())
                .deadline(item.getDeadline())
                .starting_bid(item.getStarting_bid())
                .winning_bid(item.getWinning_bid())
                .reg_date(item.getReg_date())
                .soldOut(item.isSoldOut())
                .build();
        return itemForm;
    }

    @Transactional
    public void deletePost(Long item_no){
        itemRepository.deleteById(item_no);
    }

    @Transactional
    public void saveFisrtFile(String fileName, Long itemNo) {
        itemRepository.saveFirstFile(fileName, itemNo);
    }

    @Transactional
    public void setSoldOut(Long itemNo) { itemRepository.setSoldOut(itemNo); }
}