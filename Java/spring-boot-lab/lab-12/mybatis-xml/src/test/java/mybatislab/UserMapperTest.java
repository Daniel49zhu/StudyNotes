package mybatislab;

import mybatislab.dataobject.UserDO;
import mybatislab.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        UserDO user = new UserDO()
                .setUsername(UUID.randomUUID().toString())
                .setPassword("nicai")
                .setCreateTime(new Date());
        userMapper.insert(user);
    }
}
