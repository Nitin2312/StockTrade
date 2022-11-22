package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.delegate.StockTradeDelegate;
import com.hackerrank.stocktrades.model.Trade;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StockTradeRestController {

    private final StockTradeDelegate stockTradeDelegate;

    @Autowired
    public StockTradeRestController(StockTradeDelegate stockTradeDelegate) {
        this.stockTradeDelegate = stockTradeDelegate;
    }

    @ApiOperation(value = "This operation is used to create the stock trade", response=Trade.class)
    @ApiResponses(value={
            @ApiResponse(code=201,message="Successfully Stock is created"),
            @ApiResponse(code=400,message="If the share number or stock type is wrong"),
            @ApiResponse(code=500,message="Something went wrong. Please try again")})
    @PostMapping (value = "/trades",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trade> createStockTrade(@Valid @RequestBody Trade stockTrade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockTradeDelegate.createStockTrade(stockTrade));
    }

    @ApiOperation(value = "This operation is used to get requested  stock trade", response=Trade.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Successfully Stock is retrieved based on the requested stockId"),
            @ApiResponse(code=404,message="Requested Stock is not found"),
            @ApiResponse(code=500,message="Something went wrong. Please try again")})
    @GetMapping(value = "/trades/{tradeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trade> getStockTrade(@PathVariable Integer tradeId) {
        return ResponseEntity.status(HttpStatus.OK).body(stockTradeDelegate.readStockTrade(tradeId));
    }
    @ApiOperation(value = "This operation is used to get all the stocks based on different options", response=Trade.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Successfully Stock is retrieved "),
            @ApiResponse(code=500,message="Something went wrong. Please try again")})
    @GetMapping(value = "/trades", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trade>> getStockTrades(@RequestParam (required = false)Integer userId, @RequestParam (required = false) String type) {
        return ResponseEntity.status(HttpStatus.OK).body(stockTradeDelegate.readStocks(type,userId));
    }


}

