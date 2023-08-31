package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern((pattern)));
    }

    @Test
    void shouldTestForm() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Москва");
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(planningDate);
        $("[data-test-id='name'] .input__control").setValue("Игорь Игорь");
        $("[data-test-id='phone'] .input__control").setValue("+79067777777");
        $("[data-test-id='agreement'] ").click();
        $(".button__text").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));

    }
    @Test
    void should() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Мо");
        $$(".menu-item__control").findBy(text("Москва")).click();
        $("[data-test-id=date] input").click();
        String data = generateDate(3, "MM");
        String data2 = generateDate(7, "MM");
        String bookedDate = generateDate(7, "dd.MM.yyyy");
        if(data.equals(data2)) {
            $$(".calendar__day").findBy(text(generateDate(7, "d"))).click();
        }else {
            $$(".calendar__arrow_direction_right").last().click();
            $$(".calendar__day").findBy(text(generateDate(7, "d"))).click();

        }
         $("[data-test-id='name'] .input__control").setValue("Игорь Игорь");
        $("[data-test-id='phone'] .input__control").setValue("+79067777777");
        $("[data-test-id='agreement'] ").click();
        $(".button__text").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + bookedDate));
    }

}

