package com.mipt.smehelper;

import com.mipt.smehelper.models.Notification;
import com.mipt.smehelper.models.User;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Data {

    private static final Data INSTANCE = new Data();

    public Hashtable<Integer, String> matches = new Hashtable<>();

    // Logged in user
    private User user;
    // Workers for current user, may be null
    private List<User> workers;

    private List<Notification> notifications;

    private Data(){
        matches.put(9, "Автомобили");
        matches.put(10, "Запчасти и аксессуары");
        matches.put(11, "Водный транспорт");
        matches.put(14, "Мотоциклы и мототехника");
        matches.put(19, "Ремонт и строительство");
        matches.put(20, "Мебель и интерьер");
        matches.put(21, "Бытовая техника");
        matches.put(23, "Комнаты");
        matches.put(24, "Квартиры");
        matches.put(25, "Дома, дачи, коттеджи");
        matches.put(26, "Земельные участки");
        matches.put(27, "Одежда, обувь, аксессуары");
        matches.put(28, "Часы и украшения");
        matches.put(29, "Детская одежда и обувь");
        matches.put(30, "Товары для детей и игрушки");
        matches.put(31, "Настольные компьютеры");
        matches.put(32, "Аудио и видео");
        matches.put(33, "Билеты и путешествия");
        matches.put(34, "Велосипеды");
        matches.put(36, "Коллекционирование");
        matches.put(38, "Музыкальные инструменты");
        matches.put(39, "Спорт и отдых");
        matches.put(40, "Оборудование для бизнеса");
        matches.put(42, "Коммерческая недвижимость");
        matches.put(81, "Грузовики и спецтехника");
        matches.put(82, "Продукты питания");
        matches.put(83, "Книги и журналы");
        matches.put(84, "Телефоны");
        matches.put(85, "Гаражи и машиноместа");
        matches.put(86, "Недвижимость за рубежом");
        matches.put(87, "Посуда и товары для кухни");
        matches.put(88, "Красота и здоровье");
        matches.put(89, "Собаки");
        matches.put(90, "Кошки");
        matches.put(91, "Птицы");
        matches.put(92, "Аквариум");
        matches.put(93, "Другие животные");
        matches.put(94, "Товары для животных");
        matches.put(96, "Планшеты и электронные книги");
        matches.put(97, "Игры, приставки и программы");
        matches.put(98, "Ноутбуки");
        matches.put(99, "Оргтехника и расходники");
        matches.put(101, "Товары для компьютера");
        matches.put(102, "Охота и рыбалка");
        matches.put(105, "Фототехника");
        matches.put(106, "Растения");
        matches.put(111, "Вакансии");
        matches.put(112, "Резюме");
        matches.put(114, "Предложения услуг");
        matches.put(115, "Запросы на услуги");
        matches.put(116, "Готовый бизнес");

    }

    public static Data getInstance(){
        return INSTANCE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
