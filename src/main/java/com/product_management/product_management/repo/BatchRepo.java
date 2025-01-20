package com.product_management.product_management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product_management.product_management.entity.Batch;
// import com.product_management.product_management.entity.Gtin;

/**
 * This interface represents a repository for managing {@link Batch} entities.
 * It extends Spring Data JPA's {@link JpaRepository} interface, providing basic CRUD operations.
 * Additionally, it includes custom queries for retrieving batches based on their available quantity.
 * @author PUSHKAR D
 * @since 1.0
 */
@Repository
public interface BatchRepo extends JpaRepository<Batch, Integer> {

    /**
     * Retrieves a list of batches with a positive available quantity.
     *
     * @return a list of {@link Batch} entities with available quantity greater than 0.
     */
    @Query("SELECT b FROM Batch b WHERE b.availableQuantity > 0")
    List<Batch> findPositiveAvailableQuantityBatches();

    // ALSO POSSIBLE WITH QUERYING DISTINCT GTINS INSTEAD OF BATCH
    // @Query("SELECT DISTINCT b.gtin FROM Batch b WHERE b.availableQuantity > 0")
    // List<Gtin> findGtinsWithPositiveQuantityBatches();

    /**
     * Retrieves a list of batches with a non-positive available quantity, sorted by inward date in descending order.
     *
     * @return a list of {@link Batch} entities with available quantity less than or equal to 0, sorted by inward date in descending order.
     */
    @Query("SELECT b FROM Batch b WHERE b.availableQuantity <= 0 ORDER BY b.inwardedOn DESC")
    List<Batch> findNegativeOrZeroAvailableQuantityBatches();
}
