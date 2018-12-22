package cn.ccsu.score.api;

import cn.ccsu.score.entity.PostStudentScoreInfo;
import cn.ccsu.score.entity.ScoreEvent;
import cn.ccsu.score.entity.StudentData;
import cn.ccsu.score.enums.CodeMessage;
import cn.ccsu.score.entity.Result;
import cn.ccsu.score.service.impl.ScoreServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RefreshScope
public class ScoreController {

    Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    ScoreServiceImpl scoreService;

    //获取全部综测信息
    @GetMapping(value = "/totalScore",produces = "application/json;character=UTF-8")
    public String getStudentTotalScore(@RequestParam String id){
        if (StringUtils.isEmpty(id)){
            return Result.error(CodeMessage.STUDENT_ID_EMPTY).toString();
        }
        StudentData studentData = scoreService.getStudentTotalScoreById(id);
        if (studentData==null){
            return Result.error(CodeMessage.STUDENT_NOT_FOUND).toString();
        }
        return Result.success(studentData).toString();
    }

    //获取综测具体信息
    @GetMapping(value = "scoreDetail",produces = "application/json;character=UTF-8")
    public String getStudentEvents(@RequestParam String id){
        if (StringUtils.isEmpty(id)){
            return Result.error(CodeMessage.STUDENT_ID_EMPTY).toString();
        }
        List<ScoreEvent> scoreEvents = scoreService.getStudentAllEventById(id);
        if (scoreEvents==null){
            return Result.error(CodeMessage.STUDENT_NOT_FOUND).toString();
        }
        return Result.success(scoreEvents).toString();
    }


    //增加综测

    /**
     * @param postInfo Json数据，必须包含以下数据
     * String id;
     * Date recordTime;
     * String event;
     * float score;
     * @return 成功或者失败的Json消息
     */

    @PostMapping(value = "addScore",produces = "application/json;character=UTF-8")
    public String increaseScore(String postInfo){
        @Valid PostStudentScoreInfo info = JSON.parseObject(postInfo,new TypeReference<PostStudentScoreInfo>(){});
        Boolean success  = scoreService.increaseScoreById(info.getId(),info.getScore(),info.getEvent(),info.getRecordTime());
        logger.info(Boolean.toString(success));
        return Result.success(success).toString();
    }
}
