package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.FinanceService;
import com.lingnan.sas.core.entity.Finance;
import com.lingnan.sas.mapper.FinanceDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Finance)表服务实现类
 *
 * @author makejava
 * @since 2021-03-11 16:26:40
 */
@Service("financeService")
public class FinanceServiceImpl implements FinanceService {
    @Resource
    private FinanceDao financeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Finance queryById(Integer id) {
        return this.financeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Finance> queryAllByLimit(int offset, int limit) {
        return this.financeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param finance 实例对象
     * @return 实例对象
     */
    @Override
    public Finance insert(Finance finance) {
        this.financeDao.insert(finance);
        return finance;
    }

    /**
     * 修改数据
     *
     * @param finance 实例对象
     * @return 实例对象
     */
    @Override
    public Finance update(Finance finance) {
        this.financeDao.update(finance);
        return this.queryById(finance.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.financeDao.deleteById(id) > 0;
    }
}