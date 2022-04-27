import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import pageobject.MainPage;
import pageobject.UserMenuItems;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class ParameterizedTests {

    @BeforeEach
    public void setUp(){
        browserSize = "1920x1080";
    }

    @CsvSource(value = {
            "Мсква, Москва, Москва",
            "Советск, Щекинский район, Советск",
            "dtkbrbq yjdujhjl, Великий Новгород, Великий Новгород"
    }
    )

    @ParameterizedTest(name="Выбор гео {2} при вводе {0} и клике в списке {1}")
    public void setGeoPositionTest(String city, String locality, String currentGeo){
        Selenide.open(MainPage.MAIN, MainPage.class)
                .chooseGeoPosition()
                .setCity(city)
                .setCityLocality(locality);
        Assertions.assertEquals(currentGeo,$x("//div[@role='navigation']//button//span/span").getText());
    }

    static Stream<Arguments> catalogStructureTest(){
        return Stream.of(
                Arguments.of("Обувь", List.of(
                        "Женщинам",
                        "Мужчинам",
                        "Детям",
                        "Уход и аксессуары")),
                Arguments.of("Одежда", List.of(
                        "Женщинам",
                        "Мужчинам",
                        "Детям",
                        "Спецодежда",
                        "Уход за одеждой"))
        );
    }

    @MethodSource("catalogStructureTest")
    @ParameterizedTest(name = "Структура подменю пункта {0} каталога")
    public void catalogStructureTest(String menuSection, List<String> expectedMenuItems){
        MainPage mainPage = open(MainPage.MAIN, MainPage.class);
        mainPage.openCatalog();
        mainPage.lookOverSection(menuSection);
        List<String> actualMenuItemsList = mainPage.getMenuItemsList();
    Assertions.assertEquals(expectedMenuItems, actualMenuItemsList);}

    @EnumSource(UserMenuItems.class)
    @ParameterizedTest(name = "Открытие страницы по иконке {0}")
    public void userMenuPages(UserMenuItems userMenuItem){
        MainPage mainPage = open(MainPage.MAIN, MainPage.class);
        mainPage.userMenuItemClick(userMenuItem);
        Assertions.assertEquals(userMenuItem.urlUserMenuItem, url().substring(19));
    }
}