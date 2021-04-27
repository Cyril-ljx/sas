package com.lingnan.sas.admin.service;

import com.lingnan.sas.core.entity.Classinfo;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;

/**
 * 班级信息(Classinfo)表服务接口
 *
 * @author makejava
 * @since 2021-03-14 19:59:09
 */
public interface ClassinfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param classid 主键
     * @return 实例对象
     */
    Classinfo queryById(Integer classid);

    /**
     * 查询多条数据
     *
     * @param schoolManageVO 学校管理
     *
     * @return 对象列表
     */
    LayuiVO queryAllByLimit(SchoolManageVO schoolManageVO,Integer page, Integer limit);

    /**
     * 新增数据
     *
     * @param classinfo 实例对象
     * @return 实例对象
     */
    Classinfo insert(Classinfo classinfo);

    /**
     * 修改数据
     *
     * @param classinfo 实例对象
     * @return 实例对象
     */
    Classinfo update(Classinfo classinfo);

    /**
     * 通过主键删除数据
     *
     * @param classid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer classid);

    //查询全部
    Object queryAll();

    //条件查询classname
    List<SchoolManageVO> tjSelClass(SchoolManageVO schoolManageVO);


    Response addOneClass(Classinfo classInfo);
}