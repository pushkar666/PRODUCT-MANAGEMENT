package com.product_management.product_management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product_management.product_management.entity.Batch;
import com.product_management.product_management.entity.Gtin;
import com.product_management.product_management.entity.Product;
import com.product_management.product_management.repo.BatchRepo;
import com.product_management.product_management.repo.GtinRepo;
import com.product_management.product_management.repo.ProductRepo;

/**
 * This class provides various data-related services for managing products,
 * GTINs, and batches.
 * @author PUSHKAR D
 * @since 1.0
 */
@Service
public class DataService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private GtinRepo gtinRepo;

    @Autowired
    private BatchRepo batchRepo;

    /**
     * Creates a new product in the database.
     *
     * @param product The product to be created.
     * @return The created product.
     */
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    /**
     * Creates a new GTIN in the database.
     *
     * @param gtin The GTIN to be created.
     * @return The created GTIN.
     */
    public Gtin createGtin(Gtin gtin) {
        return gtinRepo.save(gtin);
    }

    /**
     * Creates a new batch in the database.
     *
     * @param batch The batch to be created.
     * @return The created batch.
     */
    public Batch createBatch(Batch batch) {
        return batchRepo.save(batch);
    }

    /**
     * Retrieves a GTIN by its ID.
     *
     * @param id The ID of the GTIN to be retrieved.
     * @return An Optional containing the GTIN if found, otherwise an empty
     *         Optional.
     */
    public Optional<Gtin> getGtinById(Integer id) {
        return gtinRepo.findById(id);
    }

    /**
     * Retrieves a list of GTINs by their GTIN value.
     *
     * @param gtin The GTIN value to be used for retrieval.
     * @return A list of GTINs that match the given GTIN value.
     */
    public List<Gtin> getGtinByGtin(String gtin) {
        return gtinRepo.findGtinsByGtin(gtin);
    }

    /**
     * Retrieves all GTINs from the database.
     *
     * @return A list of all GTINs.
     */
    public List<Gtin> getAllGtins() {
        return gtinRepo.findAll();
    }

    /**
     * Retrieves a list of GTINs that have associated batches with positive
     * available quantity.
     *
     * @return A list of distinct GTINs with positive available quantity batches.
     */
    // THIS METHOD RETURNS LIST OF DISTINCT GTIN GTIN//-
    // public List<Gtin> getGtinsWithPositiveQuantityBatches() {//-
    // return batchRepo.findGtinsWithPositiveQuantityBatches();//-
    // }/
    public List<Gtin> getGtinsWithPositiveQuantityBatches() {
        List<Batch> batches = batchRepo.findPositiveAvailableQuantityBatches();
        return batches.stream()
                .map(Batch::getGtin)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the latest batch with negative or zero available quantity.
     *
     * @return The latest batch with negative or zero available quantity, or null if
     *         no such batch exists.
     */
    public Batch getLatestBatchWithNegativeOrZeroQuantity() {
        List<Batch> batches = batchRepo.findNegativeOrZeroAvailableQuantityBatches();
        return batches.isEmpty() ? null : batches.get(0);
    }
}
