package lab02.resource.apl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    //    @CrossOrigin
    @RequestMapping("/hello")
    public String hello() {
        return "world";
    }

}
