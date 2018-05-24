package jp.ac.jaist.Crawler.Action;

import jp.ac.jaist.Common.obj.DuolingoInfo;
import jp.ac.jaist.Common.obj.DuolingoObject;
import jp.ac.jaist.Common.obj.Milestone;
import jp.ac.jaist.Common.obj.Skill;
import jp.ac.jaist.Database.Database;
import jp.ac.jaist.Database.IdGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 1:53 PM 7/3/17.
 */
public class GettingSkillTree extends MainAction {
    private final String milestoneRegconization = "Exonérate";
    private final String unavailabeSkillRegconization = "/";
    private final String urlPattern = "";
    @Override
    public void run(RemoteWebDriver driver, RemoteWebDriver driver1, RemoteWebDriver driver2, DuolingoObject parent) {
        driver.get(DuolingoInfo.homepage);
        breathe(2);
        // get all skill tree
        WebElement eSkillTree = driver.findElement(By.cssSelector("*[data-test=\"skill-tree\"]"));
        // get all a tag in skill tree
        List<WebElement> eDivTags = eSkillTree.findElements(By.className("_2GJb6"));
        // filtering skill and milestone
        int numLine = 1;
        int numDiv = eDivTags.size();
        List<Integer> skillIds = new ArrayList<>();
        for(int n = 0; n< numDiv; n++ ){
            WebElement eDivTag = driver.findElement(By.cssSelector("*[data-test=\"skill-tree\"]")).findElements(By.className("_2GJb6")).get(n);
            String text = eDivTag.getText();
            String[] skillTitles = text.split("\\n");
            boolean isSkill= false;
            //get all ATags;
            List<WebElement> eATags = eDivTag.findElements(By.tagName("a"));
            for(int i = 0; i < skillTitles.length;i++){
                String st = skillTitles[i];
                if(st.contains(milestoneRegconization)){
                    Milestone milestone = new Milestone();
                    milestone.setId(IdGenerator.getMilestoneId());
                    milestone.setTitle(st);
                    milestone.setSkillIds(new ArrayList<>(skillIds));
                    database.saveData(Database.eTableType.MILESTONE, milestone);
                    skillIds = new ArrayList<>();
                    System.out.println(milestone.toString());
                }else{
                    isSkill = true;
                    // is kill available?
                    String title = st;
                    int numlesson = 0;
                    if(st.contains(unavailabeSkillRegconization)){
                        title = st.substring(0, st.length()-3);
                        numlesson = Integer.parseInt(st.substring(st.length()-1));
                    }
                    Skill skill = new Skill();
                    skill.setId(IdGenerator.getSkillId());
                    skill.setLineNumber(numLine);
                    skill.setNumberOfLesson(numlesson);
                    skill.setTitle(title);
                    skill.setUrl(eATags.get(i).getAttribute("href"));
                    //getting lesson
                    driver1.get(skill.getUrl());
                    breathe(1);
                    new GettingLesson().run(driver,driver1,driver2, skill);
                    //end getting lesson
                    database.saveData(Database.eTableType.SKILL,skill);
                    System.out.println(skill.toString());
                }
            }
            if(isSkill)
                numLine++;
            //reload pages
            driver.get(DuolingoInfo.homepage);
            breathe(1);
        }


    }
}
