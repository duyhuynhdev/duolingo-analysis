package jp.ac.jaist.Common.obj;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 12:55 PM 7/3/17.
 */
public class Milestone extends DuolingoObject {
    private int id;
    private String title = "";
    private List<Integer> skillIds = new ArrayList<>();

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

    public List<Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }
}
