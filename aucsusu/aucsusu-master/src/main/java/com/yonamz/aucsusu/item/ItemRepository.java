package com.yonamz.aucsusu.item;


import com.yonamz.aucsusu.File.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select item from Item item where item_no = :item_no")
    Item findByItem_no(Long item_no);

    @Query(value = "update Item item set item.winning_bid = :biddingPrice, item.bidder = :bidder where item.item_no = :itemNo")
    @Modifying
    @Transactional
    void saveWinningBid(Integer biddingPrice, String bidder, Long itemNo);

    @Query(value = "select item from Item item where item.item_no in (select bidding.itemNo from Bidding bidding where bidding.uid = :uid)", nativeQuery = true)
    List<Item> findBiddingItemsByUid(String uid);

    @Query("select item from Item item where item.writer = :uid")
    List<Item> findItemsByUid(String uid);

    @Query("update Item item set item.fileName = :fileName where item.item_no = :itemNo")
    @Modifying
    @Transactional
    void saveFirstFile(String fileName, Long itemNo);


    @Query("update Item item set item.soldOut = true where item.item_no = :itemNo")
    @Modifying
    @Transactional
    void setSoldOut(Long itemNo);

    @Query("select i from Item i where category=:category")
    List<Item> findByCategory(String category);

    List<Item> findByWriterContaining(String keyword);
    List<Item> findByTitleContaining(String keyword);

}
