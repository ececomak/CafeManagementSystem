package com.inn.cafe.restImpl;

import com.inn.cafe.constants.CafeConstants;
import com.inn.cafe.rest.CategoryRest;
import com.inn.cafe.service.CategoryService;
import com.inn.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryRestImpl implements CategoryRest {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<String> addNewCategory(@RequestBody Map<String, String> requestMap) {
        try {
            return categoryService.addNewCategory(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}