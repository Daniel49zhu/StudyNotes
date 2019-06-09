package com.zjc;

import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Test;

public class TestConvertUtils {

    @Test
    public void testConvert() {
        System.out.println(ConvertUtils.convert(null));

        System.out.println(ConvertUtils.convert("12",Long.class));


    }
}
