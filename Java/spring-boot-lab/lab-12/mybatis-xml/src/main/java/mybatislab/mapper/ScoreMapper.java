package mybatislab.mapper;

import mybatislab.dataobject.ScoreDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreMapper {
    ScoreDO selectById(@Param("id") Integer id);
}
