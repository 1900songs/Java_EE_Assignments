package edu.whu.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import edu.whu.demo.DemoApplication;
import edu.whu.demo.dao.ProductDao;
import edu.whu.demo.dao.SupplierDao;
import edu.whu.demo.domain.Product;
import edu.whu.demo.domain.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = DemoApplication.class)
@Transactional
class ProductServiceImplTest {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private SupplierDao supplierDao;
    @BeforeEach
    void init(){
        Product p1= new Product();
        p1.setName("coke");
        p1.setPrice(10);
        p1.setNum(8);
        productDao.insert(p1);

        Product p2= new Product();
        p2.setName("apple");
        p2.setPrice(5);
        p2.setNum(6);
        productDao.insert(p2);

        Product p3= new Product();
        p3.setName("jacket");
        p3.setPrice(100);
        p3.setNum(2);
        productDao.insert(p3);

        Product p4= new Product();
        p4.setName("machine");
        p4.setPrice(1000);
        p4.setNum(0);
        productDao.insert(p4);

        Supplier s1 = new Supplier();
        s1.setName("京东");
        supplierDao.insert(s1);

        Supplier s2 = new Supplier();
        s2.setName("淘宝");
        supplierDao.insert(s2);

        Supplier s3 = new Supplier();
        s3.setName("拼多多");
        supplierDao.insert(s3);

        supplierDao.addSupplierToProduct(p1.getId(),s1.getId());
        supplierDao.addSupplierToProduct(p1.getId(),s2.getId());
        supplierDao.addSupplierToProduct(p1.getId(),s3.getId());

        supplierDao.addSupplierToProduct(p2.getId(),s1.getId());
        supplierDao.addSupplierToProduct(p2.getId(),s2.getId());

        supplierDao.addSupplierToProduct(p3.getId(),s2.getId());
        supplierDao.addSupplierToProduct(p3.getId(),s3.getId());

        supplierDao.addSupplierToProduct(p4.getId(),s1.getId());
    }
    @Test
    void addProduct() {
        Product p= new Product();
        p.setName("medicine");
        p.setPrice(10);
        p.setNum(2);
        productDao.insert(p);

        String name = productDao.selectById(p.getId()).getName();
        assertEquals("medicine",name);

        int count = productDao.selectList(null).size();
        assertEquals(5,count);

        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.ge(Product::getPrice,200);
        int num = productDao.selectList(lqw).size();
        assertEquals(1,num);
    }

    @Test
    void removeProduct() {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.ge(Product::getNum,3).or().le(Product::getPrice,10);
        productDao.delete(lqw);
        int count = productDao.selectList(null).size();

        assertEquals(2,count);
    }

    @Test
    void updateProduct() {
         LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
         lqw.eq(Product::getNum,0);
         Product p= productDao.selectOne(lqw);
         p.setNum(10);
         productDao.updateById(p);

         int num = productDao.selectById(p.getId()).getNum();

         assertEquals(10,num);
    }

    @Test
    void findEmpty() {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product::getNum,0);
        String name = productDao.selectOne(lqw).getName();
        assertEquals("machine",name);
    }

    @Test
    void findAllSuppliers() {
        List<Product> mapList = productDao.findAllProductSuppliers();
        assertEquals(4,mapList.size());
        Product p= mapList.get(0);
        assertEquals(3,p.getSuppliers().size());
    }

    @Test
    void findSuppliers() {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Product::getName,"coke");
        Product p=productDao.selectOne(lqw);
        List<Supplier> supplierList1 = productDao.findAllSupplierByproductId(p.getId());
        assertEquals(3,supplierList1.size());

        LambdaQueryWrapper<Product> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(Product::getName,"apple");
        Product p2=productDao.selectOne(lqw2);
        List<Supplier> supplierList2 = productDao.findAllSupplierByproductId(p2.getId());
        assertEquals(2,supplierList2.size());

        LambdaQueryWrapper<Product> lqw3 = new LambdaQueryWrapper<>();
        lqw3.eq(Product::getName,"jacket");
        Product p3=productDao.selectOne(lqw3);
        List<Supplier> supplierList3 = productDao.findAllSupplierByproductId(p3.getId());
        assertEquals(2,supplierList3.size());

        LambdaQueryWrapper<Product> lqw4 = new LambdaQueryWrapper<>();
        lqw4.eq(Product::getName,"machine");
        Product p4=productDao.selectOne(lqw4);
        List<Supplier> supplierList4 = productDao.findAllSupplierByproductId(p4.getId());
        assertEquals(1,supplierList4.size());

    }
}