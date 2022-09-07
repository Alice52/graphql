package io.github.alice52.graphql;

import common.redis.config.ExcludeRedisConfig;
import common.swagger.annotation.EnableSwagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author zack <br>
 * @create 2022-09-07<br>
 * @project graphql <br>
 */
@EnableSwagger
@Import(ExcludeRedisConfig.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RabbitAutoConfiguration.class})
public class GraphqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }
}
