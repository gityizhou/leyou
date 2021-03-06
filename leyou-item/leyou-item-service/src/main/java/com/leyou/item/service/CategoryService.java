package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点查询子节点
     * @param pid
     * @return
     */
    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return categoryMapper.select(record);
    }

    public void saveCategory(Category category) {

        categoryMapper.insertSelective(category);
    }

    public void editCategory(Long id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    public void deleteCategory(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    public List<Category> queryByBrandId(Long bid) {
        return this.categoryMapper.queryByBrandId(bid);
    }

    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> categories = this.categoryMapper.selectByIdList(ids);
        List<String> collect = categories.stream().map(category -> {
            String name = category.getName();
            return name;
        }).collect(Collectors.toList());
        return collect;
    }
}
