package com.lingnan.sas.client.service;



import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.core.entity.Deliver;
import com.lingnan.sas.core.entity.Newsinfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DeliverService {
    ArrayList<HashMap> queryAllById(int id, int offset, int limit);

    Deliver queryDeliverById(int id);

    ArrayList<HashMap> queryAllOverById(int id, int offset, int limit);

    int updateDeliver(Deliver deliver);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 新增数据
     *
     * @param deliver 实例对象
     * @return 影响行数
     */
    int insert(Deliver deliver);

    R deleteAll();
}
