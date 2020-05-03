package lab11.dao.redis;

import lab11.cacheobject.UserCacheObject;
import lab11.util.JsonUtil;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserCacheDao {

    private static final String KEY_PATTERN = "user:%d"; // user:用户编号

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Resource(name="redisTemplate")
    private ValueOperations<String,String> operations;

    private static String buildKey(Integer id) {
        return String.format(KEY_PATTERN,id);
    }


    public void set(Integer id, UserCacheObject object) {
        String key = buildKey(id);
        String value = JsonUtil.toJSONString(object);
        operations.set(key, value);
    }
}
