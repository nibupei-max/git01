package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/product")
@Api(tags = "spuInfo 数据接口")
public class SpuManageController {
    @Autowired
    private ManageService manageService;
    //http://api.gmall.com/admin/product/ {page}/{limit}?category3Id=61
    /**
     * 获取spu分页列表
     */
    @GetMapping("{page}/{limit}")
    public Result getSpuList(@PathVariable Long page,
                             @PathVariable Long limit,
                             SpuInfo spuInfo){
        Page<SpuInfo> spuInfoPage = new Page(page,limit);
        IPage spuInfoPageLIst = manageService.getSpuInfoPage(spuInfoPage, spuInfo);
        return Result.ok(spuInfoPageLIst);
    }

    /**
     * 2、获取销售属性
     * 接口	http://api.gmall.com/admin/product/baseSaleAttrList
     */
    @GetMapping("baseSaleAttrList")
    public Result baseSaleAttrList(){
        return  Result.ok(manageService.getBaseSaleAttrList());
    }

    /**
     * 添加spu
     * 接口	http://api.gmall.com/admin/product/saveSpuInfo
     */
    @PostMapping("saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo ){
        manageService.saveSpuInfo(spuInfo);
        return Result.ok();
    }
}
