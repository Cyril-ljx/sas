package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.NewsinfoService;
import com.lingnan.sas.admin.util.ShiroUtil;
import com.lingnan.sas.core.entity.Newsinfo;
import com.lingnan.sas.core.entity.SysUser;
import com.lingnan.sas.core.util.DateUtil;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.NewsinfoDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告(Newsinfo)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:06:59
 */
@Service("newsinfoService")
public class NewsinfoServiceImpl implements NewsinfoService {
    @Resource
    private NewsinfoDao newsinfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param nid 主键
     * @return 实例对象
     */
    @Override
    public Newsinfo queryById(Integer nid) {
        return this.newsinfoDao.queryById(nid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public LayuiVO queryAllByLimit(int offset, int limit) {
        List<Newsinfo> newsinfo = newsinfoDao.queryAllByLimit(offset, limit);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(newsinfoDao.queryStudentCount());//总数
        layData.setData(newsinfo);
        return layData;
    }

    /**
     * 新增数据
     *
     * @param newsinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Response insert(Newsinfo newsinfo) {
        newsinfo.setNtime(DateUtil.getNowTime());
        SysUser sysUser = (SysUser) ShiroUtil.getSubject().getPrincipal();
        newsinfo.setNauthor(sysUser.getNickName());
        int result = newsinfoDao.insert(newsinfo);
        if (result > 0) {
            return Response.success(result);
        }
        return Response.error("添加失败");
    }

    /**
     * 修改数据
     *
     * @param newsinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Newsinfo update(Newsinfo newsinfo) {
        this.newsinfoDao.update(newsinfo);
        return this.queryById(newsinfo.getNid());
    }

    /**
     * 通过主键删除数据
     *
     * @param nid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer nid) {
        return this.newsinfoDao.deleteById(nid) > 0;
    }
}