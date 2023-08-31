package com.example.qwer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
@RequestMapping("/rest")
public class SampleRestController {

    @GetMapping("sample-payload")
    public  SamplePayLoad samplePayLoadGet(){
        return new SamplePayLoad("zxcv",222,"adfasdf");
    }


    @GetMapping(value = "/sample-image",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public byte[] sampleImage() throws IOException{
        InputStream inputStream = getClass().getResourceAsStream("/static/asdf.png"); // 이 문자열을 찾아서 들어간다.
        // inputStream = new FileInputStream(new File(""));

        return inputStream.readAllBytes();
    }

    @PostMapping(
            value="sample-payload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void samplePayLoadPost(@RequestBody SamplePayLoad samplePayLoad){

        log.info(samplePayLoad.toString());
    }
    @PostMapping("/sample-multi")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void asdffff(
            @RequestParam("name") String name,
            @RequestParam("age") Integer age,
            @RequestParam("occupation") String occupation,
            @RequestParam("file") MultipartFile multipartFile

    ){

        log.info(multipartFile.getOriginalFilename());
    }
    @PostMapping("/asdfasdf")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void testPosting(@RequestParam("name") String name,
                            @RequestParam("ww") String ww,
                            @RequestParam("fifa") String player

    ){
        log.info(name +" " + " "+ ww + " "+player + " ");
    }
}
