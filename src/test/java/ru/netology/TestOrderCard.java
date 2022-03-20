package ru.netology;

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

    @Test
    public void shouldOrderCardDelivery() {
        Configuration.holdBrowserOpen = true;
        open("http://0.0.0.0:7777/");
        $("[placeholder='Город']").setValue("Ульяновск");
        String data = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(data);
        $("[name='name']").setValue("Летов Олег");
        $("[name='phone']").setValue("+78002000500");
        $(".checkbox").click();
        $(".button__text").click();
        $(Selectors.withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
