默认路径 http://127.0.0.1:8080/swagger-ui.html

@Api 注解，添加在 Controller 类上，标记它作为 Swagger 文档资源。

@ApiOperation 注解，添加在 Controller 方法上，标记它是一个 API 操作

@ApiImplicitParam 注解，添加在 Controller 方法上，声明每个请求参数的信息。

@ApiModel 注解，添加在 POJO 类，声明 POJO 类的信息。而在 Swagger 中，把这种 POJO 类称为 Model 类。所以，我们下文就统一这么称呼。

@ApiModelProperty 注解，添加在 Model 类的成员变量上，声明每个成员变量的信息。

@ApiResponse 注解，添加在 Controller 类的方法上，声明每个响应参数的信息。