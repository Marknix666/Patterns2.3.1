package ru.netology.card.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.card.data.DiliveryCard;
import ru.netology.card.data.DiliveryCard;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDiliveyTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    @Test
    @DisplayName("Should seccessful plan meeting")
    void ShouldSeccessfulPlanMeeting() {
        DiliveryCard.UserInfo validUser = DiliveryCard.Registration.generationUser("ru");
        int daysToAddForFirsrtMeeting = 4;
        String FirstMeetingDate = DiliveryCard.generationDate(daysToAddForFirsrtMeeting);
        int daysTooAddForSecondMeeting = 7;
        String SecondsMeetingDate = DiliveryCard.generationDate(daysTooAddForSecondMeeting);
        $("[data-test-id=city ]input").setValue(validUser.getCiti());
        $("[data-test-id= date ]input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id= date ]input").setValue(FirstMeetingDate);
        $("[data-test-id= name ]input").setValue(validUser.getName());
        $("[data-test-id= phone ]input").setValue(validUser.getPhone());
        $("[data-test-id= agreement ]").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id= 'success-notification'] .success-notification")
                .shouldHave(exactText("Встреча успешно запланирована на " + FirstMeetingDate))
                .shouldBe(visible);
        $("[data-test-id= date] input").sendKeys(Keys.SHIFT,Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id= date] input").setValue(SecondsMeetingDate);
        $("Запланировать").click();
        $("[data-test-id= 'replan-notification'] .notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(visible);
        $("[data-test-id= 'replan-notification'] button").click();
        $("[data-test-id= 'success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на" + SecondsMeetingDate))
                .shouldBe(visible);
    }
}
