package com.product_management.product_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product_management.product_management.entity.Batch;
import com.product_management.product_management.entity.Gtin;
import com.product_management.product_management.entity.Product;
import com.product_management.product_management.service.DataService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This class is responsible for handling HTTP requests related to product,
 * GTIN, and batch data.
 * It uses the DataService to perform CRUD operations on the database tables.
 */
@RestController
@RequestMapping("/api/")
public class DataController {

    @Autowired
    private DataService dataService;

    /**
     * POST API: Creates a new product row in the database.
     *
     * @param product The product object to be created.
     * @return The created product object with an HTTP status code of 201 (Created).
     */
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = dataService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * POST API: Creates a new GTIN row in the database.
     *
     * @param gtin The GTIN object to be created.
     * @return The created GTIN object with an HTTP status code of 201 (Created).
     */
    @PostMapping("/gtins")
    public ResponseEntity<Gtin> createGtin(@RequestBody Gtin gtin) {
        Gtin createdGtin = dataService.createGtin(gtin);
        return new ResponseEntity<>(createdGtin, HttpStatus.CREATED);
    }

    /**
     * POST API: Creates a new batch row in the database.
     *
     * @param batch The batch object to be created.
     * @return The created batch object with an HTTP status code of 201 (Created).
     */
    @PostMapping("/batches")
    public ResponseEntity<Batch> createBatch(@RequestBody Batch batch) {
        Batch createdBatch = dataService.createBatch(batch);
        return new ResponseEntity<>(createdBatch, HttpStatus.CREATED);
    }

    /**
     * GET API: Retrieves a GTIN by its ID.
     *
     * @param id The ID of the GTIN to be retrieved.
     * @return The GTIN object with the specified ID, or an HTTP status code of 404
     *         (Not Found) if not found.
     */
    @GetMapping("/gtin/{id}")
    public ResponseEntity<Gtin> getGtinById(@PathVariable Integer id) {
        return dataService.getGtinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * GET API: Retrieves all GTINs.
     *
     * @return A list of all GTIN objects.
     */
    @GetMapping("/gtins")
    public ResponseEntity<List<Gtin>> getAllGtins() {
        List<Gtin> gtins = dataService.getAllGtins();
        return ResponseEntity.ok(gtins);
    }

    /**
     * GET API: Retrieves GTINs by their GTIN entity.
     *
     * @param gtin The GTIN entity to be retrieved.
     * @return A list of GTIN objects with the specified GTIN entity.
     */
    @GetMapping("/gtins/{gtin}")
    public ResponseEntity<List<Gtin>> getGtinsByGtinEntity(@RequestParam String gtin) {
        List<Gtin> gtins = dataService.getGtinByGtin(gtin);
        return ResponseEntity.ok(gtins);
    }

    /**
     * GET API: Retrieves GTINs with positive available quantity batches.
     *
     * @return A list of GTIN objects that have positive available quantity batches.
     */
    @GetMapping("/gtins/positive-quantity")
    public ResponseEntity<List<Gtin>> getGtinsWithPositiveQuantityBatches() {
        List<Gtin> gtins = dataService.getGtinsWithPositiveQuantityBatches();
        return ResponseEntity.ok(gtins);
    }

    /**
     * GET API: Retrieves the latest batch with negative or zero available quantity.
     *
     * @return The latest batch with negative or zero available quantity, or an HTTP
     *         status code of 404 (Not Found) if not found.
     */
    @GetMapping("/batches/negative-zero-latest")
    public ResponseEntity<Batch> getLatestBatchWithNegativeOrZeroQuantity() {
        Batch batch = dataService.getLatestBatchWithNegativeOrZeroQuantity();
        if (batch != null) {
            return ResponseEntity.ok(batch);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
