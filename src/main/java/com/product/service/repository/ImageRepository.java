package com.product.service.repository;

import com.product.service.dto.image.ImageResponse;
import com.product.service.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByFileName(String fileName);

    Image findByUuid(String uuid);
    List<Image> findAllById(Long id);
}
