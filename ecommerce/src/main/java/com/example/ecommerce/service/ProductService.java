package com.example.ecommerce.service;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.repository.Category_SubRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.dto.ProductDTO;
import com.example.ecommerce.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Category_SubRepository category_subRepository;

    private ProductMapper productMapper=new ProductMapper();


    public List<ProductDTO> findByProductNew(){

        return  productMapper.toDto(productRepository.findByProductNew());
    }
    public List<ProductDTO> findAllByProductSumseller(){
        return productMapper.toDto(productRepository.findAllByProductSumseller());
    }
    public Page<Product> findAllPage(int page){
        Pageable pageable= PageRequest.of(page,2);//0 là trang, 2 la so phan tu
        Page<Product> lst=productRepository.findAll(pageable);
        return lst;
    }
    public void save(ProductDTO productDTO){
        Category_Sub category_sub=category_subRepository.findOneById(Long.parseLong(productDTO.getCategory_subID()));
        Product product=productMapper.toEntity(productDTO);
        product.setCategory_sub(category_sub);
        productRepository.save(product);
    }

    public void delete(ProductDTO productDTO){
        Product product=productMapper.toEntity(productDTO);
        productRepository.delete(product);

    }

}
