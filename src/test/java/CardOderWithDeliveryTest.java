import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOderWithDeliveryTest {
    @Test
    void shouldTestData() throws InterruptedException {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue("Нижний Новгород");
        form.$(("[data-test-id='date'] input")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), (Keys.DELETE));
        form.$("[data-test-id=date] input").setValue("");
        form.$("[data-test-id=name] input").setValue("Прохорова Анна-Мария");
        form.$("[data-test-id=phone] input").setValue("+79162354864");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        form.$("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("[class=notification__title]").shouldHave(exactText("Успешно!"));
        $("[class=notification__content]+").shouldHave(exactText("Встреча успешно забронирована на" + ));

    }

}
