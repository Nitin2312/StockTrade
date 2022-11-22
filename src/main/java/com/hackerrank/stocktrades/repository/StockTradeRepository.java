package com.hackerrank.stocktrades.repository;

import com.hackerrank.stocktrades.model.StockTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockTradeRepository extends JpaRepository<StockTrade, Integer> {

    @Override
    Optional<StockTrade> findById(Integer integer);

    Optional<List<StockTrade>> findByTypeOrUserIdOrderByUserIdAsc(String type,Integer userId);

    List<StockTrade> findAll();

}

