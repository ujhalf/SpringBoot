package com.half.boot05webadmin;

import org.junit.jupiter.api.*;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/29 15:47
 * @Version 1.0
 * @Description
 */
@DisplayName("junit5功能测试类")
public class Junit5Test {

    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有测试方法开始了");
    }
    @BeforeEach
    void testBeforeEach() {
        System.out.println("测试方法开始了^_^");
    }

    @DisplayName("测试displayName注解")
    @Test
    void testDisplayName() {
        System.out.println("测试方法1开始了");
    }

    @Disabled
    @DisplayName("测试方法2")
    @Test
   public void test2() {
        System.out.println("测试方法2开始了");
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("测试结束了");
    }

    @AfterAll
   static void testAfterAll() {
        System.out.println("所有测试方法结束了");
    }
}
