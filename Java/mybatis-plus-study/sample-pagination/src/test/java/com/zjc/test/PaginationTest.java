package com.zjc.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjc.entity.User;
import com.zjc.mapper.UserMapper;
import com.zjc.model.MyPage;
import com.zjc.model.ParamSome;
import com.zjc.model.UserChildren;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class PaginationTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void lambdaPagination() {
        Page<User> page = new Page<>(1, 3);
        Page<User> result = mapper.selectPage(page, Wrappers.<User>lambdaQuery().ge(User::getAge, 1).orderByAsc(User::getAge));
        assertThat(result.getTotal()).isGreaterThan(3);
        assertThat(result.getRecords().size()).isEqualTo(3);
    }


    @Test
    public void tests1() {
        log.error("----------------------------------baseMapper 自带分页-------------------------------------------------------");
        Page<User> page = new Page<>(1, 5);
        page.addOrder(OrderItem.asc("age"));
        Page<User> userIPage = mapper.selectPage(page, Wrappers.<User>lambdaQuery().eq(User::getAge, 20).like(User::getName, "Jack"));
        assertThat(page).isSameAs(userIPage);
        log.error("总条数 -------------> {}", userIPage.getTotal());
        log.error("当前页数 -------------> {}", userIPage.getCurrent());
        log.error("当前每页显示数 -------------> {}", userIPage.getSize());
        List<User> records = userIPage.getRecords();
        assertThat(records).isNotEmpty();

        log.error("----------------------------------json 正反序列化-------------------------------------------------------");
        String json = JSON.toJSONString(page);
        log.info("json ----------> {}", json);
        Page<User> page1 = JSON.parseObject(json, new TypeReference<Page<User>>() {
        });
        List<User> records1 = page1.getRecords();
        assertThat(records1).isNotEmpty();
        assertThat(records1.get(0).getClass()).isEqualTo(User.class);

        log.error("----------------------------------自定义 XML 分页-------------------------------------------------------");
        MyPage<User> myPage = new MyPage<User>(1, 5).setSelectInt(20).setSelectStr("Jack");
        ParamSome paramSome = new ParamSome(20, "Jack");
        MyPage<User> userMyPage = mapper.mySelectPage(myPage, paramSome);
        assertThat(myPage).isSameAs(userMyPage);
        log.error("总条数 -------------> {}", userMyPage.getTotal());
        log.error("当前页数 -------------> {}", userMyPage.getCurrent());
        log.error("当前每页显示数 -------------> {}", userMyPage.getSize());
    }

    @Test
    public void tests2() {
        /* 下面的 left join 不会对 count 进行优化,因为 where 条件里有 join 的表的条件 */
        MyPage<UserChildren> myPage = new MyPage<>(1, 5);
        myPage.setSelectInt(18).setSelectStr("Jack");
        MyPage<UserChildren> userChildrenMyPage = mapper.userChildrenPage(myPage);
        List<UserChildren> records = userChildrenMyPage.getRecords();
        records.forEach(System.out::println);

        /* 下面的 left join 会对 count 进行优化,因为 where 条件里没有 join 的表的条件 */
        myPage = new MyPage<UserChildren>(1, 5).setSelectInt(18);
        userChildrenMyPage = mapper.userChildrenPage(myPage);
        records = userChildrenMyPage.getRecords();
        records.forEach(System.out::println);
    }
}
