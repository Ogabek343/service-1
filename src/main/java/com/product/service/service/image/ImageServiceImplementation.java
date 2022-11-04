package com.product.service.service.image;

import com.product.service.dto.ApiResponseWithList;
import com.product.service.dto.ApiResponseWithObject;
import com.product.service.entity.Image;
import com.product.service.repository.ImageRepository;
import com.product.service.utils.FileNameHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImplementation implements ImageService {

    private final ImageRepository imageRepository;
    private final FileNameHelper fileHelper;

    @Override
    public ApiResponseWithObject uploadSingleFile(MultipartFile file) {
        Image image = Image.buildImage(file, fileHelper);
        return new ApiResponseWithObject(true, "SUCCESS", save(image));
    }

    @Override
    public ApiResponseWithList uploadMultipleFiles(MultipartFile[] files) {
        return new ApiResponseWithList(true, "SUCCESS", Collections.singletonList(images(files)));
    }


    @Override
    public Image save(Image image) throws NullPointerException {
        if (image == null)
            throw new NullPointerException("Image Data NULL");
        return imageRepository.save(image);
    }

    @Override
    public Image findByFileName(String fileName) {
        return this.imageRepository.findByFileName(fileName);
    }

    public List<Image> images(MultipartFile[] files) {
        List<Image> images = Arrays.asList(files).stream().map(file -> save(Image.buildImage(file, fileHelper))).collect(Collectors.toList());
        return images;
    }

    @Override
    public List<Image> findAllImages(Long id) {
        return imageRepository.findAllById(id);
    }


}
