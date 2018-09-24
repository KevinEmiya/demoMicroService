package com.ky.elasticsearch;

import com.ky.elasticsearch.domain.es.EsBlog;
import com.ky.elasticsearch.repository.es.EsBlogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Before
    public void init(){
        esBlogRepository.deleteAll();

        esBlogRepository.save(new EsBlog("测试", "测试中文", "这是一篇测试的文章"));
        esBlogRepository.save(new EsBlog("test", "test word", "This is a test article"));
        esBlogRepository.save(new EsBlog("normal", "normal blog", "This is a normal blog"));
    }

    @Test
    public void testSearch(){
        PageRequest pageRequest = PageRequest.of(0, 20);
        String title = "测试";
        String summary = "中文";
        String content = "文章";
        Page<EsBlog> page = esBlogRepository.findByTitleContainingOrSummaryContainingOrContentContaining(
                title, summary, content, pageRequest);
        System.out.println("中文：");
        for(EsBlog esBlog : page){
            System.out.println(esBlog.toString());
        }

        title = "test";
        summary = "word";
        content = "article";
        page = esBlogRepository.findByTitleContainingOrSummaryContainingOrContentContaining(
                title, summary, content, pageRequest);
        System.out.println("英文1: ");
        for(EsBlog esBlog : page){
            System.out.println(esBlog.toString());
        }

        content = "blog";
        page = esBlogRepository.findByTitleContainingOrSummaryContainingOrContentContaining(
                title, summary, content, pageRequest);
        System.out.println("英文2: ");
        for(EsBlog esBlog : page){
            System.out.println(esBlog.toString());
        }
    }
}
