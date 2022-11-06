package com.product.service.controller;

import com.product.service.dto.ApiResponseWithList;
import com.product.service.dto.ApiResponseWithObject;
import com.product.service.entity.Image;
import com.product.service.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    /**
     * Upload single file to database.
     *
     * @param file file data
     * @return return saved image info with ImageResponse class.
     */
    @PostMapping("/upload")
    public ApiResponseWithObject uploadSingleFile(@RequestParam("file") MultipartFile file) {
        return imageService.uploadSingleFile(file);
    }

    /**
     * Upload multiple files to database.
     *
     * @param files files data
     * @return return saved images info list with ImageResponse class.
     */

    @PostMapping(value = "/uploads")
    public ApiResponseWithList uploadMultiFiles(@RequestParam("files") MultipartFile[] files) {
        return imageService.uploadMultipleFiles(files);
    }

    /**
     * Sends valid or default image bytes with given fileName pathVariable.
     *
     * @param fileName
     * @return return valid byte array
     */
    @GetMapping("/show/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) throws Exception {
        Image image = getImageByName(fileName);
        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getFileType())).body((image.getData()));
    }


    public Image getImageByName(String name) {
        Image image = imageService.findByFileName(name);
        return image;
    }

}
