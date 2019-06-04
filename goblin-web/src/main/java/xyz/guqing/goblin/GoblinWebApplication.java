package xyz.guqing.goblin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.goblin.common.Result;

@SpringBootApplication
@MapperScan("xyz.guqing.goblin.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class GoblinWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoblinWebApplication.class, args);
    }

}
