package dev.bootcamp.ecommerce.controller;

import dev.bootcamp.ecommerce.model.Product;
import dev.bootcamp.ecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @PostMapping("/upload")
    public Product saveProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("category") String category,
            @RequestParam("stockQuantity") Integer stockQuantity,
            @RequestParam("sku") String sku,
            @RequestParam("image") MultipartFile file) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setStockQuantity(stockQuantity);
        product.setSku(sku);

        if (!file.isEmpty()) {
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            product.setImageUrl(fileNameAndPath.toString());
        }

        return productService.createProduct(product);
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            return fileNameAndPath.toString();
        } else {
            throw new RuntimeException("File is empty");
        }
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("price") Double price,
                                 @RequestParam("category") String category,
                                 @RequestParam("stockQuantity") Integer stockQuantity,
                                 @RequestParam("sku") String sku,
                                 @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
        Product productDetails = new Product();
        productDetails.setName(name);
        productDetails.setDescription(description);
        productDetails.setPrice(price);
        productDetails.setCategory(category);
        productDetails.setStockQuantity(stockQuantity);
        productDetails.setSku(sku);

        if (file != null && !file.isEmpty()) {
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            productDetails.setImageUrl(fileNameAndPath.toString());
        } else {
            // Retain the existing image URL
            Product existingProduct = productService.getProductById(id);
            productDetails.setImageUrl(existingProduct.getImageUrl());
        }

        return productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("keyword") String keyword) {
        return productService.searchProducts(keyword);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            String imageUrl = product.getImageUrl();

            // Log the image URL
            logger.debug("Fetching image from URL: {}", imageUrl);

            byte[] imageBytes;
            if (imageUrl.startsWith("http://") || imageUrl.startsWith("https://")) {
                // Fetch the image from the web URL
                RestTemplate restTemplate = new RestTemplate();
                imageBytes = restTemplate.getForObject(imageUrl, byte[].class);
            } else {
                // Fetch the image from the local file system
                Path imagePath = Paths.get(imageUrl);
                imageBytes = Files.readAllBytes(imagePath);
            }

            // Create a resource from the byte array
            ByteArrayResource resource = new ByteArrayResource(imageBytes);

            // Set the content type and attachment header
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpg");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(imageBytes.length)
                    .body(resource);
        } catch (Exception e) {
            logger.error("Error fetching image for product ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}