package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.DiretionService;
import com.lingnan.sas.core.entity.Diretion;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.mapper.DiretionDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Diretion)表服务实现类
 *
 * @author makejava
 * @since 2021-03-11 16:26:40
 */
@Service("diretionService")
public class DiretionServiceImpl implements DiretionService {
    @Resource
    private DiretionDao diretionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Diretion queryById(Integer id) {
        return this.diretionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public LayuiVO queryAllByLimit(int offset, int limit) {
        List<Diretion> diretions= diretionDao.queryAllByLimit(offset, limit);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(diretionDao.queryDiretionCount());//总数
        layData.setData(diretions);
        return layData;
    }

    /**
     * 新增数据
     *
     * @param diretion 实例对象
     * @return 实例对象
     */
    @Override
    public Diretion insert(Diretion diretion) {
        this.diretionDao.insert(diretion);
        return diretion;
    }

    /**
     * 修改数据
     *
     * @param diretion 实例对象
     * @return 实例对象
     */
    @Override
    public Diretion update(Diretion diretion) {
        this.diretionDao.update(diretion);
        return this.queryById(diretion.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.diretionDao.deleteById(id) > 0;
    }
}