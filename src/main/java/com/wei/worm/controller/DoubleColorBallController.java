package com.wei.worm.controller;

import com.wei.worm.service.DoubleColorBallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doubleColorBall")
public class DoubleColorBallController {
    @Autowired
    private DoubleColorBallService doubleColorBallService;


}
