package com.lingnan.sas.client.service.impl;


import cn.hutool.core.util.IdUtil;
import com.lingnan.sas.client.service.MessageService;
import com.lingnan.sas.client.task.MessageTask;
import com.lingnan.sas.core.entity.MessageEntity;
import com.lingnan.sas.core.entity.MessageRefEntity;
import com.lingnan.sas.client.mapper.MessageDao;
import com.lingnan.sas.client.mapper.MessageRefDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MessageRefDao messageRefDao;

    @Autowired
    private MessageTask messageTask;

    @Override
    public String insertMessage(MessageEntity entity) {
        String id = messageDao.insert(entity);
        return id;
    }

    @Override
    public List<HashMap> searchMessageByPage(int userId, long start, int length) {
        List<HashMap> list = messageDao.searchMessageByPage(userId, start, length);
        return list;
    }

    @Override
    public HashMap searchMessageById(String id) {
        HashMap map = messageDao.searchMessageById(id);
        return map;
    }

    @Override
    public String insertRef(MessageRefEntity entity) {
        String id = messageRefDao.insert(entity);
        return id;
    }

    @Override
    public  List<MessageRefEntity> batchInsertRef(List<MessageRefEntity> entitys) {
        List<MessageRefEntity> result = messageRefDao.batchInsert(entitys);
        return result;
    }

    @Override
    public int publishMessage(MessageEntity entity, List<MessageRefEntity> entitys) {
        entity.setUuid(IdUtil.simpleUUID());
        entity.setSendTime(new Date());
        messageTask.sendAsync(entity.getMsg() + "", entity);
        return messageTask.batchReceiveAsync(entity.getMsg()+"",entitys);
    }

    @Override
    public long searchUnreadCount(int userId) {
        long count = messageRefDao.searchUnreadCount(userId);
        return count;
    }

    @Override
    public long searchLastCount(int userId) {
        long count = messageRefDao.searchLastCount(userId);
        return count;
    }

    @Override
    public long updateUnreadMessage(String id) {
        long rows = messageRefDao.updateUnreadMessage(id);
        return rows;
    }

    @Override
    public long deleteMessageRefById(String id) {
        long rows = messageRefDao.deleteMessageRefById(id);
        return rows;
    }

    @Override
    public long deleteUserMessageRef(int userId) {
        long rows = messageRefDao.deleteUserMessageRef(userId);
        return rows;
    }
}
