package com.leyou.item.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;


    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid")Long cid){
        List<SpecGroup> groups = this.specificationService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }


    @PostMapping("group")
    public ResponseEntity<Void> addGroup(SpecGroup group) {
        specificationService.addGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("group")
    public ResponseEntity<Void> editGroup(@RequestParam("id") Long id, @RequestParam("name") String name) {
        SpecGroup group = new SpecGroup();
        group.setId(id);
        group.setName(name);
        specificationService.editGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("group/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("id") Long id) {
        specificationService.deleteGroup(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据gid查询具体规格参数
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(@RequestParam(value = "gid", required = false) Long gid,
                                                       @RequestParam(value = "cid", required = false) Long cid,
                                                       @RequestParam(value = "generic", required = false) Boolean generic,
                                                       @RequestParam(value = "searching", required = false) Boolean searching) {
        List<SpecParam> params = this.specificationService.queryParams(gid, cid, generic, searching);
        if (CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }

    @PostMapping("param")
    public ResponseEntity<Void> addParam(SpecParam specParam) {
        specificationService.addParam(specParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("param")
    public ResponseEntity<Void> editParam(SpecParam specParam) {
        specificationService.editParam(specParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteParam(@PathVariable("id") Long id) {
        specificationService.deleteParam(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }




}