package com.zjc.springdata.repository;


import com.zjc.springdata.dataobject.ProductDO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductDO, Integer> {
}
