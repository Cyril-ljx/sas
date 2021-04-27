package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.ClockService;
import com.lingnan.sas.core.entity.Clock;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.mapper.ClockDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * (Clock)表服务实现类
 *
 * @author makejava
 * @since 2021-03-11 16:26:40
 */
@Service("clockService")
public class ClockServiceImpl implements ClockService {
    @Resource
    private ClockDao clockDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HashMap queryById(Integer id) {
        return this.clockDao.queryById(id);
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
        List<Clock> clocks = clockDao.queryAllByLimit(offset, limit);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(clockDao.queryClockCount());//总数
        layData.setData(clocks);
        return layData;
    }

    /**
     * 新增数据
     *
     * @param clock 实例对象
     * @return 实例对象
     */
    @Override
    public Clock insert(Clock clock) {
        this.clockDao.insert(clock);
        return clock;
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.clockDao.deleteById(id) > 0;
    }
}