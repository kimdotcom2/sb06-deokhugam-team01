package com.sprint.sb06deokhugamteam01.service.book;

import org.springframework.stereotype.Service;

@Service
public class BasicS3StorageService implements S3StorageService {
    @Override
    public String putObject(String id, byte[] data) {
        return "";
    }

    @Override
    public void deleteObject(String id) {

    }

    @Override
    public String getPresignedUrl(String id) {
        return "";
    }
}
