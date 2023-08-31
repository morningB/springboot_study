package dev.example.jpa.service;

import dev.example.jpa.dto.MediaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
public class MediaServiceImp implements MediaService {
    private final String basePath = "./media";

    @Override
    public MediaDto saveFile(MultipartFile file) {

        return this.saveToDir(file);
    }

    @Override
    public Collection<MediaDto> saveFileBulk(MultipartFile[] files) {
        Collection<MediaDto> mediaDto = new ArrayList<>();
        for (MultipartFile file : files) {
            mediaDto.add(this.saveToDir(file));
        }
        return mediaDto;
    }

    @Override
    public byte[] getFileAsBytes(String resourcePath) {
        try{
            return Files.readAllBytes(Path.of(basePath,resourcePath));
        } catch(IOException e){
            log.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }


    private MediaDto saveToDir(MultipartFile file) {
        MediaDto dto = new MediaDto();
        dto.setStatus(200);
        dto.setOriginalName(file.getOriginalFilename());

        try {
            LocalDateTime now = LocalDateTime.now(); // 같은 이름의 사용자가 동시에 올리는거를 막기위해 즉 중복 파일을 막기 위해
            String targetDir = Path.of(
                    basePath,
                    now.format(DateTimeFormatter.BASIC_ISO_DATE)).toString();

            String newFileName = now.format(DateTimeFormatter.ofPattern("asdasdasd")) + " " + file.getOriginalFilename();

            File dirNow = new File(targetDir);
            if (!dirNow.exists())
                dirNow.mkdir();

            file.transferTo(Path.of(targetDir,newFileName));
            dto.setResourcePath(Path.of(targetDir, newFileName).toString().substring(1));
            return dto;
        } catch (Exception e) {
            log.info(e.getMessage());
            dto.setMessage("failed");
            dto.setStatus(500);
            return dto;
        }

    }
}
