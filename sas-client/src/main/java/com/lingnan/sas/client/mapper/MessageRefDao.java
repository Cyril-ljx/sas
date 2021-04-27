package com.lingnan.sas.client.mapper;


import com.lingnan.sas.core.entity.MessageRefEntity;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRefDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public String insert(MessageRefEntity entity) {
        entity = mongoTemplate.save(entity);
        return entity.get_id();
    }

    //批量插入
    public List<MessageRefEntity> batchInsert(List<MessageRefEntity> entitys) {
        entitys = (List<MessageRefEntity>) mongoTemplate.insert(entitys,MessageRefEntity.class);
        return entitys;
    }
    /**
     * 查找有多少条未读消息
     *
     * @param userId 对应用户的id
     * @return count
     */
    public long searchUnreadCount(int userId) {
        Query query = new Query();
        //添加查询条件
        query.addCriteria(Criteria.where("readFlag").is(false).and("receiverId").is(userId));
        long count = mongoTemplate.count(query, MessageRefEntity.class);
        return count;
    }
    /**
     * 查找有用户新接收消息的数量
     *
     * @param userId 对应用户的id
     * @return rows
     */
    public long searchLastCount(int userId) {
        Query query = new Query();

        query.addCriteria(Criteria.where("lastFlag").is(true).and("receiverId").is(userId));
        Update update = new Update();
        //将最新消息置为false，这样下次有最新消息就不会包含了
        update.set("lastFlag", false);
        UpdateResult result = mongoTemplate.updateMulti(query, update, "message_ref");
        long rows = result.getModifiedCount();
        return rows;
    }
    /**
     * 将未读消息置为已读
     *
     * @param id 对应消息的id
     * @return rows
     */
    public long updateUnreadMessage(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("readFlag", true);
        UpdateResult result = mongoTemplate.updateFirst(query, update, "message_ref");
        long rows = result.getModifiedCount();
        return rows;
    }

    /**
     * 根据id把ref消息删除
     *
     * @param id 对应消息的id
     * @return rows
     */
    public long deleteMessageRefById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        DeleteResult result=mongoTemplate.remove(query,"message_ref");
        long rows=result.getDeletedCount();
        return rows;
    }

    /**
     * 根据userId把用户的全部ref消息删除
     *
     * @param userId 对应用户的id
     * @return rows
     */
    public long deleteUserMessageRef(int userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("receiverId").is(userId));
        DeleteResult result=mongoTemplate.remove(query,"message_ref");
        long rows=result.getDeletedCount();
        return rows;
    }
}
