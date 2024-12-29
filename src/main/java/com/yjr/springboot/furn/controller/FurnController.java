package com.yjr.springboot.furn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjr.springboot.furn.bean.Furn;
import com.yjr.springboot.furn.service.FurnService;
import com.yjr.springboot.furn.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class FurnController {

    @Resource
    private FurnService furnService;


    @PostMapping("/save")
    public Result save(@Validated @RequestBody Furn furn, Errors errors){
        //如果出现错误，会把错误信息放入errors中
        HashMap<String, Object> map = new HashMap<>();
        //取出错误信息
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        //如果没有错误信息，则正常执行
        if (map.isEmpty()){
            log.info("furn:{}",furn);
            furnService.save(furn);
            return Result.success();
        }else {
            return Result.error(map,"400","后端校验失败");
        }

    }

    //返回所有的家居信息
    @RequestMapping("/furns")
    public Result listFurn(){
        List<Furn> furns = furnService.list();
        return Result.success(furns);
    }

    //处理修改
    @PutMapping("/update")
    public Result update(@RequestBody Furn furn){
        furnService.updateById(furn);
        return Result.success();
    }

    //处理删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        furnService.removeById(id);
        return Result.success();
    }

    //根据id查询
    @GetMapping("/furn/{id}")
    public Result getById(@PathVariable Integer id){
        Furn furn = furnService.getById(id);
        return Result.success(furn);
    }

    //分页查询
    @GetMapping("/furnsByPage")
    public Result listFurnsByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize){
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize));
        return Result.success(page);
    }

    //带条件的分页检索
    @GetMapping("/furnsByConditionPage")
    public Result listFurnsByConditionPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "5") Integer pageSize,
                                           @RequestParam(defaultValue = "") String search){
        //先创建QueryWrapper对象，可以将检索条件封装到QueryWrapper
        QueryWrapper<Furn> query = Wrappers.query();
        if(StringUtils.hasText(search)){
            query.like("name",search);
        }
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize),query);
        return Result.success(page);
    }

    //使用LambdaQueryWrapper
    @GetMapping("/furnsByConditionPage2")
    public Result listFurnsByConditionPage2(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "5") Integer pageSize,
                                           @RequestParam(defaultValue = "") String search){

        LambdaQueryWrapper<Furn> furnLambdaQueryWrapper = Wrappers.<Furn>lambdaQuery();

        if(StringUtils.hasText(search)){
            //类名::实例方法
            furnLambdaQueryWrapper.like(Furn::getName,search);
        }
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize),furnLambdaQueryWrapper);
        return Result.success(page);
    }
}
