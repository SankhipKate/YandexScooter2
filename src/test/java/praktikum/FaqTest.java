package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.pages.FaqPage;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertEquals;

//параметризация
@RunWith(Parameterized.class)
//Выпадающий список в разделе «Вопросы о важном».
// Тебе нужно проверить:
// когда нажимаешь на стрелочку, открывается соответствующий текст
public class FaqTest {
    //переменная для массива вопросов
    private final String itemId;

    //открыть браузер один раз
    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    //Конструктор класса
    public FaqTest(String itemId) {
        this.itemId = itemId;
    }

    //открываем страницу и закрываем куки
    @BeforeClass
    public static void acceptCookies() {
        var mainPage = new MainPage(driverRule.getDriver());
        mainPage.openWebPage();
        mainPage.clickAcceptCookiesButton();
    }

    @Parameterized.Parameters
    public static Object[][] faqData() {
        return new Object[][]{
                {"0"},
                {"1"},
                {"2"},
                {"3"},
                {"4"},
                {"5"},
                {"6"},
                {"7"}
        };
    }

    //проверяем список Вопросы о важном
    @Test
    public void clickOnFaqItem() throws Exception {
        FaqPage faqPage = new FaqPage(driverRule.getDriver());
        //проверить что список свернут, кликнуть на вопрос и подождать появление текста
        faqPage.checkAnswerIsInvisible(itemId).clickOnQuestion(itemId).waitForAnswer(itemId);
        // Получаем текст ответа
        String actualAnswerText = faqPage.getAnswerText(itemId);
        // Проверяем текст ответа
        String expectedAnswerText = faqPage.getExpectedText(itemId);
        assertEquals(FaqPage.MISMATCHED_TEXT_ERROR, expectedAnswerText, actualAnswerText);
    }
}