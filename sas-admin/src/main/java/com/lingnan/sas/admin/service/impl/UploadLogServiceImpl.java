package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.UploadLogService;
import com.lingnan.sas.core.entity.UploadLog;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.mapper.UploadLogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UploadLog)表服务实现类
 *
 * @author makejava
 * @since 2021-03-11 16:26:39
 */
@Service("uploadLogService")
public class UploadLogServiceImpl implements UploadLogService {
    @Resource
    private UploadLogDao uploadLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UploadLog queryById(Integer id) {
        return this.uploadLogDao.queryById(id);
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
        List<UploadLog> uploadLogs = uploadLogDao.queryAllByLimit(offset, limit);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(uploadLogDao.queryUploadCount());//总数
        layData.setData(uploadLogs);
        return layData;
    }

    /**
     * 新增数据
     *
     * @param uploadLog 实例对象
     * @return 实例对象
     */
    @Override
    public UploadLog insert(UploadLog uploadLog) {
        this.uploadLogDao.insert(uploadLog);
        return uploadLog;
    }

    /**
     * 修改数据
     *
     * @param uploadLog 实例对象
     * @return 实例对象
     */
    @Override
    public UploadLog update(UploadLog uploadLog) {
        this.uploadLogDao.update(uploadLog);
        return this.queryById(uploadLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.uploadLogDao.deleteById(id) > 0;
    }
}