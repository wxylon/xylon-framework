package com.xylon.framework.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * Created by wangxiong on 4/15/15.
 */
public class DubboCustomer {
    public static void main(String[] args) {
// 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("yyy");
// 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("10.86.145.231:2181");
//        registry.setUsername("aaa");
//        registry.setPassword("bbb");
// 注意:ReferenceConfig 为重对象,内部封装了与注册中心的连接,以及与服务提供方的连接
// 引用远程服务
        ReferenceConfig<IService> reference = new ReferenceConfig<IService>(); // 此实例很重,封装了与注册中心的连接以及与提供者的连接,请自行缓存,否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(IService.class);
        reference.setVersion("1.0.0");
// 和本地bean 一样使用xxxService
        IService iService = reference.get(); // 注意:此代理对象内部封装了所有通讯细节,对象较重,请缓存复用
    }
}
