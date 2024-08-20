package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.EnvConfig;

//   описания кнопок главной страницы и методов с ними
public class MainPage {
    // Локаторы элементов на главной странице
    //кнопка принять куки
    private final By ACCEPT_COOKIES_BUTTON = By.id("rcc-confirm-button");
    //кнопка ЗАКАЗАТЬ  верхняя и нижняя
    private final By ORDER_TOP_BUTTON = By.className("Button_Button__ra12g");
    private final By ORDER_BOTTOM_BUTTON = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    private final WebDriver driver;

    //Конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы взаимодействия с элементами

    //открыть вебстраницу
    public void openWebPage() {
        driver.get(EnvConfig.BASE_URL);
    }

    // кликнуть кнопку принять куки
    public void clickAcceptCookiesButton() {
        driver.findElement(ACCEPT_COOKIES_BUTTON).click();
    }

    // кликнуть кнопку Заказать верхнюю
    public void clickOrderTopButton() {
        driver.findElement(ORDER_TOP_BUTTON).click();
    }

    // кликнуть кнопку Заказать нижнюю
    public void clickOrderBottomButton() {
        driver.findElement(ORDER_BOTTOM_BUTTON).click();
    }
}