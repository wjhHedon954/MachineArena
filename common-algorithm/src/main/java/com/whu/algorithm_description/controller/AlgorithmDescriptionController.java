package com.whu.algorithm_description.controller;


import com.entity.AlgorithmDescription;
import com.whu.algorithm.service.IAlgorithmService;
import com.whu.algorithm_description.service.IAlgorithmDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@RestController
public class AlgorithmDescriptionController {
    @Autowired
    IAlgorithmDescriptionService algorithmDescriptionService;

    @PostMapping("/algorithm/description")
    public AlgorithmDescription addDescription (@RequestBody AlgorithmDescription description) {
        System.out.println("description in controller: \n" + description.toString());
        algorithmDescriptionService.addDescription(description);
        return description;
    }
}
