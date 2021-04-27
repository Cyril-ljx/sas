package com.lingnan.sas.client.mapper;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.lingnan.sas.core.entity.MessageEntity;
import com.lingnan.sas.core.entity.MessageRefEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class MessageDao {

    @Autowired
    private MongoTemplate mongoTemplate;//用于操作MongoDB

    /**
     * 分页查询数据
     *
     * @param entity 消息对象
     * @return _id
     */
    public String insert(MessageEntity entity) {
        Date sendTime = entity.getSendTime();
        //将北京时间转化为格林尼治时间
        sendTime = DateUtil.offset(sendTime, DateField.HOUR, 8);
        entity.setSendTime(sendTime);
        entity = mongoTemplate.save(entity);
        return entity.get_id();
    }

    /**
     * 分页查询数据
     *
     * @param userId 发送对象
     * @param start  起始位置
     * @param length 偏移量
     * @return HashMap实例对象
     */
    public List<HashMap> searchMessageByPage(int userId, long start, int length) {
        JSONObject json = new JSONObject();
        json.set("$toString", "$_id");
        Aggregation aggregation = Aggregation.newAggregation(
                //设定临时变量
                Aggregation.addFields().addField("id").withValue(json).build(),
                //message集合联合查询message_ref
                Aggregation.lookup("message_ref", "id", "messageId", "ref"),
                //where条件，根据receiverId条件查询，receiverId=#{userId}
                Aggregation.match(Criteria.where("ref.receiverId").is(userId)),
                //降序排序
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "sendTime")),
                //分页
                Aggregation.skip(start),
                Aggregation.limit(length)
        );
        //得到联合查询的结果
        AggregationResults<HashMap> results = mongoTemplate.aggregate(aggregation, "message", HashMap.class);
        //提取查询结果
        List<HashMap> list = results.getMappedResults();
        list.forEach(one -> {
            List<MessageRefEntity> refList = (List<MessageRefEntity>) one.get("ref");
            MessageRefEntity entity = refList.get(0);
            boolean readFlag = entity.getReadFlag();
            String refId = entity.get_id();
            one.put("readFlag", readFlag);
            one.put("refId", refId);
            //提取后删除引用字段
            one.remove("ref");
            //对移动端无影响，删除只是删对应接收人
            one.remove("_id");
            Date sendTime = (Date) one.get("sendTime");
            //将时间转化回来
            sendTime = DateUtil.offset(sendTime, DateField.HOUR, -8);

            String today = DateUtil.today();
            if (today.equals(DateUtil.date(sendTime).toDateStr())) {
                //今天的时间则只需展示小时分钟
                one.put("sendTime", DateUtil.format(sendTime, "HH:mm"));
            } else {
                one.put("sendTime", DateUtil.format(sendTime, "yyyy/MM/dd"));
            }
        });
        return list;
    }

    /**
     * 根据id查找消息
     *
     * @param id 对应message的id
     * @return _id
     */
    public HashMap searchMessageById(String id) {
        HashMap map = mongoTemplate.findById(id, HashMap.class, "message");
        Date sendTime = (Date) map.get("sendTime");
        sendTime = DateUtil.offset(sendTime, DateField.HOUR, -8);
        map.replace("sendTime", DateUtil.format(sendTime, "yyyy-MM-dd HH:mm"));
        return map;
    }
}
