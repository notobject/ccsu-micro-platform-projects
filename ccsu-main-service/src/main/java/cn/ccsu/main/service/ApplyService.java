package cn.ccsu.main.service;

import cn.ccsu.common.enums.NotifyType;
import cn.ccsu.common.enums.SystemNotifyType;
import cn.ccsu.common.message.NotifyMessage;
import cn.ccsu.main.dao.ApplyDAO;
import cn.ccsu.main.enums.ApplyStatus;
import cn.ccsu.main.enums.InformationCategory;
import cn.ccsu.main.exceptions.GlobalException;
import cn.ccsu.main.pojo.po.Information;
import cn.ccsu.main.pojo.po.InformationApply;
import cn.ccsu.main.stream.NotifyMQSender;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author hangs.zhang
 * @date 2019/1/30
 * *****************
 * function:
 */
@Service
public class ApplyService {

    @Autowired
    private NotifyMQSender notifyMQSender;

    @Autowired
    private ApplyDAO applyDAO;

    @Autowired
    private InformationService informationService;

    // 报名
    public void apply(int informationId, int userId) {
        Information information = informationService.getInformationById(informationId);
        if (information == null) {
            throw new GlobalException(-1, "information不存在");
        }
        if (!InformationCategory.ACTIVITY.name().equals(information.getCategory())) {
            throw new GlobalException(-1, "information类型错误");
        }
        InformationApply apply = new InformationApply();
        apply.setUserId(userId);
        apply.setInformationId(informationId);
        apply.setStatus(ApplyStatus.CURRENT_APPLY.name());
        int i = applyDAO.insert(apply);
        if (i != 1) {
            throw new GlobalException(-1, "报名失败，无法重复报名");
        }
    }

    // 更新报名状态
    public void updateApplyStatus(int applyId, ApplyStatus applyStatus) {
        InformationApply apply = applyDAO.selectById(applyId);
        // TODO: 2019/1/30 将id转化为数据
        int userId = apply.getUserId();
        int informationId = apply.getInformationId();
        Information information = informationService.getInformationById(informationId);
        if (information == null) {
            throw new GlobalException(-1, "information不存在");
        }
        String content;
        if (applyStatus.equals(ApplyStatus.FAILURE)) {
            content = "非常抱歉，当前活动报名失败";
        } else {
            content = "恭喜您，活动报名成功";
        }
        NotifyMessage notifyMessage = generateActivityNotifyMessage(Lists.newArrayList(userId), information.getTitle(), content);
        // 发送消息数据
        notifyMQSender.sendMessage(notifyMessage, null);
        int i = applyDAO.update(applyId, applyStatus.name());
        if (i != 1) {
            throw new GlobalException(-1, "更新失败");
        }
    }

    // 查看对应information的报名列表
    public List<InformationApply> getApplyByInformationId(int informationId) {
        return applyDAO.listAppliesByInformationId(informationId);
    }

    // 生成notifymessage
    public static NotifyMessage generateActivityNotifyMessage(List<Integer> userIds, String activityName, String content) {
        NotifyMessage notifyMessage = new NotifyMessage();
        notifyMessage.setUserIds(userIds);
        NotifyMessage.Notify notify = new NotifyMessage.Notify();
        NotifyMessage.SystemNotify systemNotify = new NotifyMessage.SystemNotify();
        systemNotify.setSystemNotifyType(SystemNotifyType.activity.toString());
        systemNotify.setActivityName(activityName);
        notify.setSystemNotify(systemNotify);
        notify.setNotifyType(NotifyType.system.toString());
        notify.setMessage(content);
        notify.setSendTime(new Date());
        notifyMessage.setNotify(notify);
        return notifyMessage;
    }

}
