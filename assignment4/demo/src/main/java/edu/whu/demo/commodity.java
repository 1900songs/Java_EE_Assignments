package edu.whu.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(value="商品信息实体类")
@Data
public class commodity {
    @ApiModelProperty("唯一标识符")
    Long id;
    @ApiModelProperty("商品名称")
    String name;
    @ApiModelProperty("商品价格")
    Long price;
    @ApiModelProperty("商品库存数量")
    Long num;
}
