package com.lingnan.sas.client.service.impl;

import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.service.DeliverService;
import com.lingnan.sas.core.entity.Deliver;
import com.lingnan.sas.core.entity.Newsinfo;
import com.lingnan.sas.core.util.DateUtil;
import com.lingnan.sas.mapper.DeliverDao;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 19399.
 * @date 2021/4/2.
 * @time 1:49
 */
@Service("deliverService")
public class DeliverServiceImpl implements DeliverService {

    @Autowired
    private DeliverDao deliverDao;

    @Override
    public ArrayList<HashMap> queryAllById(int id,int offset, int limit) {

        return deliverDao.queryAllById(id,offset,limit);
    }

    @Override
    public Deliver queryDeliverById(int id) {
        return deliverDao.queryById(id);
    }

    @Override
    public ArrayList<HashMap> queryAllOverById(int id, int offset, int limit) {
        return deliverDao.queryAllOverById(id,offset,limit);
    }

    @Override
    public int updateDeliver(Deliver deliver) {
        return deliverDao.update(deliver);
    }

    @Override
    public int deleteById(Integer id) {
        return deliverDao.deleteById(id);
    }

    @Override
    public int insert(Deliver deliver) {
        return deliverDao.insert(deliver);
    }

    @Override
    public R deleteAll() {
        String end = deliverDao.selectLastData();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        int count=0;
        try {
            System.out.println((sdf.parse(DateUtil.getNowTime()).getTime()-sdf.parse(end).getTime())/(24 * 60 * 60 * 1000));
            if((sdf.parse(DateUtil.getNowTime()).getTime()-sdf.parse(end).getTime())/(24 * 60 * 60 * 1000)>3){
               count=  deliverDao.deleteAll();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(count>0){
            return R.ok().put("result","已删除全部记录");
        }
        return R.ok().put("result","删除全部记录失败");
    }
}
