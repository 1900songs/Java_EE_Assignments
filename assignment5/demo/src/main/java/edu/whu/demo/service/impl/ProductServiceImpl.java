package edu.whu.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.whu.demo.domain.Product;
import edu.whu.demo.dao.ProductDao;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author susong
 * @since 2023-10-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements IProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product addProduct(Product myProduct) {
        productDao.insert(myProduct);
        return myProduct;
    }

    @Override
    public void removeProduct(int id) {
        productDao.deleteById(id);
    }

    @Override
    public void updateProduct(Product myProduct) {
        productDao.updateById(myProduct);
    }

    @Override
    public Product getProduct(int id) {
        Product product=productDao.selectById(id);
        return product;
    }

    @Override
    public List<Product> findEmpty() {
        LambdaQueryWrapper<Product> qw = new LambdaQueryWrapper<>();
        qw.eq(Product::getNum,0);
        List<Product> emptyProductList= productDao.selectList(qw);
        return emptyProductList;
    }

    @Override
    public List<Product> findAllSuppliers() {
        List<Product> list = productDao.findAllProductSuppliers();
        return list;
    }

    @Override
    public List<Supplier> findSuppliers(int id) {
        List<Supplier> supplierList=productDao.findAllSupplierByproductId(id);
        return supplierList;
    }
}
