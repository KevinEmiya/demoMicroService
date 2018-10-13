package com.ky.mongo.file.dao;

import com.ky.mongo.file.domain.FileItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileItemDao extends MongoRepository<FileItem, String> {
}
