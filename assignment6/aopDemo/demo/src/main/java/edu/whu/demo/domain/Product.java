package edu.whu.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author susong
 * @since 2023-10-21
 */
@Data
@ApiModel(value="商品信息实体类")
@EqualsAndHashCode(callSuper = false)
@TableName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("唯一标识符")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("商品价格")
    private Integer price;
    @ApiModelProperty("商品名称")
    private String name;
    @ApiModelProperty("商品库存数量")
    private Integer num;
    @TableField(exist = false)
    @ApiModelProperty("供货商")
    private List<Integer> suppliers;
}
