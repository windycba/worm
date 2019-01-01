package com.wei.worm.controller;

import com.alibaba.fastjson.JSONObject;
import com.wei.entity.DoubleColorBall;
import com.wei.service.DoubleColorBallService;
import com.wei.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doubleColorBall")
public class DoubleColorBallController {
    @Autowired
    private DoubleColorBallService doubleColorBallService;

    @RequestMapping("/save")
    public Map<String,Object> save(@RequestParam String item){
        Map<String,Object> map=new HashMap<String,Object>();
        DoubleColorBall doubleColorBall=JSONObject.parseObject(item,DoubleColorBall.class);
        doubleColorBallService.save(doubleColorBall);
        map.put("success",true);
        return map;
    }

    @RequestMapping("/delete")
    public Map<String,Object> delete(@RequestParam String ids){
        Map<String,Object> map=new HashMap<String,Object>();
        List<Integer> param=JSONObject.parseArray(ids,Integer.class);
        doubleColorBallService.delete(param);
        map.put("success",true);
        return map;
    }
    @RequestMapping("/query")
    public Page<DoubleColorBall> query(@RequestParam int page, @RequestParam int rows, @RequestParam(required = false) String param){
        JSONObject  params=new JSONObject();
        if(param!=null){
            params=JSONObject.parseObject(param);
        }
        return doubleColorBallService.query(rows,page,params);
    }

    @RequestMapping("/import")
    public String  springUpload(HttpServletRequest request){
        doubleColorBallService.doUpload(request);
        return "success";
    }
}
