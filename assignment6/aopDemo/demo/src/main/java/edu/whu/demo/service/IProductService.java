package edu.whu.demo.service;

import edu.whu.demo.dao.ProductDao;
import edu.whu.demo.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.demo.domain.Supplier;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author susong
 * @since 2023-10-21
 */
public interface IProductService extends IService<Product> {
    public Product addProduct(Product myProduct);

    public void removeProduct(int id);

    public void updateProduct(Product myProduct);

    public Product getProduct(int id);

    public List<Product> findEmpty();

    public List<Product> findAllSuppliers();

    public List<Supplier> findSuppliers(int id);
}