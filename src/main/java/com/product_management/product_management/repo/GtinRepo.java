package com.product_management.product_management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.product_management.product_management.entity.Gtin;

/**
 * This interface represents a repository for managing {@link Gtin} entities.
 * It extends Spring Data JPA's {@link JpaRepository} interface, providing basic CRUD operations.
 * Additionally, it includes a custom method for searching GTINs based on a partial match.
 * @author PUSHKAR D
 * @since 1.0
 */
@Repository
public interface GtinRepo extends JpaRepository<Gtin, Integer> {

    /**
     * Finds GTINs that partially match the given {@code gtin} parameter.
     * The search is case-insensitive and matches any part of the GTIN.
     *
     * @param gtin The partial GTIN to search for.
     * @return A list of {@link Gtin} entities that match the given {@code gtin}.
     */
    @Query("SELECT g FROM Gtin g WHERE LOWER(g.gtin) LIKE LOWER(CONCAT('%', :gtin, '%'))")
    List<Gtin> findGtinsByGtin(String gtin);

}
