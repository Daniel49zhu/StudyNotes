package lab30.rpc.service;

import lab30.rpc.api.UserRpcService;
import lab30.rpc.dto.UserDTO;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "${dubbo.provider.UserRpcService.version}")
public class UserRpcServiceImpl implements UserRpcService {
    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }
}
