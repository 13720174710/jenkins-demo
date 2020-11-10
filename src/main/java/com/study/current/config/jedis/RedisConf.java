package com.study.current.config.jedis;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.current.util.RedisUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Data
public class RedisConf {

    @Value(value = "${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value(value = "${spring.redis.jedis.pool.max-wait}")
    private long maxWait;

    @Value(value = "${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value(value = "${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value(value = "${spring.redis.commandTimeout}")
    private int commandTimeout;

    @Value(value = "${spring.redis.cluster.nodes}")
    private String nodes;

    @Value(value = "${spring.redis.cluster.max-redirects}")
    private int maxRedirects;


    @Value(value = "${spring.redis.jedis.pool.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value(value = "${spring.redis.jedis.pool.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun;

    @Value(value = "${spring.redis.jedis.pool.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value(value = "${spring.redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value(value = "${spring.redis.testWhileIdle}")
    private boolean testWhileIdle;


    @Value(value = "${spring.redis.password}")
    private String password;


    @Value(value = "${spring.redis.database}")
    private int database;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(minIdle);
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxActive);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        //     jedisPoolConfig.set
        return jedisPoolConfig;
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();

        //Set<RedisNode> clusterNodes
        String[] serverArray = null;
        if (nodes.contains(",")) {
            serverArray = nodes.split(",");
        } else {
            serverArray = new String[]{nodes};
        }
        Set<RedisNode> nodes = new HashSet<RedisNode>();
        for (String ipPort : serverArray) {
            String[] ipAndPort = ipPort.split(":");
            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
        }

        if (StringUtils.isNotEmpty(password)) {
            redisClusterConfiguration.setPassword(RedisPassword.of(password));
        }

        redisClusterConfiguration.setClusterNodes(nodes);
        redisClusterConfiguration.setMaxRedirects(maxRedirects);
        return redisClusterConfiguration;
    }

    @Bean("jedisConnectionFactory")
    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig, RedisClusterConfiguration redisClusterConfiguration) {
        JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
        return JedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }


    @Bean
    RedisMessageListenerContainer container(@Qualifier(value = "jedisConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

        /*@Bean
        MessageListenerAdapter listenerAdapter(MessageReceive receiver) {
            //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
            //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
            return new MessageListenerAdapter(receiver, "receiveMessage");
        }*/


}
