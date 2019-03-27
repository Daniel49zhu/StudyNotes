package com.zjc;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.apache.commons.collections4.functors.UniquePredicate;
import org.apache.commons.collections4.list.PredicatedList;
import org.junit.Test;

import java.util.ArrayList;

public class TestCollections {
    @Test
    public void predicate() {
        System.out.println("============自定义判断============");
        //自定义的判别式
        Predicate<String> selfPre = object -> object.length() >= 5 && object.length() <= 20;
        Predicate notNull = NotNullPredicate.notNullPredicate();
        Predicate uniquePre = UniquePredicate.uniquePredicate();

        Predicate all = PredicateUtils.allPredicate(notNull, selfPre,uniquePre);

        PredicatedList<String> list = PredicatedList.predicatedList(new ArrayList<String>(), all);
        list.add("bjsxt");
        list.add("bjsxt");
        list.add(null);
        list.add("bj");
    }


}
