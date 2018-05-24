package jp.ac.jaist.Database;

import java.util.Set;

/**
 * Created by hpduy17 on 5/24/16.
 */
public class IdGenerator {
    private static Database database = Database.getInstance();
    private static int lessonId = 0;
    private static int questionId = 0;
    private static int skillId = 0;
    private static int milestoneId = 0;


    public synchronized static int getLessonId() {
        Set<Integer> idSet = database.getLessonMap().keySet();
        do {
            lessonId++;
        }
        while (idSet.contains(lessonId));
        return lessonId;
    }

    public synchronized static int getQuestionId() {
        Set<Integer> idSet = database.getQuestionMap().keySet();
        do {
            questionId++;
        }
        while (idSet.contains(questionId));
        return questionId;
    }

    public synchronized static int getSkillId() {
        Set<Integer> idSet = database.getSkillMap().keySet();
        do {
            skillId++;
        }
        while (idSet.contains(skillId));
        return skillId;
    }

    public synchronized static int getMilestoneId() {
        Set<Integer> idSet = database.getMilestoneMap().keySet();
        do {
            milestoneId++;
        }
        while (idSet.contains(milestoneId));
        return milestoneId;
    }



}
