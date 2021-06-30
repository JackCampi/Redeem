package es.nacho.redeem.service;

import es.nacho.redeem.exception.InsufficientStockException;
import es.nacho.redeem.model.Product;

public interface ProductService {

    Product reduceProductStock(Product product, int quantity) throws InsufficientStockException;
}
