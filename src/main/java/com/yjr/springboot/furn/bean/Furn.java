package com.yjr.springboot.furn.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
//如果类名和表的名字无法映射，可以通过@TableName注解来映射
@TableName("furn")
public class Furn {
    @TableId(type = IdType.AUTO) //标识主键 并且自增
    private Integer id;
    @NotEmpty(message = "名称不能为空")
    private String name;
    @NotEmpty(message = "生产厂商不能为空")
    private String maker;
    @NotNull(message = "价格不能为空")
    @Range(min = 0,message = "价格不能小于0")
    private Double price;
    @NotNull
    private Integer sales;
    private Integer stock;
}
