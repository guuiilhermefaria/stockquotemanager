package com.guilhermefaria.stockquotemanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermefaria.stockquotemanager.entity.StockQuote;
import com.guilhermefaria.stockquotemanager.repository.StockQuoteRepository;
import com.guilhermefaria.stockquotemanager.service.CacheService;

@RestController
@RequestMapping("/stock")
public class StockQuoteController {

	@Autowired
	private StockQuoteRepository stockQuoteRepository;

	@Autowired
	private CacheService cacheService;

	@GetMapping
	public List<StockQuote> getAllStockQuotes() {
		return this.stockQuoteRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<StockQuote> getStockQuoteById(@PathVariable(value = "id") String stockQuoteId) {
		Example<StockQuote> example = Example.of(new StockQuote(stockQuoteId));

		Optional<StockQuote> stockQuote = this.stockQuoteRepository.findOne(example);
		if (stockQuote.isPresent()) {
			return ResponseEntity.ok(stockQuote.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<StockQuote> create(@RequestBody StockQuote stockQuote) {
//		System.out.println(stockQuote.toString());
		if (cacheService.containStock(stockQuote.getId())) {
			StockQuote stockQuoteDao = this.stockQuoteRepository.save(stockQuote);
			return ResponseEntity.ok(stockQuoteDao);
		}
		return ResponseEntity.notFound().build();
	}

}
