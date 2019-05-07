/*
 * Created by Long Duping
 * Date 2019-05-06 20:59
 */
package cn.ccsu.controlcenter.service;

import cn.ccsu.controlcenter.pojo.AckInfo;
import cn.ccsu.controlcenter.pojo.MachineInfo;
import cn.ccsu.controlcenter.pojo.TaskInfo;
import cn.ccsu.controlcenter.util.UuidUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class TaskManagement {
    /**
     * 任务类型
     */
    public static final String TASK_TYPE_BUILD = "build";
    public static final String TASK_TYPE_DEPLOY = "deploy";
    public static final String TASK_TYPE_DESTRIBUTE = "distribute";
    /**
     * 任务状态
     */
    public static final String TASK_STATUS_PROCESSING = "processing";
    public static final String TASK_STATUS_WAITING = "waiting";
    public static final String TASK_STATUS_COMPLETE = "complete";

    // 任务池
    private Map<String, TaskInfo> pool = new ConcurrentHashMap<>();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static TaskManagement myself = new TaskManagement();

    private TaskManagement() {

    }

    public static TaskManagement getInstance() {
        return myself;
    }

    public TaskInfo newTask(String action, Integer creator) {
        MachineInfo machine = MachineManagement.getInstance().get(action);
        if (machine == null) {
            log.info("has no machine to using...");
            return null;
        }
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setId(UuidUtil.get());
        taskInfo.setAction(action);
        taskInfo.setCreateTime(new Date(System.currentTimeMillis()));
        taskInfo.setCurrentStatus(TASK_STATUS_WAITING);
        taskInfo.setCreatorId(creator);
        taskInfo.setMachine(machine);
        taskInfo.setMessages(new LinkedList<>());
        pool.put(taskInfo.getId(), taskInfo);
        return taskInfo;
    }

    public void doProcess(AckInfo ackInfo) {
        TaskInfo taskInfo = pool.get(ackInfo.getTaskId());
        if (taskInfo == null) {
            log.info("task {} not exist.", ackInfo.getTaskId());
            return;
        }
        if (TASK_STATUS_COMPLETE.equals(taskInfo.getCurrentStatus())) {
            log.info("task {} already completed.", taskInfo.getId());
            return;
        }
        int code = ackInfo.getCode();
        List<String> messages = taskInfo.getMessages();
        String m = String.format("[%s][%d] %s \n%s\n", sdf.format(new Date(ackInfo.getTimestamp())), ackInfo.getSerialNo(), ackInfo.getCmd(), ackInfo.getMsg());
        messages.add(m);
        if (code != 10000) {
            taskInfo.setCurrentStatus(TASK_STATUS_PROCESSING);
        } else {
            taskInfo.setCurrentStatus(TASK_STATUS_COMPLETE);
        }
    }

    public String getCurrentState(String taskId) {
        JSONObject res = new JSONObject();
        TaskInfo taskInfo = pool.get(taskId);
        if (taskInfo == null) {
            res.put("msg", "task not exist.");
            res.put("status", "error");
            return res.toString();
        }
        List<String> messages = taskInfo.getMessages();
        StringBuilder msg = new StringBuilder();
        for (int i = taskInfo.getMark(); i < messages.size(); i++) {
            msg.append(messages.get(i));
            taskInfo.setMark(i + 1);
        }
        res.put("msg", msg.toString());
        res.put("status", taskInfo.getCurrentStatus());
        return res.toString();
    }


}
