package es.nacho.redeem.service.api;

import es.nacho.redeem.model.Product;

import java.util.Collection;

public interface ViewProductList {
    Collection<Product> get(long companyNIT) throws Exception;
}
