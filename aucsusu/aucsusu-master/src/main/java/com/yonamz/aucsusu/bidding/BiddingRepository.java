package com.yonamz.aucsusu.bidding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BiddingRepository extends JpaRepository<Bidding, Long> {
    Bidding findByItemNo(Long itemNo);

    @Query("select new com.yonamz.aucsusu.bidding.BiddingDto(bidding.biddingId, bidding.itemNo, bidding.uid, max(bidding.biddingPrice)) " +
            "from Bidding bidding where bidding.uid = :uid group by bidding.itemNo")
    List<BiddingDto> findBiddingByUid(@Param("uid") String uid);

}
