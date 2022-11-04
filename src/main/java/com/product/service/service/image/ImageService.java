package com.product.service.service.image;

import com.product.service.dto.ApiResponseWithList;
import com.product.service.dto.ApiResponseWithObject;
import com.product.service.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    ApiResponseWithObject uploadSingleFile(MultipartFile file);

    ApiResponseWithList uploadMultipleFiles(MultipartFile[] files);

    Image save(Image image);

    Image findByFileName(String fileName);

    List<Image> images(MultipartFile[] files);

    List<Image> findAllImages(Long id);
}
