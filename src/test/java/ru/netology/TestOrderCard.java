package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestOrderCard {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldSendForm() {
        String planningDate = generateDate(4);
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Ульяновск");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $$("[data-day]").get(1).click();
        $("[data-test-id='name'] input").setValue("Олег Зимов");
        $("[data-test-id='phone'] input").setValue("+78002000600");
        $(".checkbox__box").click();
        $("button.button").click();
        $("[data-test-id='notification']").should(appear, Duration.ofSeconds(20));
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
        $("[class='notification__content']").shouldHave(text("Встреча успешно забронирована на " + planningDate));
    }
}
