package lab30.rpc.api;

import lab30.rpc.dto.UserDTO;

public interface UserRpcService {


    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserDTO get(Integer id);
}
