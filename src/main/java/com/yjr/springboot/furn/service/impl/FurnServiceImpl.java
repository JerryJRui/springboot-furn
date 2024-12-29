package com.yjr.springboot.furn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjr.springboot.furn.bean.Furn;
import com.yjr.springboot.furn.mapper.FurnMapper;
import com.yjr.springboot.furn.service.FurnService;
import org.springframework.stereotype.Service;

@Service
public class FurnServiceImpl extends ServiceImpl<FurnMapper, Furn> implements FurnService {

}
