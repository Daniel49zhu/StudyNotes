package com.zjc.spring.springinaction;

public interface IFooService {
    void insertRecord();

    void insertThenRollback() throws Exception;

    void invokeInsertThenRollback() throws Exception;
}
