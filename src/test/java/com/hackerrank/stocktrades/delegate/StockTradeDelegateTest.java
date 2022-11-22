package com.hackerrank.stocktrades.delegate;

import com.hackerrank.stocktrades.exception.StockNotFoundException;
import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.model.Trade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockTradeDelegateTest {

    @Mock
    StockTradeRepository stockTradeRepository;

    @InjectMocks
    StockTradeDelegate stockTradeDelegate;

    @Test
    public void testCreateStockTrade(){
        Trade trade = getTradeDataSuccess();
        StockTrade stockTrade = trade.toEntity();
        when(stockTradeRepository.save(stockTrade)).thenReturn(stockTrade);
        Trade tradeCreated = stockTradeDelegate.createStockTrade(trade);
        assertEquals(trade.getType(),tradeCreated.getType());
        assertEquals(trade.getSymbol(),tradeCreated.getSymbol());
        assertEquals(trade.getPrice(),tradeCreated.getPrice());
    }

    @Test
    public void testReadStock_Available(){
        Trade trade = getTradeDataSuccess();
        StockTrade stockTrade = trade.toEntity();
        when(stockTradeRepository.findById(10)).thenReturn(Optional.of(stockTrade));
        Trade stockInformation = stockTradeDelegate.readStockTrade(10);
        assertEquals(trade.getType(),stockInformation.getType());
        assertEquals(trade.getSymbol(),stockInformation.getSymbol());
        assertEquals(trade.getPrice(),stockInformation.getPrice());
    }

    @Test(expected = StockNotFoundException.class)
    public void testReadStock_NoDataFound(){
        when(stockTradeRepository.findById(10)).thenReturn(Optional.empty());
        stockTradeDelegate.readStockTrade(10);
    }

    @Test
    public void testReadStocksSuccess_With_Type_UserId(){
        List<StockTrade> stockTradeList = new ArrayList<>();
        stockTradeList.add(getTradeDataSuccess().toEntity());
        when(stockTradeRepository.findByTypeOrUserIdOrderByUserIdAsc("sell",10)).thenReturn(Optional.of(stockTradeList));
        List<Trade> retrievedStockList = stockTradeDelegate.readStocks("sell",10);
        assertEquals(1,retrievedStockList.size());
        assertEquals("sell",retrievedStockList.get(0).getType());

    }

    @Test
    public void testReadStocksSuccess_With_Type(){
        when(stockTradeRepository.findByTypeOrUserIdOrderByUserIdAsc("sell",null)).thenReturn(Optional.of(getStockTradeList()));
        List<Trade> retrievedStockList = stockTradeDelegate.readStocks("sell",null);
        assertEquals(1,retrievedStockList.size());
        assertEquals("sell",retrievedStockList.get(0).getType());

    }

    @Test
    public void testReadStocksSuccess_With_userId(){
        when(stockTradeRepository.findByTypeOrUserIdOrderByUserIdAsc(null,10)).thenReturn(Optional.of(getStockTradeList()));
        List<Trade> retrievedStockList = stockTradeDelegate.readStocks(null,10);
        assertEquals(1,retrievedStockList.size());
        assertEquals("sell",retrievedStockList.get(0).getType());
    }

    @Test
    public void testReadStocksSuccess_Without_Type_UserId(){
        when(stockTradeRepository.findAll(Sort.by(Sort.Direction.ASC,"userId"))).thenReturn(getStockTradeList());
        List<Trade> retrievedStockList = stockTradeDelegate.readStocks(null,null);
        assertEquals(1,retrievedStockList.size());
        assertEquals("sell",retrievedStockList.get(0).getType());
    }


    private List<StockTrade> getStockTradeList(){
        List<StockTrade> stockTradeList = new ArrayList<>();
        stockTradeList.add(getTradeDataSuccess().toEntity());
        return stockTradeList;
    }
    private Trade getTradeDataSuccess(){
        return Trade.builder().type("sell").price(10).userId(1).id(1).symbol("xm").timestamp(100L).shares(1).build();
    }

}
