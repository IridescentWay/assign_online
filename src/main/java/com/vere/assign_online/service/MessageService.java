package com.vere.assign_online.service;

import com.vere.assign_online.model.Message;

import java.util.List;

/**
 * ClassName:MessageService
 * Package:com.vere.assign_online.service
 * Description:
 *
 * @Date:2022/5/12 10:17
 * @Author:3046990030@qq.com
 */
public interface MessageService {
    List<Message> getMessageList(String toName);
    int addMessage(Message message);
    boolean readMessage(Message message);
}
