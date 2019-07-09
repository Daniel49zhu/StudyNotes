package com.zjc.test;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Arrays;

public class TestSpelApplication {

    @Test
    public void test1() {
        ExpressionParser parser = new SpelExpressionParser();
        System.out.println(parser.parseExpression("'Hello World!'").getValue());
        System.out.println(parser.parseExpression("'Hello World'.concat('!')").getValue());
        System.out.println(Arrays.toString((byte[]) parser.parseExpression("'Hello World!'.bytes").getValue()));
        System.out.println( parser.parseExpression("'Hello World!'.bytes.length").getValue());
        System.out.println( parser.parseExpression("new String('Hello World!').toUpperCase()").getValue());
    }
}
