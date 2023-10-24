package edu.whu.demo.dao;

import edu.whu.demo.domain.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author susong
 * @since 2023-10-21
 */
@Mapper
@Repository
public interface SupplierDao extends BaseMapper<Supplier> {
    @Insert("INSERT INTO product_supplier(product_id,supplier_id) VALUES (#{product_id},#{supplier_id})")
    public void addSupplierToProduct(int product_id,int supplier_id);
}
