package com.yonamz.aucsusu.File;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int fno;
    @Column
    String fileName;
    @Column
    String fileOriName;
    @Column
    String fileUrl;
    @Column
    long itemNo;

    //https://url.kr/fis4un 참고
}