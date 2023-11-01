package edu.whu.demo.dao;

import edu.whu.demo.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.demo.domain.Supplier;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author susong
 * @since 2023-10-21
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {
    @Select("SELECT * FROM product")
    @Results({
            @Result(column = "name",property = "name"),
            @Result(column = "id",property = "suppliers",many = @Many(select = "edu.whu.demo.dao.ProductDao.findAllSupplierByproductId"))
    })
    List<Product> findAllProductSuppliers();
    @Select("SELECT supplier.* FROM product_supplier,supplier WHERE product_supplier.supplier_id=supplier.id and product_supplier.product_id=#{productId}")
    List<Supplier> findAllSupplierByproductId(int productId);
}
