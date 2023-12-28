package com.vere.assign_online.controller;

import com.vere.assign_online.model.*;
import com.vere.assign_online.service.CourseService;
import com.vere.assign_online.service.HomeworkService;
import com.vere.assign_online.service.MessageService;
import com.vere.assign_online.utils.DataFormatUtil;
import com.vere.assign_online.utils.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName:CommonController
 * Package:com.vere.assign_online.controller
 * Description:
 *
 * @Date:2022/4/30 11:03
 * @Author:3046990030@qq.com
 */

@RestController
@RequestMapping(value = "common")
public class CommonController {
    @Autowired
    HomeworkService homeworkService;

    @Autowired
    CourseService courseService;

    @Autowired
    MessageService messageService;

    @Autowired
    QiNiuUtil qiNiuUtil;

    @GetMapping(value = "/getQiNiuToken", produces = "application/json; charset=UTF-8")
    @ResponseBody
    String getQiNiuToken(@RequestParam String key) {
        return qiNiuUtil.getToken(key);
    }

    @GetMapping(value = "/getPrivateUrl", produces = "application/json; charset=UTF-8")
    @ResponseBody
    String getPrivateUrl(@RequestParam String fileName) {
        return qiNiuUtil.getPrivateUrl(fileName);
    }

    @GetMapping("/getStudents")
    @ResponseBody
    List<Student> getStudents(@RequestParam Integer courseId) {
        return courseService.getStudents(courseId);
    }

    @GetMapping("/getAllHomework")
    @ResponseBody
    List<Map<String, Object>> getHomeworkByCourseId(@RequestParam Integer courseId, @RequestParam String role) {
        role = String.format("ROLE_%s", role);

        List<Map<String, Object>> homework = DataFormatUtil.formatHumpNameForList(
                role.equals(Role.TEACHER) ?
                        homeworkService.getHomeworkForTeacher(courseId) :
                        homeworkService.getHomeworkForStudent(courseId)
        );
        // task, allCommitted, posted -> true or false, editPopoverVisible: false, hasChildren: true
        for (Map<String, Object> map : homework) {
            map.put("task", DataFormatUtil.formatHumpNameForList(homeworkService.getTasksByHomeworkId((Integer) map.get("id"))));
            map.put("posted", map.get("posted").equals(HomeworkService.POSTED));
            map.put("allCommitted", (Integer) map.get("committed") == courseService.getStudents(courseId).size());
            map.put("editPopoverVisible", false);
            map.put("hasChildren", true);
        }
        return homework;
    }

    @GetMapping("/getHomeworkFile")
    @ResponseBody
    List<String> getHomeworkFile(@RequestParam Integer studentId, @RequestParam Integer homeworkId) {
        return homeworkService.getHomeworkFile(studentId, homeworkId);
    }

    @GetMapping("/getNotifications")
    @ResponseBody
    List<Message> getNotifications(@RequestParam String toName, @RequestParam Integer firstTime) {
        if (firstTime == 1) {
            return messageService.getMessageList(toName);
        }
        List<Message> messages = messageService.getMessageList(toName);
        // 如果 10s 内没有更新数据库，则返回 null
        if ((messages.size() > 0 && messages.get(0).getUpdateTime().isBefore(LocalDateTime.now().minusSeconds(10))) || messages.size() == 0) {
            return null;
        }
        return messages;
    }

    @PutMapping("/readNotification")
    @ResponseBody
    boolean readNotification(@RequestBody Message message) {
        message.setState(Message.READE_DONE);
        System.out.println(message);
        return messageService.readMessage(message);
    }
}
