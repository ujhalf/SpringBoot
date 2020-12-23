package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/23 20:06
 * @Version 1.0
 * @Description
 */
@Service
public class FixedTaskService {
    @Autowired
    RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(FixedTaskService.class);

    @Scheduled(fixedDelay = 1000)
    public void getQuote() {
        Quote quote = restTemplate.getForObject(
                "https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
    }
}
