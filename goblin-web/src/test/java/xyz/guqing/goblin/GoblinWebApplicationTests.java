package xyz.guqing.goblin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.guqing.goblin.service.ArticleService;
import xyz.guqing.goblin.service.CategoryService;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoblinWebApplicationTests {
    @Resource
    private CategoryService categoryService;
    @Resource
    private ArticleService articleService;
    @Test
    public void contextLoads() throws Exception {
//        System.out.println(categoryService.getClass());
        categoryService.moveUp(4);
    }

}
