package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TPoetryType;
import com.springboot.entity.VPoetry;
import com.springboot.service.PoetryTypeService;
import com.springboot.service.VPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class PoetryTypeController {
    @Autowired
    PoetryTypeService poetryTypeService;
    @Autowired
    VPoetryService vPoetryService;
    @GetMapping("/listallpoetrytype")
    @ResponseBody
    public Result listallpoetrytype(){
        Result result = new Result();
        List<TPoetryType> list= poetryTypeService.getAllPoetryType();
        //放到data中
        result.setData(list);
        return result;
    }
    @PostMapping("/listallpoetrytypebyid")
    @ResponseBody
    public Result listallpoetrytypebyid(@RequestBody String id){
        Result result = new Result();
        long id1=Integer.valueOf(id);
        List<VPoetry> list= vPoetryService.showAllByTypeId(id1);
        //放到data中
        result.setData(list);
        return result;
    }


    //管理员功能
    @GetMapping("/admin/listpoetrytypes")
    @ResponseBody
    public Result adminlistalltype(String kw, Model model){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        Result result = new Result();
        //通过关键字查询
        List<TPoetryType> listtypes= poetryTypeService.adminshowAll(kw);
        //放到data中
        result.setData(listtypes);
        return result;
    }
    @PostMapping("/admin/listpoetrytypes")
    @ResponseBody
    public Result adminlistalltypebykw(@RequestBody String kw){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        Result result = new Result();
        //通过关键字查询
        List<TPoetryType> listtypes= poetryTypeService.adminshowAll(kw);
        //放到data中
        result.setData(listtypes);
        return result;
    }

    @PostMapping("/admin/deletepoetrytype")
    @ResponseBody
    public Result admindelete(@RequestBody String id){
        Result result = new Result();
        long id1=Integer.valueOf(id);
        poetryTypeService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过关键字查询
        List<TPoetryType> listtypes= poetryTypeService.adminshowAll(kw);
        //放到data中
        result.setData(listtypes);
        return result;
    }


    //管理员添加诗词类型
    @PostMapping("/admin/addtype")
    @ResponseBody
    public Result addType(TPoetryType poetryType) throws ParseException {
        Result result=new Result();
        poetryTypeService.AddPoetryType(poetryType);
        result.setDescription("添加成功");//添加返回信息描述
        result.setData(poetryType);
        return  result;
    }

    //管理员修改诗词类型
    @PostMapping("/admin/modifytype")
    @ResponseBody
    public Result modifyType(TPoetryType poetryType) throws ParseException {
        Result result=new Result();
        System.out.println("前端传来的数据poetryType====="+poetryType.toString());//前端传来的数据
        poetryTypeService.AddPoetryType(poetryType);
        result.setDescription("添加成功");//添加返回信息描述
        result.setData(poetryType);
        return  result;
    }
}
