package com.trikonenko.filesearch.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.trikonenko.filesearch.persistence.domain.FileEntity;
import com.trikonenko.filesearch.persistence.repo.FilesRepo;
import com.trikonenko.filesearch.utils.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileSearchService {

    @Autowired
    private FilesRepo filesRepo;

    public List<FileEntity> searchFile(String keyword) {
        return filesRepo.findByNameContainsOrPathContainsOrContentContains(keyword, keyword, keyword);
    }

    @Transactional
    public void indexDirectory(File dirPath) {
        filesRepo.deleteAll();
        File[] directoryListing = dirPath.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                String content = FileReader.readLineByLineJava8(file.getAbsolutePath());
                FileEntity fileEntity = new FileEntity();
                fileEntity.setName(file.getName());
                fileEntity.setPath(file.getAbsolutePath());
                fileEntity.setContent(content);
                fileEntity.setUpdated(new Date(file.lastModified()));
                filesRepo.save(fileEntity);
            }
        }
    }
}
