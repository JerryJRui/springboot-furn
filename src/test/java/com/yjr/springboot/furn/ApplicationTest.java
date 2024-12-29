package com.yjr.springboot.furn;

import com.yjr.springboot.furn.bean.Furn;
import com.yjr.springboot.furn.mapper.FurnMapper;
import com.yjr.springboot.furn.service.FurnService;
import com.yjr.springboot.furn.service.impl.FurnServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ApplicationTest {
    @Resource
    private FurnMapper furnMapper;

    @Resource
    private FurnService furnService;
    @Test
    public void test(){
        System.out.println("furnMapper--" + furnMapper.selectById(4));
    }

    @Test
    public void test2(){
        List<Furn> list = furnService.list();
        for (Furn furn : list) {
            System.out.println(furn);
        }

    }
}
