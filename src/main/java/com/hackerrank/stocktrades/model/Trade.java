package com.hackerrank.stocktrades.model;


import com.hackerrank.stocktrades.validation.Shares;
import com.hackerrank.stocktrades.validation.StockType;
import lombok.*;

@ToString
@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trade {


    private Integer id;
    @StockType
    private String type;
    private Integer userId;
    private String symbol;
    @Shares
    private Integer shares;
    private Integer price;
    private Long timestamp;

    public  StockTrade toEntity(){
      return  StockTrade.builder().type(getType())
              .userId(getUserId())
              .symbol(getSymbol())
              .shares(getShares())
              .price(getPrice())
              .timestamp(getTimestamp()).build();
    }

    public  Trade fromEntity(StockTrade stockTrade){
        return  Trade.builder().type(stockTrade.getType()).id(stockTrade.getId())
                .userId(stockTrade.getUserId())
                .symbol(stockTrade.getSymbol())
                .shares(stockTrade.getShares())
                .price(stockTrade.getPrice())
                .timestamp(stockTrade.getTimestamp()).build();
    }

}
