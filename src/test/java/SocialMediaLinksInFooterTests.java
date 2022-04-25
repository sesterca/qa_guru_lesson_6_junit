import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pageobject.MainPage;

import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SocialMediaLinksInFooterTests {

    @BeforeEach
    public void setUp(){
        browserSize = "1920x1080";
    }

    @AfterEach
    public void tearDown(){
        getWebDriver().quit();
    }

    @ValueSource(strings = {
            "VK",
            "Одноклассники",
            "Telegram"
        }
    )

    @ParameterizedTest(name = "Проверка ссылки в лого соцсети {0}")
    public void goToSocialMedia(String socialMediaName){
        Selenide.open(MainPage.MAIN, MainPage.class)
                .scrollToFooter()
                .goToSocial(socialMediaName);
        Assertions.assertEquals(2, getWebDriver().getWindowHandles().size());
    }
}
