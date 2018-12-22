package cn.ccsu.score.enums;

public enum CodeMessage {

    SUCCESS(0,"成功"),
    STUDENT_ID_EMPTY(60001,"学生学号不能为空"),
    BIND_ERROR(500101,"参数校验异常:%s"),
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

    public CodeMessage fillArgs(Object... args){
        resultMessage = String.format(BIND_ERROR.resultMessage,args);
        return CodeMessage.BIND_ERROR;
    }
}
