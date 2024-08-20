package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;
import praktikum.pages.OrderPage;

//Заказ самоката.
// Нужно проверить весь флоу позитивного сценария с двумя наборами данных.
// Проверить точки входа в сценарий, их две:
// кнопка «Заказать» вверху страницы и внизу.
//Из чего состоит позитивный сценарий:
//Нажать кнопку «Заказать». На странице две кнопки заказа.
//Заполнить форму заказа.
//Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.


@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final boolean isHeader;
    private  final String firstName;
    private  final String surName;
    private  final String phone;
    private  final String address;

    //вместо бефор и афтер
    @Rule
    public DriverRule factory = new DriverRule();

    // конструктор класса
    public OrderTest(  String firstName, String surName, String address, String phone, boolean isHeader) {
        this.firstName = firstName;
        this.surName = surName;
        this.phone = phone;
        this.address = address;
        this.isHeader = isHeader;
    }

    //параметризация данных клиента
    @Parameterized.Parameters
    public static Object[][] getClientData() {
        return new Object[][] {
                { "Иван", "Ургант", "Москва", "+79062113300", true},
                { "Алла", "Пугачева", "Воронеж", "+79111123131", false},
        };
    }


    //позитивный флоу заказа самоката
    @Test
    public void openMainPage() throws Exception {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var orderPage = new OrderPage(driver);

        //открыть вебстраницу
        mainPage.openWebPage();

        // кликнуть кнопку принять куки
        mainPage.clickAcceptCookiesButton();

        // кликнуть кнопку Заказать - обе кнопки включены в набор для параметризации
        if(isHeader) {
            mainPage.clickOrderTopButton();
        } else {
            mainPage.clickOrderBottomButton();
        }

        // через параметризацию заполнить поля заказа на 1 странице
        //ввести имя
        orderPage.inputName(firstName);
        //ввести фамилию
        orderPage.inputSurname(surName);
        //ввести адрес
        orderPage.inputAddress(address);
        //раскрыть список станций метро
        orderPage.clickMetroStation();
        //дождаться раскрытия списка и выбрать станцию
        orderPage.chooseMetroStation();
        //ввести телефон
        orderPage.inputPhone(phone);
        // кликнуть кнопку Далее
        orderPage.clickNextButton();

        //переход на экран про аренду
        // открыть календарь
        orderPage.openCalendar();
        //дождаться появления календаря и выбрать дату
        orderPage.chooseDate();
        //раскрыть список срок аренды
        orderPage.openListHowManyDays();
        //дождаться появления списка и выбрать срок
        orderPage.chooseHowManyDays();
        //выбрать из чекбокса - можно не выбирать, т.к. параметр необязательный
        //ввести коммент - можно не вводить, т.к. параметр необязательный
        // кликнуть кнопку Заказать
        orderPage.clickOrderButton2();

        //дождаться появления окна Хотите оформить заказ? и кликнуть кнопку Да
        orderPage.clickYes();

        //дождаться появления окна Заказ оформлен и проверить что это окно появилось
        orderPage.checkOrderSuccess();
    }
}


