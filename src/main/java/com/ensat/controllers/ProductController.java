package com.ensat.controllers;

import com.ensat.entities.Product;
import com.ensat.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Product controller.
 */
@RestController
@RequestMapping("/products")  // Use @RequestMapping for the base path
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * List all products.
     *
     * @param model
     * @return
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        System.out.println("Returning products:");
        return "products";  // This will render the list of products
    }

    /**
     * View a specific product by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";  // This will render the product details
    }

    /**
     * Show form to edit a product.
     *
     * @param id
     * @param model
     * @return
     */
    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productform";  // This will render the product form for editing
    }

    /**
     * Show form for creating a new product.
     *
     * @param model
     * @return
     */
    @GetMapping("/new")  // Changed to "/new" to avoid conflict
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "productform";  // This will render the form for new product
    }

    /**
     * Save the product to the database.
     *
     * @param product
     * @return
     */
    @PostMapping  // Use @PostMapping without specifying the path to match the base URL
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/products/" + product.getId();  // Redirect to the newly created product's page
    }

    /**
     * Delete a product by its id.
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";  // Redirect to the product list
    }
}
