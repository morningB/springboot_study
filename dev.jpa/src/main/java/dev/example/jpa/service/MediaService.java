package dev.example.jpa.service;

import dev.example.jpa.dto.MediaDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface MediaService {
   MediaDto saveFile(MultipartFile file);
    Collection<MediaDto> saveFileBulk(MultipartFile[] files);
    byte[] getFileAsBytes(String resourcePath);
}
