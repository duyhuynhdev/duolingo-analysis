package jp.ac.jaist.Crawler;

import jp.ac.jaist.Common.obj.DuolingoInfo;
import jp.ac.jaist.Common.obj.DuolingoObject;
import jp.ac.jaist.Crawler.Action.GettingSkillTree;
import jp.ac.jaist.Crawler.Action.Login;
import jp.ac.jaist.Crawler.Action.MainAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 1:15 PM 7/3/17.
 */
public class DuolingoCrawler extends MainAction {
    private RemoteWebDriver driver;
    private RemoteWebDriver driver1;
    private RemoteWebDriver driver2;
    private static final DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
    boolean setup = false;

    @Override
    public void run(RemoteWebDriver driver, RemoteWebDriver driver1, RemoteWebDriver driver2, DuolingoObject parent) {
        driver.get(DuolingoInfo.homepage);
        driver1.get(DuolingoInfo.homepage);
        driver2.get(DuolingoInfo.homepage);
        breathe(2);
        // login
        Login login = new Login();
        login.run(driver, driver1, driver2, null);
        //skill
        GettingSkillTree gettingSkillTree = new GettingSkillTree();
        gettingSkillTree.run(driver, driver1, driver2, null);

    }

    public void run() {
        try {
            setUp();
            run(driver, driver1, driver2, null);
            tearDown();
        } catch (Exception e) {
            e.printStackTrace();
//            tearDown();

        }
    }

    public void setUp() throws Exception {
        if (!setup) {
            driver = createDriver();
            driver1 = createDriver();
            driver2 = createDriver();
        }
    }

    public void tearDown() {
        try {
            driver.quit();
            driver1.quit();
            driver2.quit();
            setup = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
