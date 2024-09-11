package br.com.fatecommerce.api.service;

import br.com.fatecommerce.api.entity.Product;
import br.com.fatecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    ;

    public List<Product> getInfoProducts() {
        return productRepository.findAll();
    }

    // passa que deve receber ums string e um objeto
    public HashMap<String, Object> deleteProduct(Long idProduct) {
        Optional<Product> product =
                Optional.ofNullable(productRepository.findById(idProduct).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado!")));


        productRepository.delete(product.get());
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Produto: " + product.get().getNameProduct() + " excluído com sucesso!");
        return result;
    }

    public Product findProductById(Long idProduct) {
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado!"));
    }

    public Product updateProduct(Product product) {
        if (findProductById(product.getIdProduct()) != null) {
            return productRepository.saveAndFlush(product);
        } else {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Produto não encontrado");
        }
    }

    public Optional<Product> findByEanProduct(String eanProduct) {
        return Optional.ofNullable(productRepository.findByEanProduct(eanProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Nenhum produto encontrado com esse EAN!")));
    }

    public List<Product> findBySkuProduct(String skuProduct) {
        if (skuProduct != null) {
            List<Product> list = productRepository.findBySkuProduct(skuProduct);
            if (list.isEmpty()) {
                throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Lista de produtos está vazia!");
            }
            return list;
        } else {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "SKU não encontrado");
        }
    }

    public List<Product> findByNameProduct(String nameProduct) {
        if (nameProduct != null) {
            List<Product> list = productRepository.findByNameProductIgnoreCaseContaining(nameProduct);
            if (list.isEmpty()) {
                throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Lista de produtos está vazia!");
            }
            return list;
        } else {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Produto não encontrado");
        }
    }
}
