package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.SysRoleService;
import com.lingnan.sas.core.entity.SysRole;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.SysRoleDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统角色(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2021-03-12 18:20:28
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRole queryById(Integer id) {
        return this.sysRoleDao.queryById(id);
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
        List<SysRole> sysRole=sysRoleDao.queryAllByLimit(offset, limit);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(sysRoleDao.queryAllCount());//总数
        layData.setData(sysRole);
        return layData;
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public Response insert(SysRole sysRole) {
        if(sysRoleDao.queryByRole(sysRole.getRole())>0){
            return Response.error("已有该权限不可添加");
        }
         if(sysRoleDao.insert(sysRole)>0) {
            return Response.success("添加成功");
        }
        return Response.error("添加失败");
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole update(SysRole sysRole) {
        this.sysRoleDao.update(sysRole);
        return this.queryById(sysRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysRoleDao.deleteById(id) > 0;
    }

    @Override
    public List<SysRole> queryAll() {
        return sysRoleDao.queryAll();
    }

    @Override
    public Integer queryAllCount() {
        return sysRoleDao.queryAllCount();
    }
}