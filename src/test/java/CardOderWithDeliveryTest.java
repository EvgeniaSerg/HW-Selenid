import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOderWithDeliveryTest {
    private String generateDate(int addDays, String patterns) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(patterns));
    }

    @Test
    public void shouldTestData() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Нижний Новгород");
        String planningDate = generateDate(3,"dd.MM.yyyy");
        $(("[data-test-id='date'] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), (Keys.DELETE));
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Прохорова Анна-Мария");
        $("[data-test-id=phone] input").setValue("+79162354864");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("[class=notification__title]").shouldHave(exactText("Успешно!"));
        $("[class=notification__content] ").shouldHave(exactText("Встреча успешно забронирована на " + planningDate));

    }

}
