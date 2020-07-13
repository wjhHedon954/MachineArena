package com.whu.algorithm_description.controller;


import com.entity.AlgorithmDescription;
import com.whu.algorithm.service.IAlgorithmService;
import com.whu.algorithm_description.service.IAlgorithmDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@RestController
@RequestMapping("/algorithm-description/algorithm-description")
public class AlgorithmDescriptionController {
    @Autowired
    IAlgorithmDescriptionService algorithmDescriptionService;

    public AlgorithmDescription addDescription (AlgorithmDescription description) {
        algorithmDescriptionService.addDescription(description);
        return description;
    }
}
