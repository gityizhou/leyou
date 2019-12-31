package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点的id查询子节点
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {

            if (pid == null || pid < 0) {
                // 400 参数不合法
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                return ResponseEntity.badRequest().build();
            }
            List<Category> categories = this.categoryService.queryCategoriesByPid(pid);
            if (CollectionUtils.isEmpty(categories)){
                // 404 资源服务器未找到
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                return ResponseEntity.notFound().build();
            }
            // 200 查询不成功
            return ResponseEntity.ok(categories);

        // 500 服务器内部错误
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping("add")
    public ResponseEntity<Void> addCategory(Category category){

        this.categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("edit")
    public ResponseEntity<Void> editCategory(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name){
        this.categoryService.editCategory(id, name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteCategory(@RequestParam(value = "id") Long id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid){
        List<Category> list = this.categoryService.queryByBrandId(bid);
        if (list == null || list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

}
