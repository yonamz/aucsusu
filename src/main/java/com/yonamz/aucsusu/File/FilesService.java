package com.yonamz.aucsusu.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FilesService {
    @Autowired
    FilesRepository filesRepository;

    public void save(Files files) {
        Files file = new Files();
        file.setFileName(files.getFileName());
        file.setFileOriName(files.getFileOriName());
        file.setFileUrl(files.getFileUrl());
        file.setItemNo(files.getItemNo());

        filesRepository.save(file);
    }

    public Files findByFno(int i) {
        Files file = filesRepository.findByFno(i);
        return file;
    }

    public Files findByItemNo(Long item_no) {
        Files file = filesRepository.findByItemNo(item_no);
        return file;
    }

    public List<Files> findAllByItemNo(Long item_no) {
        List<Files> filesList = filesRepository.findAllByItemNo(item_no);
        //System.out.println("파일리스트 : "+filesList);
        return filesList;
    }

    public List<Files> getFilesList() {
        List<Files> files = filesRepository.findAll();
        return files;
    }

    @Transactional
    public void deleteImageByItemNo(Long itemNo) {
        filesRepository.deleteImageByItemNo(itemNo);
    }

    @Transactional
    public void deleteImageByFno(int fno) {
        filesRepository.deleteImageByFno(fno);
    }

    @Transactional
    public String findFirstByItemNo(Long itemNo) {
        return filesRepository.findFirstByItemNo(itemNo).getFileName();
    }
}