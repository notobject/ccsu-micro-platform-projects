package cn.ccsu.score.service.impl;

import cn.ccsu.score.dao.StudentDataDAO;
import cn.ccsu.score.entity.Result;
import cn.ccsu.score.entity.ScoreEvent;
import cn.ccsu.score.entity.StudentData;
import cn.ccsu.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    StudentDataDAO studentDataDAO;

    @Override
    public StudentData getStudentTotalScoreById(String id) {
        StudentData studentData = studentDataDAO.getTotalDataById(id);
        return studentData;
    }

    @Override
    public List<ScoreEvent> getStudentAllEventById(String id) {
        List<ScoreEvent> scoreEvents = studentDataDAO.getScoreDetailById(id);
        return scoreEvents;
    }

    @Override
    @Transactional
    public Boolean increaseScoreById(String id, float score, String event, Date time) {
        studentDataDAO.increaseTotalScore(id,score);
        studentDataDAO.increaseEventDetail(id,score,time,event);
        return true;
    }
}
