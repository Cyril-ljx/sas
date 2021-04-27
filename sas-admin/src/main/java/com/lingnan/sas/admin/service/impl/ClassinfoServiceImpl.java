package com.lingnan.sas.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingnan.sas.admin.service.ClassinfoService;
import com.lingnan.sas.core.entity.Classinfo;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.util.DateUtil;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.ClassinfoDao;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 * 班级信息(Classinfo)表服务实现类
 *
 * @author makejava
 * @since 2021-03-14 19:59:09
 */
@Service("classinfoService")
public class ClassinfoServiceImpl implements ClassinfoService {
    @Resource
    private ClassinfoDao classinfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param classid 主键
     * @return 实例对象
     */
    @Override
    public Classinfo queryById(Integer classid) {
        return this.classinfoDao.queryById(classid);
    }

    /**
     * 查询多条数据
     *
     * @param page 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public LayuiVO queryAllByLimit(SchoolManageVO schoolManageVO,Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<SchoolManageVO> classInfo = classinfoDao.queryAllByLimit(schoolManageVO);
        PageInfo pageInfo = new PageInfo(classInfo);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount((int) pageInfo.getTotal());//总数
        layData.setData(pageInfo.getList());
        return layData;
    }

    /**
     * 新增数据
     *
     * @param classinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Classinfo insert(Classinfo classinfo) {
        this.classinfoDao.insert(classinfo);
        return classinfo;
    }

    /**
     * 修改数据
     *
     * @param classinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Classinfo update(Classinfo classinfo) {
        this.classinfoDao.update(classinfo);
        return this.queryById(classinfo.getClassid());
    }

    /**
     * 通过主键删除数据
     *
     * @param classid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer classid) {
        return this.classinfoDao.deleteById(classid) > 0;
    }

    @Override
    public Object queryAll() {
        return classinfoDao.queryAll(null);
    }

    /**
     * 条件查询
     */
    //条件查询classname
    @Override
    public List<SchoolManageVO> tjSelClass(SchoolManageVO schoolManageVO) {
        return classinfoDao.tjSelClass(schoolManageVO);
    }



    @Override
    public Response addOneClass(Classinfo classInfo) {
        int check = classinfoDao.checkOneClass(classInfo);
        if (check > 0) {
            return Response.error("有重复的班级不可添加");
        } else {
            classInfo.setBegintime(DateUtil.getNowDate());
            classInfo.setCcount(0);
            int r = classinfoDao.addOneClass(classInfo);
            if (r == 1) {
                return Response.success("添加班级成功");
            } else {
                return Response.error("添加班级失败");
            }
        }
    }
}