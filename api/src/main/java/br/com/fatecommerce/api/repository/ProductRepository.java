package br.com.fatecommerce.api.repository;

import br.com.fatecommerce.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
