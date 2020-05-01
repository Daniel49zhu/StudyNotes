package springmvc.service;

import org.springframework.stereotype.Service;
import springmvc.vo.UserVO;

@Service
public class UserService {
    public UserVO get(Integer id) {
        return new UserVO().setId(id).setUsername("test");
    }
}
