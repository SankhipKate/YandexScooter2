package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

//описания кнопок и методов обеих страниц заказа
public class OrderPage {
    //константы
    public static final By FIELD_NAME = By.xpath("//input[@placeholder='* Имя']");
    public static final By FIELD_SURNAME = By.xpath("//input[@placeholder='* Фамилия']");
    public static final By FIELD_ADDRESS = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    public static final By METROSTATION_LIST = By.xpath("//input[@placeholder='* Станция метро']");
    public static final By METROSTATION = By.xpath(".//li[@data-index='0']");
    public static final By FIELD_PHONE = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    public static final By NEXT_BUTTON = By.className("Button_Middle__1CSJM");
    public static final By CALENDAR = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    public static final By DATE = By.className("react-datepicker__day--019");
    public static final By LIST_HOW_MANY_DAYS = By.className("Dropdown-placeholder");
    public static final By HOW_MANY_DAYS = By.xpath(".//*[@id='root']/div/div[2]/div[2]/div[2]/div[2]/div[1]");
    public static final By ORDER_BUTTON2 = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");
    public static final By YES_BUTTON = By.xpath("//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");
    private final By orderCreateWindow = By.xpath("//*[text() = 'Заказ оформлен']");
    public WebDriver driver;

    //Конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //вспомогательный метод для явного ожидания видимости элемента
    public void waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //вспомогательный метод для явного ожидания кликабельности элемента
    public void waitForClickable(By locator, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Методы взаимодействия с элементами

    //ввести имя
    public void inputName(String firstName) {
        driver.findElement(FIELD_NAME).sendKeys(firstName);
    }

    //ввести фамилию
    public void inputSurname(String surName) {
        driver.findElement(FIELD_SURNAME).sendKeys(surName);
    }

    //ввести адрес
    public void inputAddress(String address) {
        driver.findElement(FIELD_ADDRESS).sendKeys(address);
    }

    //раскрыть список станций метро
    public void clickMetroStation() {
        driver.findElement(METROSTATION_LIST).click();
    }

    //ожидание видимости и выбрать станцию метро из раскрытого списка
    public void chooseMetroStation() {
        waitForElementVisible(METROSTATION);
        driver.findElement(METROSTATION).click();
    }

    //ввести телефон
    public void inputPhone(String phone) {
        driver.findElement(FIELD_PHONE).sendKeys(phone);
    }

    //клик Далее
    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }

    //открыть календарь
    public void openCalendar() {
        driver.findElement(CALENDAR).click();
    }

    //ожидание видимости и выбрать дату
    public void chooseDate() {
        waitForElementVisible(DATE);
        driver.findElement(DATE).click();
    }

    //раскрыть список срок аренды
    public void openListHowManyDays() {
        driver.findElement(LIST_HOW_MANY_DAYS).click();
    }

    //ожидание видимости и выбрать сколько дней
    public void chooseHowManyDays() {
        waitForElementVisible(HOW_MANY_DAYS);
        driver.findElement(HOW_MANY_DAYS).click();
    }

    // клик по кнопке Заказать
    public void clickOrderButton2() {
        driver.findElement(ORDER_BUTTON2).click();
    }

    // ожидание кликабельности и клик по кнопке Да в окне Хотите оформить заказ?
    public void clickYes() {
        waitForClickable(YES_BUTTON, driver);
        driver.findElement(YES_BUTTON).click();
    }

    //ожидание и проверить появление окна Заказ оформлен
    public void checkOrderSuccess() {
        waitForElementVisible(orderCreateWindow);
        assertTrue(driver.findElement(orderCreateWindow).isDisplayed());
    }
}
