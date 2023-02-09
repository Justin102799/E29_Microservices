package com.example.Product.Service;

import com.example.Product.DTO.ProductDTO;
import com.example.Product.DTO.ResponseDTO;
import com.example.Product.DTO.UserDTO;
import com.example.Product.Entity.Product;
import com.example.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestTemplate restTemplate;
//    public Product addProducts(Product product) {
//        return productRepository.save(product);
//    }

    public Product updateProduct(Long id, Product product) {
        product.setProductId(id);
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    public ResponseDTO getProductsById(Long productId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Optional<Product> product = productRepository.findById(productId);
        ProductDTO productDTO = mapToUser(product.get());

        ResponseEntity<UserDTO> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/api/getUsers/"+ product.get().getUserId(),
                        UserDTO.class);

        UserDTO userDTO = responseEntity.getBody();

        System.out.println(responseEntity.getStatusCode());


        responseDTO.setProductDto(productDTO);
        responseDTO.setUserDto(userDTO);
        return responseDTO;
        }
    private ProductDTO mapToUser(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getUserId());
        return productDTO;
    }
    public String getUserRole(int user_id){
        UserDTO user = restTemplate.getForObject("http://localhost:8080/api/getUsers/"+user_id, UserDTO.class);
        if(user == null){
            return null;
        }
        return user.getRole();
    }
    public String addProducts(int userId, Product product) {
        if(getUserRole(userId) == null){
            return HttpStatus.NOT_FOUND.toString();
        }
        if(getUserRole(userId).equals("buyer")){
            return HttpStatus.UNAUTHORIZED.toString();
        }
        product.setUserId(String.valueOf(userId));
        productRepository.save(product);
        return HttpStatus.OK.toString();
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
