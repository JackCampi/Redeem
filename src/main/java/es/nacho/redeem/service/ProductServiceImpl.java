package es.nacho.redeem.service;

import es.nacho.redeem.exception.InsufficientStockException;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product reduceProductStock(Product product, int quantity) throws InsufficientStockException {
        if(product.getStock()-quantity<0) throw new InsufficientStockException();
        product.setStock(product.getStock()-quantity);
        return productRepository.save(product);
    }
}
