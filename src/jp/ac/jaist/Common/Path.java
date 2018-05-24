package jp.ac.jaist.Common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by d on 5/24/2016.
 */
public class Path {
    private static final String dbPathRootWindows = "C:\\DuolingoProject";
    private static final String dbPathRootUNIX = "/DuolingoProject";
    private static String dataPath, logPath;
    private static String  skillFilePath, lessonFilePath,questionFilePath, milestoneFilePath;

    public static void buildRoot() throws IOException {
        String root;
        if (File.separator.equals("\\"))
            root = dbPathRootWindows;
        else
            root = dbPathRootUNIX;
        // making dir
        dataPath = root + File.separator + "data";
        File fileData = new File(dataPath);
        if (!fileData.exists()) {
            fileData.mkdirs();
        }
        // making file
        skillFilePath = dataPath + File.separator + "skill.txt";
        lessonFilePath = dataPath + File.separator + "lesson.txt";
        questionFilePath = dataPath + File.separator + "question.txt";
        milestoneFilePath = dataPath + File.separator + "milestone.txt";

        File skillFile = new File(skillFilePath);
        File lessonFile = new File(lessonFilePath);
        File questionFile = new File(questionFilePath);
        File stageFile = new File(milestoneFilePath);

        if (!skillFile.exists()) {
            skillFile.createNewFile();
            new FileOutputStream(skillFile, true).close();
        }

        if (!lessonFile.exists()) {
            lessonFile.createNewFile();
            new FileOutputStream(lessonFile, true).close();
        }
        if (!questionFile.exists()) {
            questionFile.createNewFile();
            new FileOutputStream(questionFile, true).close();
        }
        if (!stageFile.exists()) {
            stageFile.createNewFile();
            new FileOutputStream(stageFile, true).close();
        }

    }

    public static String getDataPath() {
        return dataPath;
    }

    public static void setDataPath(String dataPath) {
        Path.dataPath = dataPath;
    }

    public static String getLogPath() {
        return logPath;
    }

    public static void setLogPath(String logPath) {
        Path.logPath = logPath;
    }

    public static String getSkillFilePath() {
        return skillFilePath;
    }

    public static void setSkillFilePath(String skillFilePath) {
        Path.skillFilePath = skillFilePath;
    }


    public static String getLessonFilePath() {
        return lessonFilePath;
    }

    public static void setLessonFilePath(String lessonFilePath) {
        Path.lessonFilePath = lessonFilePath;
    }

    public static String getQuestionFilePath() {
        return questionFilePath;
    }

    public static void setQuestionFilePath(String questionFilePath) {
        Path.questionFilePath = questionFilePath;
    }

    public static String getMilestoneFilePath() {
        return milestoneFilePath;
    }

    public static void setMilestoneFilePath(String milestoneFilePath) {
        Path.milestoneFilePath = milestoneFilePath;
    }

}

