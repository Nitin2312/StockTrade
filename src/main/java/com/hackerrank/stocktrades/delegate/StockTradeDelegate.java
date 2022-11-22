package com.hackerrank.stocktrades.delegate;

import com.hackerrank.stocktrades.exception.StockNotFoundException;
import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.model.Trade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class StockTradeDelegate {
    private final StockTradeRepository stockTradeRepository;

    private static String STOCK_USERID="userId";

    @Autowired
    private StockTradeDelegate(StockTradeRepository stockTradeRepository) {
        this.stockTradeRepository = stockTradeRepository;
    }
    Predicate<String> checkIfTypeExists = Objects::nonNull;
    Predicate<Integer> checkIfUserIdExists = Objects::nonNull;

    public Trade createStockTrade(Trade trade) {
        return buildFromStockTrade(stockTradeRepository
                .save(trade.toEntity()));
    }

    public Trade readStockTrade(Integer id){
        return  stockTradeRepository.findById(id).map(this::buildFromStockTrade).orElseThrow(StockNotFoundException::new);
    }

    public List<Trade> readStocks(String type, Integer userId){
       return checkIfTypeExists.test(type) || checkIfUserIdExists.test(userId)?stockTradeRepository.findByTypeOrUserIdOrderByUserIdAsc(type,userId).map(stockTrades -> stockTrades.stream().map(this::buildFromStockTrade).collect(Collectors.toList())).orElseGet(ArrayList::new):
               stockTradeRepository.findAll(Sort.by(Sort.Direction.ASC,STOCK_USERID)).stream().map(this::buildFromStockTrade).collect(Collectors.toList());

    }




    private Trade buildFromStockTrade(StockTrade stockTrade) {
        return Trade.builder().build().fromEntity(stockTrade);
    }
}
