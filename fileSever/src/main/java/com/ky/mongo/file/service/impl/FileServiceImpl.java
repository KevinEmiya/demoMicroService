package com.ky.mongo.file.service.impl;

import com.ky.mongo.file.dao.FileItemDao;
import com.ky.mongo.file.domain.FileItem;
import com.ky.mongo.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FileServiceImpl implements FileService {

    @Autowired
    FileItemDao fileItemDao;

    @Override
    public FileItem saveFile(FileItem fileItem) {
        return fileItemDao.save(fileItem);
    }

    @Override
    public void removeFile(String id) {
        fileItemDao.deleteById(id);
    }

    @Override
    public Optional<FileItem> getFileById(String id) {
        return fileItemDao.findById(id);
    }

    @Override
    public List<FileItem> listFilesByPage(int pageIdx, int pageSize) {
        Pageable pageable = PageRequest.of(pageIdx, pageSize,
                Sort.Direction.DESC, "uploadDate");
        return fileItemDao.findAll(pageable).getContent();
    }
}
