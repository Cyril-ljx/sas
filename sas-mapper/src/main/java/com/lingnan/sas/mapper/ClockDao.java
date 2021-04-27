package com.lingnan.sas.mapper;

import com.lingnan.sas.core.entity.Clock;
import com.lingnan.sas.core.entity.ClockVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * (Clock)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-11 15:59:58
 */
@Mapper
public interface ClockDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HashMap queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Clock> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param clock 实例对象
     * @return 对象列表
     */
    List<Clock> queryAll(Clock clock);

    /**
     * 新增数据
     *
     * @param clock 实例对象
     * @return 影响行数
     */
    int insert(Clock clock);

    /**
     * 修改数据
     *
     * @param clock 实例对象
     * @return 影响行数
     */
    int update(Clock clock);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Integer queryClockCount();

    /**
     * 查找用户打卡信息
     *
     * @param sid
     * @return 信息
     */

    List<HashMap> searchMessageById( @Param("sid")Integer sid,@Param("page") Long page,@Param("length") Integer length);

    /**
     * 查找用户全部打卡信息
     *
     * @param sid
     * @return 信息
     */

    ClockVO searchMessage(Integer sid);



    /**
     * 查找未打卡的学生信息
     *
     * @return 信息
     */
    ArrayList<HashMap> searchNotClock(@Param("cid") Integer cid,@Param("offset")Integer offset,@Param("limit")Integer limit);

    /**
     * 查找已打卡的学生信息
     *
     * @return 信息
     */
    ArrayList<HashMap> searchClock(@Param("cid") Integer cid,@Param("offset")Integer offset,@Param("limit")Integer limit);

    /**
     * 按班级查找教师管理的年级已打卡的学生人数
     *
     * @return 信息
     */
    ArrayList<HashMap> searchClockNum(@Param("tid") Integer tid,@Param("offset")Integer offset,@Param("limit")Integer limit);
    /**
     * 按班级查找教师管理的年级未打卡的学生人数
     *
     * @return 信息
     */
    ArrayList<HashMap> searchNotClockNum(@Param("tid")Integer tid,@Param("offset")Integer offset,@Param("limit")Integer limit);
}