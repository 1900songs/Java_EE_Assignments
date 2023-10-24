package edu.whu.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api("商品管理")
@RestController
@RequestMapping("/commodities")
public class cmsController {
    @Autowired
    CommodityManagementSystem cms;
    @ApiOperation("提供id和请求体添加商品")
    @PostMapping("")
    public ResponseEntity<commodity> addCommodity(@ApiParam("请求体")@RequestBody commodity myCommodity){
        commodity result=cms.addCommodity(myCommodity);
        return ResponseEntity.ok(result);
    }
    @ApiOperation("根据id删除对应商品信息")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommodity(@PathVariable long id){
        cms.removeCommodity(id);
        return ResponseEntity.ok().build();
    }
    @ApiOperation("根据id更新对应商品信息")
    @PostMapping("/{id}")
    public ResponseEntity<Void> updateCommodity(@PathVariable long id,@RequestBody commodity myCommodity){
        cms.updateCommodity(id,myCommodity);
        return ResponseEntity.ok().build();
    }
    @ApiOperation("根据id查找对应商品信息")
    @GetMapping("/{id}")
    public ResponseEntity<commodity> getCommodity(@PathVariable long id){
        commodity nowCommodity = cms.getCommodity(id);
        return nowCommodity==null? ResponseEntity.noContent().build():ResponseEntity.ok(nowCommodity);
    }
    @ApiOperation("查找售空的商品信息")
    @GetMapping("")
    public ResponseEntity<List<commodity>> findEmptyCommodity(){
        List<commodity> commodityList = cms.findEmpty();
        return ResponseEntity.ok(commodityList);
    }
}
