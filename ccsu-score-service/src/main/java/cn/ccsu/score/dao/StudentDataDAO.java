package cn.ccsu.score.dao;

import cn.ccsu.score.entity.ScoreEvent;
import cn.ccsu.score.entity.StudentData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface StudentDataDAO {

    @Select("select * from ccsu_score_student_data where id = #{id}")
    StudentData getTotalDataById(@Param("id") String id);

    @Select("select * from ccsu_score_event where id = #{id}")
    @ResultType(ScoreEvent.class)
    List<ScoreEvent> getScoreDetailById(@Param("id") String id);

    @Update("update ccsu_score_student_data set total_score = total_score + #{score} where id = #{id}")
    Boolean increaseTotalScore(String id, float score);


    @Insert("insert into ccsu_score_event(id,record_time,event,score) values(#{id},#{date},#{event},#{score})")
    Boolean increaseEventDetail(String id, float score, Date date,String event);


}
