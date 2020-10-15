package com.guilhermefaria.stockquotemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermefaria.stockquotemanager.service.CacheService;

@RestController
@RequestMapping("/stockcache")
public class CacheController {

	@Autowired
	private CacheService cacheService;

	@DeleteMapping
	public ResponseEntity<String> getAllStockQuotes() {
		System.out.println("chamou o limpa cache");
		cacheService.clearCache();
		return ResponseEntity.ok("Cache is cleaned.");
	}
}
