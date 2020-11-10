//package com.study.current.config.redisson;
//
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.ClusterServersConfig;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@Slf4j
//public class RedissonConfig {
//    @Autowired
//    private RedisConfigProperties redisConfigProperties;
//
//    @Bean
//    public RedissonClient redissonClient() throws IOException {
//        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
//        List<String> clusterNodes = new ArrayList<>();
//        for (int i = 0; i < redisConfigProperties.getCluster().getNodes().size(); i++) {
//            String url ="redis://"+redisConfigProperties.getCluster().getNodes().get(i);
//            clusterNodes.add(url);
//        }
//        Config config = new Config();
//        // 添加集群地址
//        String[] strings = clusterNodes.toArray(new String[clusterNodes.size()]);
//        ClusterServersConfig clusterServersConfig = config.useClusterServers().addNodeAddress(strings);
//        // 设置密码
//        clusterServersConfig.setPassword(redisConfigProperties.getPassword());
//        RedissonClient redissonClient = Redisson.create(config);
//        return redissonClient;
//    }
//
////    @Value("${spring.redis.host}")
////    private String host;
////
////    @Value("${spring.redis.port}")
////    private String port;
////
////    @Value("${spring.redis.password}")
////    private String password;
////
////    @Bean
////    public RedissonClient getRedisson(){
////        Config config = new Config();
////        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
////        //添加主从配置
//////        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
////        return Redisson.create(config);
////    }
//
//}
//
