package com.lingnan.sas.client.service;



import com.lingnan.sas.core.entity.MessageEntity;
import com.lingnan.sas.core.entity.MessageRefEntity;

import java.util.HashMap;
import java.util.List;

public interface MessageService {
     String insertMessage(MessageEntity entity);
     List<HashMap> searchMessageByPage(int userId, long start, int length);
     HashMap searchMessageById(String id);
     String insertRef(MessageRefEntity entity);
     long searchUnreadCount(int userId);
     long searchLastCount(int userId);
     long updateUnreadMessage(String id);
     long deleteMessageRefById(String id);
     long deleteUserMessageRef(int userId);
     List<MessageRefEntity> batchInsertRef(List<MessageRefEntity> entitys);

     int publishMessage(MessageEntity messageEntity, List<MessageRefEntity> entitys);
}
