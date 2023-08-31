package dev.example.jpa.controller;

import dev.example.jpa.dto.MediaDto;
import dev.example.jpa.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("media")
@Slf4j
public class MediaController {
    private final MediaService mediaService;

    public MediaController(MediaService mediaService){
        this.mediaService = mediaService;
    }

    @PostMapping("upload") // 파일과 경로를 이용해서 저장함
    public ResponseEntity<MediaDto> uploadMedia(
            @RequestParam("file") MultipartFile file
            ){
        MediaDto dto = this.mediaService.saveFile(file);

        return ResponseEntity
                .status(dto.getStatus())
                .body(dto);
    }
    @PostMapping("upload-bulk") // 파일과 경로를 이용해서 저장함
    public ResponseEntity<Collection<MediaDto>> uploadMedia(
            @RequestParam("file") MultipartFile[] files
    ){
        return ResponseEntity
                .status(HttpStatus.MULTI_STATUS)
                .body(this.mediaService.saveFileBulk(files));
    }

    @GetMapping("**")
    public ResponseEntity<byte[]> staticLike(
            HttpServletRequest request
            ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(
                this.mediaService.getFileAsBytes(request.getRequestURI().split("media")[1]),
                        headers,
                        HttpStatus.OK);

    }

}
