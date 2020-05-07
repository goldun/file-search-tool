package com.trikonenko.filesearch.service;

import java.util.List;

import com.trikonenko.filesearch.persistence.domain.FileEntity;
import com.trikonenko.filesearch.persistence.repo.FilesRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileSearchService {

    @Autowired
    private FilesRepo filesRepo;

    public List<FileEntity> searchFile(String keyword) {
        return filesRepo.findByNameContainsOrPathContainsOrContentContains(keyword, keyword, keyword);
    }
}
