package com.yonamz.aucsusu.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface FilesRepository extends JpaRepository<Files, Integer> {
    Files findByFno(int fno);
    Files findByItemNo(Long itemNo);
    List<Files> findAllByItemNo(Long itemNo);
    Files findFirstByItemNo(Long itemNo);
}