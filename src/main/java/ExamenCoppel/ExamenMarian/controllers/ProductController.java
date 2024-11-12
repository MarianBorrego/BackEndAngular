package ExamenCoppel.ExamenMarian.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ExamenCoppel.ExamenMarian.models.Product;
import ExamenCoppel.ExamenMarian.repositories.ProductRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController{
	private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "10") int limit) {
        return productRepository.findAll();
    }
 // Método para insertar un producto
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Método para modificar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        // Verifica si el producto existe
        Product existingProduct = productRepository.findById(id).orElse(null);
        if(existingProduct != null) {
	        // Actualiza los detalles del producto
	        existingProduct.setTitle(productDetails.getTitle());
	        existingProduct.setPrice(productDetails.getPrice());
	        existingProduct.setDescription(productDetails.getDescription());
	        existingProduct.setCategory(productDetails.getCategory());
	        existingProduct.setImage(productDetails.getImage());
	        existingProduct.setRating(productDetails.getRating());
	
	        // Guarda el producto actualizado
	        Product updatedProduct = productRepository.save(existingProduct);
	        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return null;
    }

    // Método para borrar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Verifica si el producto existe
        Product existingProduct = productRepository.findById(id).orElse(null);
        if(existingProduct != null) {
	        productRepository.delete(existingProduct);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return null;
    }
}
