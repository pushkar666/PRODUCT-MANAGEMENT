package com.product_management.product_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product_management.product_management.entity.Product;

/**
 * This interface represents a repository for managing {@link Product} entities.
 * It extends {@link JpaRepository} which provides CRUD operations and
 * additional
 * query methods for accessing {@link Product} entities in a database.
 *
 * @author PUSHKAR D
 * @since 1.0
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
