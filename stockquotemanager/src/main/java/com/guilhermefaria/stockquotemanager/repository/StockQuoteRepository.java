package com.guilhermefaria.stockquotemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilhermefaria.stockquotemanager.entity.StockQuote;

@Repository
public interface StockQuoteRepository extends JpaRepository<StockQuote, Long>{

}
