package ru.netology.card.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DiliveryCard {
    private DiliveryCard() {
    }

    public static String generationCity() {
        var cities = new String[]{"Уфа", "Москва", "Санкт-Петербург", "Екатеринбург", "Саранск", "Абакан", "Астрахань",
                "Краснодар", "Воронеж", "Барнаул", "Липецк", "Псков", "Кемерово", "Омск", "Казань", "Оренбург", "Иркутск",
                "Якутск", "Брянск", "Владимир", "Чита", "Орёл", "Курган", "Курск", "Кострома", "Мурманск", "Пенза", "Магадан"
        };
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generationDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    public static String generationName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generationPhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generationUser(String locale) {
            return new UserInfo(generationCity(), generationName(locale), generationPhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String citi;
        String name;
        String phone;
    }


}
