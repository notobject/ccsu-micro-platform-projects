package cn.ccsu.score.entity;

import java.math.BigDecimal;

public class StudentData {
    private String id;
    private BigDecimal totalScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }
}
