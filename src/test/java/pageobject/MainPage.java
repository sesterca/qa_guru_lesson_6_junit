package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public static final String MAIN = "https://www.ozon.ru/";

    public SelenideElement footer = $("footer");

    public SelenideElement geoPosition = $x("//div[@role='navigation']//button");

    public SelenideElement cityInput = $x("//div[h2[contains(text(),'Выберите город')]]//div[@class='ui-a0a']");

    public SelenideElement catalog = $("[data-widget='catalogMenu']");

    public ElementsCollection menuItems = $$(byXpath("//div[@data-widget='catalogMenu']//div[@class='e1c']/a/span"));

    public List<String>getMenuItemsList() {return menuItems.texts();}

    public MainPage scrollToFooter(){
        footer.scrollTo();
        return Selenide.page(MainPage.class);
    }

    public MainPage goToSocial(String socialMediaName){
        $(byTitle(socialMediaName)).click();
        return Selenide.page(MainPage.class);
    }

    public MainPage chooseGeoPosition(){
        geoPosition.click();
        return Selenide.page(MainPage.class);
    }

    public MainPage setCity(String city){
        cityInput.click();
        cityInput.setValue(city);
        return Selenide.page(MainPage.class);
    }

    public MainPage setCityLocality(String locality){
        $x("//div[h2[contains(text(),'Выберите город')]]//li//a[contains(text(),'"+locality+"')]").shouldBe(visible).click();
        return Selenide.page(MainPage.class);
    }
    public MainPage openCatalog(){
        catalog.shouldBe(visible).click();
        return Selenide.page(MainPage.class);
    }
    public MainPage lookOverSection(String menuSection){
        $x("//div[@data-widget='catalogMenu']//a/span[contains(text(), '"+menuSection+"')]")
                .shouldBe(Condition.and("can be clicked", visible, enabled), Duration.ofSeconds(4000)).hover();
        return Selenide.page(MainPage.class);
    }

    public void userMenuItemClick(UserMenuItems userMenuItem){
        $x("//span[@class='kc2' and text()='"+userMenuItem.nameUserMenuItem+"']").click();
    }
}
