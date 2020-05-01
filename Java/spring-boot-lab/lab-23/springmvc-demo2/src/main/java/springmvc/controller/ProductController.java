package springmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springmvc.vo.ProductVO;

/**
 * 产品 Controller
 */
@Deprecated
@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping(value = "/add",
        consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ProductVO add(@RequestBody ProductVO product) {
        return product;
    }

}
