package br.com.letscode.produtoapi.config;

import br.com.letscode.produtoapi.produto.dto.ProdutoDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, ProdutoDTO> redisTemplate() {
        RedisTemplate<String, ProdutoDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());

        RedisSerializer<String> serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);

        return template;
    }



}
