package springmvc.core.web;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springmvc.core.vo.CommonResult;

@ControllerAdvice(basePackages = "springmvc.controller")
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 如果已经是 CommonResult 类型，则直接返回
        if (o instanceof CommonResult) {
            return o;
        }
        // 如果不是，则包装成 CommonResult 类型
        return CommonResult.success(o);
    }
}
