package cn.ccsu.score.enums;

public enum CodeMessage {

    SUCCESS(0,"成功"),
    STUDENT_ID_EMPTY(60001,"学生学号不能为空"),
    STUDENT_NOT_FOUND(60002,"没有找到相应的用户");

    private int resultCode;
    private String resultMessage;

    CodeMessage(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
