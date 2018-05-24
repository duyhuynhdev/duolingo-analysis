package jp.ac.jaist.Common.obj;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 12:59 PM 7/3/17.
 */
public class Question extends DuolingoObject {
    private int id;
    private String questionTitle = "";
    private String questionContent = "";
    private eQuestionType type;
    private String answer = "";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public eQuestionType getType() {
        return type;
    }

    public void setType(eQuestionType type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
