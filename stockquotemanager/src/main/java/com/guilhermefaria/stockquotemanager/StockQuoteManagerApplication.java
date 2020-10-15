package com.guilhermefaria.stockquotemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.guilhermefaria.stockquotemanager.rest.StockApiController;

@SpringBootApplication
public class StockQuoteManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockQuoteManagerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initializationNotification() {
		StockApiController apiController = new StockApiController();
		apiController.notification();
	}
}
