package com.product.service.service.product;

import com.product.service.dto.ApiResponseWithList;
import com.product.service.dto.ApiResponseWithObject;
import com.product.service.dto.listProduct.ListOfProductsDto;
import com.product.service.dto.productdetails.ProductDetailsDto;
import com.product.service.entity.Image;
import com.product.service.entity.Product;
import com.product.service.entity.constants.Currency;
import com.product.service.entity.constants.HomeMaterial;
import com.product.service.entity.constants.HomeType;
import com.product.service.exception.ProductException;
import com.product.service.repository.ProductRepository;
import com.product.service.service.image.ImageService;
import com.product.service.utils.URLEncoder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {


    private final ProductRepository productRepository;
    private final URLEncoder urlEncoder = new URLEncoder();
    private final ImageService imageService;


    @Override
    public ApiResponseWithObject createProduct(ProductDetailsDto detailsDto, MultipartFile[] files) {
        Product product = new Product();
        if (files != null) {
            product.setImages(imageService.images(files));
        }
        Product productEntity = productRepository.save(saveOrUpdateProduct(detailsDto, product));
        return apiResponse(productEntity);
    }

    @Override
    public ApiResponseWithObject updateProduct(ProductDetailsDto productDetailsDto) {
        Product product = findProductById(productDetailsDto.getId());
        Product productEntity = productRepository.save(saveOrUpdateProduct(productDetailsDto, product));
        return apiResponse(productEntity);
    }

    @Override
    public ApiResponseWithObject deleteProduct(Long productId) {
        findProductById(productId);
        productRepository.deleteById(productId);
        return apiResponse((Product) null);
    }

    @Override
    public ApiResponseWithObject getProductById(Long id) {
        Product productEntity = findProductById(id);
        ProductDetailsDto productDetailsDto = new ProductDetailsDto();
        List<Image> images = imageService.findAllImages(productEntity.getId());
        productEntity.setImages(images);
        productDetailsDto.setImages(imageService.setImageDetails(images));
        productDetailsDto.setId(productEntity.getId());
        productDetailsDto.setUserId(productEntity.getUserId());
        productDetailsDto.setAddress(productEntity.getAddress());
        productDetailsDto.setCurrency(productEntity.getCurrency());
        productDetailsDto.setEnableChat(productEntity.getEnableChat());
        productDetailsDto.setFloorCount(productEntity.getFloorCount());
        productDetailsDto.setLan(productEntity.getLan());
        productDetailsDto.setLat(productEntity.getLat());
        productDetailsDto.setFloorNumber(productEntity.getFloorNumber());
        productDetailsDto.setPrice(productEntity.getPrice());
        productDetailsDto.setIsFavorite(productEntity.getIsFavorite());
        productDetailsDto.setPhoneNumber(productEntity.getPhoneNumber());
        productDetailsDto.setTotalArea(productEntity.getTotalArea());
        productDetailsDto.setRoomCount(productEntity.getRoomCount());
        return apiResponse(productDetailsDto);
    }

    @Override
    public ApiResponseWithList getAllProducts() {
        return apiResponse(Collections.singletonList(productRepository.findAll()));
    }

    @Override
    public ApiResponseWithList getListOfProducts() {
        List<Product> products = productRepository.findAll();
        List<ListOfProductsDto> list = new ArrayList<>();
        for (Product product : products) {
            List<Image> images = imageService.findAllImages(product.getId());
            product.setImages(images);
            ListOfProductsDto productsDto = new ListOfProductsDto();
            productsDto.setImages(imageService.setImageDetails(images));
            productsDto.setId(product.getId());
            productsDto.setUserId(product.getUserId());
            productsDto.setAddress(product.getAddress());
            productsDto.setCurrency(product.getCurrency());
            productsDto.setEnableChat(product.getEnableChat());
            productsDto.setFloorCount(product.getFloorCount());
            productsDto.setLan(product.getLan());
            productsDto.setLat(product.getLat());
            productsDto.setFloorNumber(product.getFloorNumber());
            productsDto.setPrice(product.getPrice());
            productsDto.setIsFavorite(product.getIsFavorite());
            productsDto.setPhoneNumber(product.getPhoneNumber());
            productsDto.setTotalArea(product.getTotalArea());
            productsDto.setRoomCount(product.getRoomCount());

            list.add(productsDto);
        }
        return apiResponse(new ArrayList<>(list));
    }

    private Product saveOrUpdateProduct(ProductDetailsDto detailsDto, Product product) {
        product.setUserId(detailsDto.getUserId());
        product.setPrice(detailsDto.getPrice());
        product.setRoomCount(detailsDto.getRoomCount());
        product.setTotalArea(detailsDto.getTotalArea());
        product.setFloorNumber(detailsDto.getFloorNumber());
        product.setAddress(detailsDto.getAddress());
        product.setFloorCount(detailsDto.getFloorCount());
        product.setIsFavorite(detailsDto.getIsFavorite());
        product.setLan(detailsDto.getLan());
        product.setLat(detailsDto.getLat());
        product.setPhoneNumber(detailsDto.getPhoneNumber());
        product.setEnablePhone(detailsDto.getEnablePhone());
        product.setEnableChat(detailsDto.getEnableChat());
        product.setAreaLength(detailsDto.getLivingArea());
        product.setWithFurniture(detailsDto.getWithFurniture());
        product.setBalcony(detailsDto.getBalcony());
        product.setBuiltYear(detailsDto.getBuiltYear());
        product.setElevator(detailsDto.getElevator());
        product.setParking(detailsDto.getParking());
        product.setDescription(detailsDto.getDescription());
        product.setViewCount(detailsDto.getViewCount());

        // TODO ENUMS
        product.setCurrency(detailsDto.getCurrency());
        product.setType(detailsDto.getType());
        product.setMaterial(detailsDto.getMaterial());

        return product;
    }

    private Product findProductById(Long productId) {
        Optional<Product> productEntity = productRepository.findById(productId);
        if (productEntity.isPresent()) {
            return productEntity.get();
        }

        throw new ProductException("Product with such Id not found");
    }

    private ApiResponseWithObject apiResponse(Product product) {
        ApiResponseWithObject apiResponse = new ApiResponseWithObject();
        apiResponse.setStatus(true);
        apiResponse.setMessage("SUCCESS");
        apiResponse.setData(product);
        return apiResponse;
    }

    private ApiResponseWithList apiResponse(List<Object> products) {
        ApiResponseWithList apiResponse = new ApiResponseWithList();
        apiResponse.setStatus(true);
        apiResponse.setMessage("SUCCESS");
        apiResponse.setData(products);
        return apiResponse;
    }
    private ApiResponseWithObject apiResponse(ProductDetailsDto product) {
        ApiResponseWithObject apiResponse = new ApiResponseWithObject();
        apiResponse.setStatus(true);
        apiResponse.setMessage("SUCCESS");
        apiResponse.setData(product);
        return apiResponse;
    }
}
