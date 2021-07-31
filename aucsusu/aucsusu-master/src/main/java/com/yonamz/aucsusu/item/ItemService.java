package com.yonamz.aucsusu.item;


import com.yonamz.aucsusu.File.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5;
    private static final int PAGE_POST_COUNT = 8;

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
    public List<ItemForm> getItemList(Integer pageNum){

        Page<Item> page = itemRepository.findAll(PageRequest.of(pageNum-1,PAGE_POST_COUNT));

        List<Item> items = page.getContent();
        //List<Item> items = itemRepository.findAll();
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
                    .category(item.getCategory())
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
                    .category(item.getCategory())
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
                    .category(item.getCategory())
                    .build();

            itemForms.add(itemForm);
        }
        return itemForms;
    }

    @Transactional
    public List<ItemForm> getOneItemList(String category){
        List<Item> items = itemRepository.findByCategory(category);
        List<ItemForm> itemForms = new ArrayList<>();

        for(Item item : items){
            ItemForm itemForm = ItemForm.builder()
                    .item_no(item.getItem_no())
                    .title(item.getTitle())
                    .content(item.getContent())
                    .writer(item.getWriter())
                    .deadline(item.getDeadline())
                    .starting_bid(item.getStarting_bid())
                    .reg_date(item.getReg_date())
                    .category(item.getCategory())
                    .build();

            itemForms.add(itemForm);
        }
        return itemForms;
    }

    @Transactional
    public ItemForm getPost(Long item_no){

        Optional<Item> itemWrapper = itemRepository.findById(item_no);
        Item item = itemWrapper.get();

        ItemForm itemForm = ItemForm.builder()
                .item_no(item.getItem_no())
                .title(item.getTitle())
                .writer(item.getWriter())
                .content(item.getContent())
                .deadline(item.getDeadline())
                .starting_bid(item.getStarting_bid())
                .winning_bid(item.getWinning_bid())
                .reg_date(item.getReg_date())
                .soldOut(item.isSoldOut())
                .category(item.getCategory())
                .build();
        return itemForm;
    }

    @Transactional
    public Integer[] getSearchList(Integer curPageNum,String keyword, String category){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 수
        Double itemsTotalCount = Double.valueOf(this.getSearchCount(keyword, category));

        //총 게시글 수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((itemsTotalCount/PAGE_POST_COUNT)));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum+BLOCK_PAGE_NUM_COUNT)
                ?curPageNum + BLOCK_PAGE_NUM_COUNT
                :totalLastPageNum;

        //페이지 시작 번호 조정
        curPageNum = (curPageNum<=3)?1:curPageNum-2;

        //페이지 번호 할당
        for(int val=curPageNum, i=0; val<=blockLastPageNum;val++,i++){
            pageList[i]=val;
        }

        return pageList;
    }

    @Transactional
    public Integer[] getPageList(Integer curPageNum){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 수
        Double itemsTotalCount = Double.valueOf(this.getItemCount());

        //총 게시글 수를 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((itemsTotalCount/PAGE_POST_COUNT)));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum+BLOCK_PAGE_NUM_COUNT)
                ?curPageNum + BLOCK_PAGE_NUM_COUNT
                :totalLastPageNum;

        //페이지 시작 번호 조정
        curPageNum = (curPageNum<=3)?1:curPageNum-2;

        //페이지 번호 할당
        for(int val=curPageNum, i=0; val<=blockLastPageNum;val++,i++){
            pageList[i]=val;
        }

        return pageList;
    }

    @Transactional
    public Long getItemCount(){
        return itemRepository.count();
    }

    @Transactional
    public Long getSearchCount(String keyword, String category){
        Long count;
        if(category.equals("writer")){
            System.out.println("writer 작동");
            count = itemRepository.findByWriterContaining(keyword).stream().count();
        }else{
            count = itemRepository.findByTitleContaining(keyword).stream().count();
        }
        return count;
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

    public List<ItemForm> searchItems(String keyword, String category){
        List<Item> items;
        System.out.println("category : "+category);
        if(category.equals("writer")){
            System.out.println("writer 작동");
            items = itemRepository.findByWriterContaining(keyword);
        }else{
            items = itemRepository.findByTitleContaining(keyword);
        }

        if(items!=null){
            System.out.println("검색 결과 : "+items);
        }else {
            System.out.println("검색 결과 : null");
        }


        List<ItemForm> itemFormList = new ArrayList<>();

        if(items.isEmpty()) return itemFormList;

        for(Item item : items){
            itemFormList.add(this.convertEntityToDto(item));
        }

        return itemFormList;
    }

    private ItemForm convertEntityToDto(Item item){
        return ItemForm.builder()
                .item_no(item.getItem_no())
                .title(item.getTitle())
                .writer(item.getWriter())
                .content(item.getContent())
                .deadline(item.getDeadline())
                .starting_bid(item.getStarting_bid())
                .reg_date(item.getReg_date())
                .category(item.getCategory())
                .build();
    }

    @Transactional
    public List<Item> findAllDesc(List<ItemForm> items){
        return itemRepository.findAllDesc();
    }


}