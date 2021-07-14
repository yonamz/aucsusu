package com.yonamz.aucsusu.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Integer> {
    Files findByFno(int fno);
    Files findByItemNo(Long itemNo);
}