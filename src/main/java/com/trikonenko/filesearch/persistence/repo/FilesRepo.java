package com.trikonenko.filesearch.persistence.repo;

import java.util.List;

import com.trikonenko.filesearch.persistence.domain.FileEntity;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface FilesRepo extends PagingAndSortingRepository<FileEntity, Integer> {

    List<FileEntity> findByNameContainsOrPathContainsOrContentContains(String name, String path, String content);

}
