package edu.whu.demo.controller;


import edu.whu.demo.dao.SupplierDao;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.service.impl.SupplierServiceImpl;
import edu.whu.demo.service.impl.SupplierServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author susong
 * @since 2023-10-21
 */
@RestController
@Api("供应商管理")
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierServiceImpl ssl;
    @ApiOperation("提供id和请求体添加供应商")
    @PostMapping("")
    public ResponseEntity<Supplier> addSupplier(@ApiParam("请求体")@RequestBody Supplier mySupplier){
        Supplier result=ssl.addSupplier(mySupplier);
        return ResponseEntity.ok(result);
    }
    @ApiOperation("根据id删除对应供应商信息")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int id){
        ssl.removeSupplier(id);
        return ResponseEntity.ok().build();
    }
    @ApiOperation("根据id更新对应供应商信息")
    @PostMapping("/{id}")
    public ResponseEntity<Void> updateSupplier(@RequestBody Supplier mySupplier){
        ssl.updateSupplier(mySupplier);
        return ResponseEntity.ok().build();
    }
    @ApiOperation("根据id查找对应供应商信息")
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable int id){
        Supplier nowSupplier = ssl.getSupplier(id);
        return nowSupplier==null? ResponseEntity.noContent().build():ResponseEntity.ok(nowSupplier);
    }
}

