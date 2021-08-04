package com.yonamz.aucsusu.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files, Integer> {
    Files findByFno(@Param("fno") int fno);
    Files findByItemNo(@Param("itemNo") Long itemNo);
    List<Files> findAllByItemNo(@Param("itemNo")Long itemNo);
    Files findFirstByItemNo(@Param("itemNo")Long itemNo);
}