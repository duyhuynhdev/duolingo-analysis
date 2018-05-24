package jp.ac.jaist.Common.obj;

import java.util.ArrayList;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 12:57 PM 7/3/17.
 */
public class Skill extends DuolingoObject {
    private int id;
    private String title = "";
    private int lineNumber = 0;
    private String url = "";
    private int numberOfLesson;
    private ArrayList<Integer> lessonIds = new ArrayList<>();

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumberOfLesson() {
        return numberOfLesson;
    }

    public void setNumberOfLesson(int numberOfLesson) {
        this.numberOfLesson = numberOfLesson;
    }

    public ArrayList<Integer> getLessonIds() {
        return lessonIds;
    }

    public void setLessonIds(ArrayList<Integer> lessonIds) {
        this.lessonIds = lessonIds;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
