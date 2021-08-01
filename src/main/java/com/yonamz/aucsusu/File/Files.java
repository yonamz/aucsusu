package com.yonamz.aucsusu.File;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int fno;
    String fileName;
    String fileOriName;
    String fileUrl;
    long itemNo;

    //https://url.kr/fis4un 참고
}