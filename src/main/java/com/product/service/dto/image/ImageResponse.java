package com.product.service.dto.image;

import com.product.service.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {

    private String uuid;
    private long size;
    private String linkForDownload;

    public ImageResponse(Image image) {
        setUuid(image.getUuid());
        setSize(image.getSize());
    }
}
