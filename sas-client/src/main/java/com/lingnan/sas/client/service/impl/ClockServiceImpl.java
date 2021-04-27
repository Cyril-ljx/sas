package com.lingnan.sas.client.service.impl;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.service.ClockService;
import com.lingnan.sas.client.task.MessageTask;
import com.lingnan.sas.core.entity.Clock;
import com.lingnan.sas.core.entity.ClockVO;
import com.lingnan.sas.core.entity.Diretion;
import com.lingnan.sas.core.entity.MessageEntity;

import com.lingnan.sas.core.util.DateUtil;
import com.lingnan.sas.mapper.ClockDao;

import com.lingnan.sas.mapper.DiretionDao;
import com.lingnan.sas.mapper.TbCityDao;
import com.lingnan.sas.mapper.TeacherDao;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 打卡日记(clock)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:07:00
 */
@Service("clockService")
@Scope("prototype")
public class ClockServiceImpl implements ClockService {

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Resource
    private ClockDao clockDao;

    @Resource
    private DiretionDao diretionDao;

    @Autowired
    private TbCityDao cityDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private MessageTask messageTask;


    @Override
    public ClockVO searchMessage(String username) {
        int sid = Integer.parseInt(username);
        return clockDao.searchMessage(sid);
    }

    @Override
    public int insert(Clock clock,int id) {
        clock.setTime(DateUtil.getNowDate());
        clock.setFlag(1);
        //查询疫情风险等级
        int risk = 0;//低风险
        String city = clock.getCity();
        String district = clock.getDistrict();
        String address=clock.getAddress();
        if (!StringUtil.isBlank(address) && !StrUtil.isBlank(district)) {
            String code = cityDao.searchCode(address);
            try {
                //发送http请求查询本地宝的地区风险等级
                String url = "http://m." + code + ".bendibao.com/news/yqdengji/?qu=" + district;
                //序解析本地宝HTML页面的标签
                Document document = Jsoup.connect(url).get();
                //找出控件
                Elements elements = document.getElementsByClass("list-content");

                if (elements.size() > 0) {
                    //取出第一个元素
                    Element element = elements.get(0);
                    //查找div里最后一个批标签并获取内容
                    String result = element.select("p:last-child").text();
                    System.out.println("风险等级为："+result);
                    if ("高风险".equals(result)) {
                        risk = 2;
                        //发送告警消息通知
                        HashMap<String, Object> map = teacherDao.searchBysId(clock.getSid());
                        String name = (String) map.get("sname");
                        String classname = (String) map.get("classname");
                        Integer tid = (Integer) map.get("id");
                        MessageEntity entity = new MessageEntity();
                        entity.setSenderId(0);
                        entity.setSenderName("系统");
                        entity.setTitle("学生" + name + "身处高风险疫情地区警告");
                        entity.setUuid(IdUtil.simpleUUID());
                        entity.setMsg(classname + "学生" + name + "，" + DateUtil.getNowDate() + "处于" + city + "，属于新冠疫情高风险地区，请及时与该员工联系，核实情况！");
                        entity.setSendTime(new Date());
                        messageTask.sendAsync(tid + "", entity);
                        messageTask.receive(tid + "");
                    } else if ("中风险".equals(result)) {
                        risk = 1;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clock.setRisk(risk);
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(0);
        entity.setSenderName("系统");
        entity.setTitle("打卡消息通知");
        entity.setUuid(IdUtil.simpleUUID());
        entity.setMsg("您已成功疫情打卡");
        entity.setSendTime(new Date());
        messageTask.sendAsync(id + "", entity);
        messageTask.receive(id+"");
        return clockDao.insert(clock);
    }

    @Override
    public ArrayList<HashMap> searchNotClock(Integer cid, Integer page, Integer length) {
        return clockDao.searchNotClock(cid, page, length);
    }

    @Override
    public ArrayList<HashMap> searchClock(Integer cid, Integer page, Integer length) {
        return clockDao.searchClock(cid, page, length);
    }

    @Override
    public ArrayList<HashMap> searchNotClockNum(String username, Integer page, Integer length) {
        int tid = Integer.parseInt(username);
        return clockDao.searchNotClockNum(tid, page, length);
    }


    @Override
    public ArrayList<HashMap> searchClockNum(String username, Integer page, Integer length) {
        int tid = Integer.parseInt(username);
        return clockDao.searchClockNum(tid, page, length);
    }

    @Override
    public List<HashMap> searchMessageById(String username, Long page, Integer length) {
        int sid = Integer.parseInt(username);
        return clockDao.searchMessageById(sid, page, length);
    }

    @Override
    public HashMap searchClockById(Integer id) {
        return clockDao.queryById(id);
    }

    @Override
    public R insertDiretion(Diretion diretion) {
        //如果想比较日期则写成"yyyy-MM-dd"就可以了
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date begin = sdf.parse(diretion.getLeftTime());
            Date end = sdf.parse(diretion.getBackTime());
            if(end.before(begin)){
                return R.ok().put("result","回校时间不允许小于离校时间，请重新选择");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
       if(diretionDao.insert(diretion)<=0){
           return R.ok().put("result","提交失败");
       }
        return R.ok().put("result","提交成功");
    }

    @Override
    public ArrayList<HashMap> searchDirection(Integer cid, Integer page, Integer length) {
        return diretionDao.searchDirection(cid, page, length);
    }

    @Override
    public ArrayList<HashMap> searchNotDirection(Integer cid, Integer page, Integer length) {
        return diretionDao.searchNotDirection(cid, page, length);
    }

    @Override
    public ArrayList<HashMap> searchDirectionNum(String username, Integer page, Integer length) {
        int tid = Integer.parseInt(username);
        return diretionDao.searchDirectionNum(tid, page, length);
    }

    @Override
    public ArrayList<HashMap> searchNotDirectionNum(String username, Integer page, Integer length) {
        int tid = Integer.parseInt(username);
        return diretionDao.searchNotDirectionNum(tid, page, length);
    }

}