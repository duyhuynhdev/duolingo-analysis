package jp.ac.jaist.Crawler.Action;

import jp.ac.jaist.Common.obj.DuolingoObject;
import jp.ac.jaist.Common.obj.Lesson;
import jp.ac.jaist.Common.obj.Skill;
import jp.ac.jaist.Database.Database;
import jp.ac.jaist.Database.IdGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 10:25 PM 7/3/17.
 */
public class GettingLesson extends MainAction {


    @Override
    public void run(RemoteWebDriver driver, RemoteWebDriver driver1, RemoteWebDriver driver2, DuolingoObject parent) {
        List<WebElement> eLessons = driver1.findElements(By.className("kHldG"));
        ((Skill) parent).setNumberOfLesson(eLessons.size());
        int numlesson = 1;
        for (WebElement l : eLessons) {
            Lesson lesson = new Lesson();
            lesson.setId(IdGenerator.getLessonId());
            lesson.setTitle(l.findElement(By.tagName("h3")).getText());
            lesson.setDescription(l.findElement(By.tagName("p")).getText());
            lesson.setUrl(((Skill) parent).getUrl() + "/" + numlesson);
            //getting question
            driver2.get(lesson.getUrl());
            breathe(3);
            new GettingQuestion().run(driver, driver1, driver2, lesson);
            //end of getting question
            database.saveData(Database.eTableType.LESSON, lesson);
            ((Skill) parent).getLessonIds().add(lesson.getId());
            System.out.println(lesson.toString());
            numlesson++;
        }

    }
}
