package com.xylon.framework.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

/**
 * Created by wangxiong on 4/15/15.
 */
public class DubboProvider {
    public static void main(String[] args) {
        IService iService = new IServiceImpl();
// 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("iService");
// 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("10.86.145.231:2181");
//        registry.setUsername("aaa");
//        registry.setPassword("bbb");
// 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(12345);
//        protocol.setThreads(200);
// 注意:ServiceConfig 为重对象,内部封装了与注册中心的连接,以及开启服务端口
// 服务提供者暴露服务配置
        ServiceConfig<IService> service = new ServiceConfig<IService>(); // 此实例很重,封装了与注册中心的连接,请自行缓存,否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setTimeout(2000000);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setInterface(IService.class);
        service.setRef(iService);
        service.setVersion("1.0.0");
// 暴露及注册服务
        service.export();
    }
}
