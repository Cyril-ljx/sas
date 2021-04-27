package com.lingnan.sas.admin.service;


import com.lingnan.sas.core.entity.Newsinfo;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;

import java.util.List;

/**
 * 公告(Newsinfo)表服务接口
 *
 * @author makejava
 * @since 2021-03-09 19:06:59
 */
public interface NewsinfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param nid 主键
     * @return 实例对象
     */
    Newsinfo queryById(Integer nid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
   LayuiVO queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param newsinfo 实例对象
     * @return 实例对象
     */
    Response insert(Newsinfo newsinfo);

    /**
     * 修改数据
     *
     * @param newsinfo 实例对象
     * @return 实例对象
     */
    Newsinfo update(Newsinfo newsinfo);

    /**
     * 通过主键删除数据
     *
     * @param nid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer nid);

}