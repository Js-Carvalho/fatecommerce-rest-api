package br.com.fatecommerce.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "name_product", nullable = false, length = 300, unique = true)
    @NotBlank(message = "O campo nome é obrigatório!")
    @Length(min = 2, max = 300, message = "O nome do produto deve ter entre 2 e 300 caracteres")
    private String nameProduct;

    @Column(name = "description_product", length = 3000)
    @NotBlank(message = "O campo descrição é obrigatório!")
    @Length(min = 15, max = 3000, message = "A descrição deve ter entre 15 e 1000 caracteres")
    private String descriptionProduct;

    @Column(name = "sku_product", length = 40)
    @NotBlank(message = "O campo SKU é obrigatório!")
    @Length(max = 40, message = "O SKU deve ter no máximo 40 caracteres")
    private String skuProduct;

    @Column(name = "ean_product", length = 13, nullable = false, unique = true)
    @NotBlank(message = "O campo EAN é obrigatório!")
    @Length(max = 13, message = "O EAN deve ter no máximo 13 caracteres")
    private String eanProduct;

    @Column(name = "price_product", precision = 10, scale = 2, nullable = false)
    @NotNull(message = "O campo preço de venda é obrigatório!")
    private BigDecimal costPriceProduct;

    @Column(name = "amount_product", precision = 10, scale = 2)
    @NotNull(message = "O campo preço de custo é obrigatório!")
    private BigDecimal amountProduct;

    @Column(name = "published_product")
    private Boolean publishedProduct;

    @Column(name = "date_created_product", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreatedProduct;

    @Column(name = "date_updated_product")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateUpdatedProduct;

    @Column(name = "stock_product", nullable = false)
    @NotNull(message = "O estoque do produto é obrigatório!")
    private Long stockProduct;

    @PrePersist
    private void prePersist() {
        this.setPublishedProduct(false);
        this.setDateCreatedProduct(LocalDate.now());
    }

    @PreUpdate
    private void preUpdate() {
        this.setDateUpdatedProduct(LocalDate.now());
    }
}
