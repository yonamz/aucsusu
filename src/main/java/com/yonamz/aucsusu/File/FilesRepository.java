package com.yonamz.aucsusu.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files, Integer> {
    Files findByFno(int fno);
    Files findByItemNo(Long itemNo);
    List<Files> findAllByItemNo(Long itemNo);
}