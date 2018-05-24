package jp.ac.jaist.Crawler.Action;

import jp.ac.jaist.Common.obj.DuolingoObject;
import jp.ac.jaist.Common.obj.Lesson;
import jp.ac.jaist.Common.obj.Question;
import jp.ac.jaist.Common.obj.eQuestionType;
import jp.ac.jaist.Database.Database;
import jp.ac.jaist.Database.IdGenerator;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 11:19 PM 7/3/17.
 */
public class GettingQuestion extends MainAction {
    private String progressbar = "";

    @Override
    public void run(RemoteWebDriver driver, RemoteWebDriver driver1, RemoteWebDriver driver2, DuolingoObject parent) {
        int numquestion = 0;
        double process = 0;
        do {
            boolean isNewQuestion = false;
            WebElement echallenge = driver2.findElement(By.cssSelector("div._1eYrt._1Y5M_"));
            Question question = new Question();
            question.setId(IdGenerator.getQuestionId());
            question.setType(getQuestionType(echallenge.getAttribute("data-test")));
            question.setQuestionTitle(echallenge.findElement(By.cssSelector("*[data-test=\"challenge-header\"]")).getText());
            switch (question.getType()) {
                case Challenge_Judge:
                    break;
                case Challenge_Listen:
                    break;
                case Challenge_Form:
                    WebElement promt = driver2.findElement(By.cssSelector("*[data-test=\"challenge-form-prompt\"]"));
                    question.setQuestionContent(promt.getText());
                    isNewQuestion = answering(driver2, question);
                    break;
                case Challenge_Name:
                    List<String> contentList = new ArrayList<>();
                    for (WebElement e : echallenge.findElements(By.tagName("li")))
                        contentList.add(e.getAttribute("style"));
                    question.setQuestionContent(contentList.toString());
                    isNewQuestion = answering(driver2, question);
                    break;
                case Challenge_Select:
                    List<String> contentList1 = new ArrayList<>();
                    for (WebElement e : echallenge.findElements(By.tagName("li")))
                        contentList1.add(e.getText());
                    question.setQuestionContent(contentList1.toString());
                    isNewQuestion = answering(driver2, question);
                    break;
                case Challenge_Translate:
                    question.setQuestionContent(driver2.findElement(By.cssSelector("*[data-test=\"challenge-translate-prompt\"]")).getText());
                    isNewQuestion = answering(driver2, question);
                    break;
                default:
                    break;
            }
            if (isNewQuestion) {
                numquestion++;
                ((Lesson) parent).getQuestionIds().add(question.getId());
            }
            System.out.println(question.toString());
            String[] temp = progressbar.split(" ");
            process = Double.parseDouble(temp[temp.length - 1].replaceAll("%;", ""));
        } while (process < 100);
        ((Lesson) parent).setNumberOfQuestion(numquestion);

    }

    public boolean answering(RemoteWebDriver driver, Question question) {
        boolean isAnswerExist = false;
        WebElement echallenge = driver.findElement(By.cssSelector("div._1eYrt._1Y5M_"));
        //check answer
        String answer = "";
        String questionKey = question.getQuestionTitle() + "|" + question.getQuestionContent();
        HashMap<String, String> answerMap = Database.answerTree.get(question.getType());
        if (answerMap == null) {
            answerMap = new HashMap<>();
            Database.answerTree.put(question.getType(), answerMap);
        } else if (answerMap.containsKey(questionKey)) {
            isAnswerExist = true;
            //this code so shit -> should be optimized in the future
//            answer = answerMap.get(questionKey).split("\\.,")[0].split("\\?,")[0].split("!,")[0].split(", newspaper")[0];
            answer = answerMap.get(questionKey);
//            int countDot = StringUtils.countMatches(answer, ".");
//            int countQuestionmark = StringUtils.countMatches(answer, "?");
//            int countEmotionmark = StringUtils.countMatches(answer, "!");
            int countCommaA = StringUtils.countMatches(answer, ",");
            int countCommaQ = StringUtils.countMatches(question.getQuestionContent(), ",");
//            if (countEmotionmark >= 2 || countQuestionmark >= 2 || countDot >= 2
//                    || countDot == 0 && countEmotionmark == 0 && countQuestionmark == 0) {
            if (countCommaA > countCommaQ
                    || question.getType().equals(eQuestionType.Challenge_Name)
                    || question.getType().equals(eQuestionType.Challenge_Select)) {
                answer = answerMap.get(questionKey).split(",")[0];
                System.out.println(answer);
                System.out.println(answerMap.get(questionKey));
            }
        }

        if (isAnswerExist) {
            //answer and continue
            switch (question.getType()) {
                case Challenge_Judge:
                    break;
                case Challenge_Listen:
                    break;
                case Challenge_Form:
                    Select dropdown = new Select(driver.findElement(By.tagName("select")));
                    dropdown.selectByVisibleText(answer);
                    break;
                case Challenge_Name:
                    WebElement inputBox = driver.findElement(By.cssSelector("*[data-test=\"challenge-name-input\"]"));
                    inputBox.clear();
                    inputBox.sendKeys(answer);
                    break;
                case Challenge_Select:
                    List<WebElement> options = echallenge.findElements(By.tagName("li"));
                    for (WebElement op : options) {
                        String potentialAns = op.findElements(By.tagName("span")).get(1).getText().toLowerCase().trim().replaceAll(" ", "");
                        if (potentialAns.equals(answer.toLowerCase().trim().replaceAll(" ", ""))) {
                            op.findElement(By.cssSelector("*[data-test=\"player-radio\"]")).click();
                        }

                    }
                    break;
                case Challenge_Translate:
                    WebElement inputBox1 = driver.findElement(By.cssSelector("*[data-test=\"challenge-translate-input\"]"));
                    inputBox1.clear();
                    inputBox1.sendKeys(answer);
                    break;
                default:
                    break;
            }
            next(driver);
            breathe(1);
            progressbar = getProgressBar(driver);
            next(driver);
            return false;
        } else {
            //skip and getting answer
            skip(driver);
            progressbar = getProgressBar(driver);
            answer = driver.findElement(By.cssSelector("div._34Ym5")).getText();
            question.setAnswer(answer);
            database.saveData(Database.eTableType.QUESTION, question);
            //put answer to answerMap
            answerMap.put(questionKey, answer);
            //next question
            next(driver);
            return true;
        }

    }

    public void skip(RemoteWebDriver driver) {
        WebElement skipButton = driver.findElement(By.cssSelector("*[data-test=\"player-skip\"]"));
        skipButton.click();
        breathe(1);
    }

    public void next(RemoteWebDriver driver) {
        WebElement nextButton = driver.findElement(By.cssSelector("*[data-test=\"player-next\"]"));
        nextButton.click();
        breathe(1);
    }

    public eQuestionType getQuestionType(String type) {
        if (type.contains("challenge-judge")) return eQuestionType.Challenge_Judge;
        else if (type.contains("challenge-listen")) return eQuestionType.Challenge_Listen;
        else if (type.contains("challenge-name")) return eQuestionType.Challenge_Name;
        else if (type.contains("challenge-select")) return eQuestionType.Challenge_Select;
        else if (type.contains("challenge-translate")) return eQuestionType.Challenge_Translate;
        else if (type.contains("challenge-form")) return eQuestionType.Challenge_Form;
        else return eQuestionType.Challenge_Other;
    }

    public String getProgressBar(RemoteWebDriver driver) {
        WebElement progressBar = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div[1]/div[1]/div[4]/div/div"));
        return progressBar.getAttribute("style");
    }
}
