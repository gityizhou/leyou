package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    /**
     * 根据分类id查询数组
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        return this.groupMapper.select(record);
    }

    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setGeneric(generic);
        specParam.setSearching(searching);
        return this.paramMapper.select(specParam);
    }

    public void addGroup(SpecGroup group) {
        groupMapper.insertSelective(group);
    }

    public void editGroup(SpecGroup group) {
        groupMapper.updateByPrimaryKeySelective(group);
    }

    public void deleteGroup(Long id) {
        groupMapper.deleteByPrimaryKey(id);
    }

    public void addParam(SpecParam specParam) {
        paramMapper.insertSelective(specParam);
    }

    public void editParam(SpecParam specParam) {
        paramMapper.updateByPrimaryKeySelective(specParam);
    }

    public void deleteParam(Long id) {
        paramMapper.deleteByPrimaryKey(id);
    }
}
