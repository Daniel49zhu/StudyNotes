package mybatislab;


import mybatislab.dataobject.ScoreDO;
import mybatislab.mapper.ScoreMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ScoreMapperTest {

    @Autowired
    private ScoreMapper scoreMapper;

    @Test
    public void testSelectById() {
        ScoreDO scoreDO = scoreMapper.selectById(1);
        System.out.println(scoreDO);
    }
}
