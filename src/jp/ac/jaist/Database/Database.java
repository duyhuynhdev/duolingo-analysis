package jp.ac.jaist.Database;

import com.google.gson.Gson;
import jp.ac.jaist.Common.Path;
import jp.ac.jaist.Common.obj.*;

import java.io.*;
import java.util.*;

/**
 * Created by hpduy17 on 5/24/16.
 */
public class Database {
    private static Database database;

    private Database() {
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
            database.loadDatabase();
        }
        return database;
    }

    //TABLE
    private Map<Integer, Milestone> milestoneMap = new HashMap<>();
    private Map<Integer, Skill> skillMap = new HashMap<>();
    private Map<Integer, Lesson> lessonMap = new HashMap<>();
    private Map<Integer, Question> questionMap = new HashMap<>();

    //OTHERS
    public static HashMap<eQuestionType, HashMap<String, String>> answerTree = new HashMap<>();

    //FUNCTIONS
    public void saveData(eTableType tableType, DuolingoObject object) {
        try {
            switch (tableType) {
                case LESSON:
                    Lesson lesson = (Lesson) object;
                    recordLine(lesson.toString(), Path.getLessonFilePath());
                    lessonMap.put(lesson.getId(), lesson);
                    break;
                case QUESTION:
                    Question question = (Question) object;
                    recordLine(question.toString(), Path.getQuestionFilePath());
                    questionMap.put(question.getId(), question);
                    break;
                case SKILL:
                    Skill skill = (Skill) object;
                    recordLine(skill.toString(), Path.getSkillFilePath());
                    skillMap.put(skill.getId(), skill);
                    break;
                case MILESTONE:
                    Milestone milestone = (Milestone) object;
                    recordLine(milestone.toString(), Path.getMilestoneFilePath());
                    milestoneMap.put(milestone.getId(), milestone);
                    break;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public void recordLine(String json, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
            new FileOutputStream(file, true).close();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(json);
        writer.newLine();
        writer.flush();
        writer.close();
    }


    private void loadDatabase() {
        try {
            load(eTableType.LESSON, Path.getLessonFilePath());
            load(eTableType.QUESTION, Path.getQuestionFilePath());
            load(eTableType.SKILL, Path.getSkillFilePath());
            load(eTableType.MILESTONE, Path.getMilestoneFilePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void load(eTableType type, String path) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File(path);
        if (file.exists()) {
            FileInputStream is = new FileInputStream(file);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    // put to db
                    switch (type) {

                        case LESSON:
                            Lesson lesson = gson.fromJson(currentLine, Lesson.class);
                            lessonMap.put(lesson.getId(), lesson);
                            break;
                        case QUESTION:
                            Question question = gson.fromJson(currentLine, Question.class);
                            questionMap.put(question.getId(), question);
                            //put to answer tree
                            HashMap<String, String> answers = answerTree.get(question.getType());
                            if (answers == null) {
                                answers = new HashMap<>();
                                answerTree.put(question.getType(), answers);
                            }
                            answers.put(question.getQuestionTitle() + "|" + question.getQuestionContent(), question.getAnswer());
                            break;
                        case SKILL:
                            Skill skill = gson.fromJson(currentLine, Skill.class);
                            skillMap.put(skill.getId(), skill);
                            break;
                        case MILESTONE:
                            Milestone milestone = gson.fromJson(currentLine, Milestone.class);
                            milestoneMap.put(milestone.getId(), milestone);
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public Map<Integer, Milestone> getMilestoneMap() {
        return milestoneMap;
    }

    public Map<Integer, Skill> getSkillMap() {
        return skillMap;
    }

    public Map<Integer, Lesson> getLessonMap() {
        return lessonMap;
    }

    public Map<Integer, Question> getQuestionMap() {
        return questionMap;
    }

    public enum eTableType {
        LESSON,
        QUESTION,
        SKILL,
        MILESTONE,

    }
}
