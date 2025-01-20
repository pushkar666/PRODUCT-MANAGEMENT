package com.product_management.product_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Represents a Global Trade Item Number (GTIN) associated with a product.
 * This class is an entity in the database and is mapped to the "gtin_table" table.
 * @author PUSHKAR D
 * @since 1.0
 *
 */
@Entity
@Data
@Table(name = "gtin_table")
public class Gtin {
    /**
     * The unique identifier for the GTIN.
     * This field is annotated with @Id and @GeneratedValue to indicate it is the primary key and auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The actual GTIN value.
     * This field is annotated with @Column to specify the column name in the database table.
     */
    @Column(name = "gtin")
    private String gtin;

    /**
     * The product associated with this GTIN.
     * This field is annotated with @ManyToOne and @JoinColumn to establish a relationship with the Product entity.
     * The "productId" column in the database table is used to map the foreign key to the Product entity.
     * The relationship is not nullable, meaning a GTIN must always be associated with a product.
     */
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

}
