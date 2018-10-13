package com.ky.mongo.file.service;

import com.ky.mongo.file.domain.FileItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface FileService {

    FileItem saveFile(FileItem fileItem);

    void removeFile(String id);

    Optional<FileItem> getFileById(String id);

    List<FileItem> listFilesByPage(int pageIdx, int pageSize);
}

