package com.vere.assign_online.service.impl;

import com.vere.assign_online.mapper.MessageMapper;
import com.vere.assign_online.model.Message;
import com.vere.assign_online.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:MessageServiceImpl
 * Package:com.vere.assign_online.service.impl
 * Description:
 *
 * @Date:2022/5/12 10:18
 * @Author:3046990030@qq.com
 */

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<Message> getMessageList(String toName) {
        return messageMapper.queryMessageList(toName);
    }

    @Override
    public int addMessage(Message message) {
       return messageMapper.insertSelective(message);
    }

    @Override
    public boolean readMessage(Message message) {
        int ans = messageMapper.updateByPrimaryKeySelective(message);
        System.out.println(ans);
        return ans == 1;
    }
}
