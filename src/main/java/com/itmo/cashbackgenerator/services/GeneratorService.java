package com.itmo.cashbackgenerator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itmo.cashbackgenerator.model.Cashback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class GeneratorService {

    private final String[] shops = {"magnit", "ozon", "wildberries", "yandexMarket", "h&m", "aliexpress"};
    private final String[] productNames = {"новогодние носки", "гирлянда", "елочная игрушка", "ёлка", "конфеты", "подарочная упаковка", "мандарины",
    "мишура", "открытка", "имбирное печенье", "ароматизированная свеча"};
    private final String[] clients = {"lenika", "alena", "katya", "lena", "sveta"};
    private final Double[] multipliers = {100d, 150d, 200d, 300d, 400d, 500d, 600d, 700d, 800d, 900d};
    private final int[] months = {7, 8, 9, 10, 11, 12};
    private final KafkaTemplate<Long, Cashback> kafkaCashbackTemplate;
    private final ObjectMapper objectMapper;
    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Autowired
    public GeneratorService(KafkaTemplate<Long, Cashback> kafkaCashbackTemplate,
                        ObjectMapper objectMapper) {
        this.kafkaCashbackTemplate = kafkaCashbackTemplate;
        this.objectMapper = objectMapper;
    }


    // каждую минуту создается новый кэшбек
    @Scheduled(initialDelay = 10000, fixedDelay = 60000)
    private void generateCashback() {
        String shopLogin = shops[(int) (Math.random() * shops.length)];
        String client = clients[(int) (Math.random() * clients.length)];
        String productName = productNames[(int) (Math.random() * productNames.length)];
        Double productPrice = Math.ceil(multipliers[(int) (Math.random() * multipliers.length)] * Math.random());
        Calendar calendar = new GregorianCalendar(2021, months[(int)(Math.random() * months.length)] , 5);
        Cashback cashback = new Cashback(shopLogin, client, productName, productPrice, calendar);
        System.out.println("<= sending {}" + writeValueAsString(cashback));
        kafkaCashbackTemplate.send(kafkaTopic, cashback);
    }

    private String writeValueAsString(Cashback dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}

