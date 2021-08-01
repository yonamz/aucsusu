package com.yonamz.aucsusu.item;


import com.yonamz.aucsusu.File.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {

    @Modifying
    @Query("update Item i set i.cnt = i.cnt + 1 where i.item_no = :item_no")
    int updateCount(@Param("item_no") Long item_no);

    @Query("select item from Item item where item_no = :item_no")
    Item findByItem_no(@Param("item_no") Long item_no);

    @Query(value = "update Item item set item.winning_bid = :biddingPrice, item.bidder = :bidder where item.item_no = :itemNo")
    @Modifying
    @Transactional
    void saveWinningBid(@Param("biddingPrice")Integer biddingPrice, @Param("bidder")String bidder, @Param("itemNo")Long itemNo);

    @Query(value = "select item from Item item where item.item_no in (select bidding.itemNo from Bidding bidding where bidding.uid = :uid)")
    List<Item> findBiddingItemsByUid(@Param("uid") String uid);

    @Query("select item from Item item where item.writer = :uid")
    List<Item> findItemsByUid(@Param("uid")String uid);

    @Query("update Item item set item.fileName = :fileName where item.item_no = :itemNo")
    @Modifying
    @Transactional
    void saveFirstFile(@Param("fileName")String fileName, @Param("itemNo")Long itemNo);


    @Query("update Item item set item.soldOut = true where item.item_no = :itemNo")
    @Modifying
    @Transactional
    void setSoldOut(@Param("itemNo")Long itemNo);

    @Query("select i from Item i where category=:category")
    List<Item> findByCategory(@Param("category")String category);

    List<Item> findByWriterContaining(@Param("keyword")String keyword);
    List<Item> findByTitleContaining(@Param("keyword")String keyword);

    @Query("select i from Item i order by i.reg_date desc")
    List<Item> findAllDesc();


}
