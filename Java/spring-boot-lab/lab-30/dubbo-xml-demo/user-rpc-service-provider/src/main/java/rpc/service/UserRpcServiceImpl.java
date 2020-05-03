package rpc.service;

import lab30.api.UserRpcService;
import lab30.core.ServiceException;
import lab30.core.ServiceExceptionEnum;
import lab30.dto.UserAddDTO;
import lab30.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

@Service
public class UserRpcServiceImpl implements UserRpcService {
    @Override
    public UserDTO get(@NotNull(message = "用户编号不能为空") Integer id) throws ConstraintViolationException {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @Override
    public Integer add(UserAddDTO addDTO) throws ConstraintViolationException {
        // 这里，模拟用户已经存在的情况
        if ("user".equals(addDTO.getName())) {
            throw new ServiceException(ServiceExceptionEnum.USER_EXISTS);
        }
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }
}
