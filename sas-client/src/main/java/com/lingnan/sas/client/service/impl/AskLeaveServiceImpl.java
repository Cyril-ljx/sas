package com.lingnan.sas.client.service.impl;

import cn.hutool.core.util.IdUtil;
import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.service.AskLeaveService;
import com.lingnan.sas.client.task.MessageTask;
import com.lingnan.sas.core.entity.AskLeave;
import com.lingnan.sas.core.entity.MessageEntity;
import com.lingnan.sas.mapper.AskLeaveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author 19399.
 * @date 2021/4/2.
 * @time 1:49
 */
@Service("askLeaveService")
public class AskLeaveServiceImpl implements AskLeaveService {

    @Autowired
    private AskLeaveDao askLeaveDao;

    @Autowired
    private MessageTask messageTask;

    @Override
    public ArrayList<HashMap> queryAllById(int id,int offset, int limit) {

        return askLeaveDao.queryAllById(id,offset,limit);
    }

    @Override
    public AskLeave queryAskLeaveById(int id) {
        return askLeaveDao.queryById(id);
    }

    @Override
    public ArrayList<HashMap> queryAllOverById(int id, int offset, int limit) {
        return askLeaveDao.queryAllOverById(id,offset,limit);
    }

    @Override
    public int updateAskLeave(AskLeave askLeave) {
        return askLeaveDao.update(askLeave);
    }

    @Override
    public int deleteById(Integer id) {
        return askLeaveDao.deleteById(id);
    }

    @Override
    public R insert(AskLeave askLeave) {
        askLeave.setState(0);
        if(askLeaveDao.queryBysId(askLeave.getSid())==null){
            //如果想比较日期则写成"yyyy-MM-dd"就可以了
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date begin = sdf.parse(askLeave.getStartTime());
                Date end = sdf.parse(askLeave.getEndTime());
                if(end.before(begin)){
                    return R.ok().put("result","请假结束时间不允许小于请假开始时间，请重新选择");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

             if(askLeaveDao.insert(askLeave)>0){
                 MessageEntity entity = new MessageEntity();
                 entity.setSenderId(0);
                 entity.setSenderName("系统");
                 entity.setTitle("请假消息");
                 entity.setUuid(IdUtil.simpleUUID());
                 entity.setMsg("你已成功申请请假，目前待辅导员审批！");
                 entity.setSendTime(new Date());
                 messageTask.sendAsync(askLeave.getSid() + "", entity);
                 messageTask.receive(askLeave.getSid()+"");
                 return R.ok().put("result","提交成功");
             }
        }
        else{
            return R.ok().put("result","已有正在进行的请假，不允许再次添加");
        }
        return R.ok().put("result","提交失败");
    }

    @Override
    public ArrayList<HashMap> searchAskLeave(String username, int offset, int limit) {
        int tid = Integer.parseInt(username);
        return askLeaveDao.searchAskLeave(tid,offset,limit);
    }

    @Override
    public ArrayList<HashMap> searchApprove(String username, int offset, int limit) {
        int tid = Integer.parseInt(username);
        return askLeaveDao.searchApprove(tid,offset,limit);
    }
}
