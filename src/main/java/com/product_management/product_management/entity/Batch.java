package com.product_management.product_management.entity;

import java.time.LocalDate;

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
 * Represents a batch of products in the product management system.
 * Each batch is associated with a specific Global Trade Item Number (GTIN) and
 * contains various attributes.
 * @author PUSHKAR D
 * @since 1.0
 */
@Entity
@Data
@Table(name = "batch_table")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batchId")
    private Integer batchId;

    /**
     * Maximum Retail Price (MRP) of the products in this batch.
     */
    @Column(name = "mrp")
    private Integer mrp;

    /**
     * Selling Price (SP) of the products in this batch.
     */
    @Column(name = "sp")
    private Integer sp;

    /**
     * Purchase price of the products in this batch.
     */
    @Column(name = "purchasePrice")
    private Integer purchasePrice;

    /**
     * Available quantity of products in this batch.
     */
    @Column(name = "availableQuantity")
    private Integer availableQuantity;

    /**
     * Date when the products in this batch were inwarded into the system.
     */
    @Column(name = "inwardedOn")
    private LocalDate inwardedOn;

    /**
     * Global Trade Item Number (GTIN) associated with this batch.
     * This field is mandatory and cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "gtinId", nullable = false)
    private Gtin gtin;
}
