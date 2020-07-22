package com.zjc.springdata.test.repository;

import com.zjc.springdata.MongoApplication;
import com.zjc.springdata.dataobject.ProductDO;
import com.zjc.springdata.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoApplication.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert() {
        // 创建 ProductDO 对象
        ProductDO product = new ProductDO();
        product.setName("芋头");
        // 插入
        productRepository.insert(product);
        // 打印 ID
        System.out.println(product.getId());
    }

}
