package com.xylon.framework.dubbo;

/**
 * Created by wangxiong on 4/15/15.
 */
public class IServiceImpl implements  IService {
    @Override
    public String say() {
        return "hello dubbo!";
    }
}
