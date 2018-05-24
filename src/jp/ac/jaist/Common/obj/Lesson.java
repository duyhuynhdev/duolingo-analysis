package jp.ac.jaist.Common.obj;

import java.util.ArrayList;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 12:58 PM 7/3/17.
 */
public class Lesson extends DuolingoObject {
    private int id;
    private String title = "";
    private String description;
    private String url = "";
    private int numberOfQuestion;
    private ArrayList<Integer> questionIds = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public ArrayList<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(ArrayList<Integer> questionIds) {
        this.questionIds = questionIds;
    }
}
