package cn.ccsu.score.service;

        import cn.ccsu.score.entity.Result;
        import cn.ccsu.score.entity.ScoreEvent;
        import cn.ccsu.score.entity.StudentData;

        import java.util.Date;
        import java.util.List;

public interface ScoreService {
    StudentData getStudentTotalScoreById(String id);
    List<ScoreEvent> getStudentAllEventById(String id);
    Boolean increaseScoreById(String id, float score, String event, Date time);
}
