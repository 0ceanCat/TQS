import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author wy
 * @date 2021/3/23 10:12
 */

class StocksPortfolioTest {

    @Test
    void getTotalValue() {
        // 1.Prepare a mock to substitute the remote service (@Mock annotation)
        IStockMarket market = mock(IStockMarket.class);

        // 2.Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.
        StocksPortfolio stocksPortfolio = new StocksPortfolio(market);

        // 3.Load the mock with the proper expectations (when...thenReturn)
        when(market.getPrice("A")).thenReturn(10.0);
        when(market.getPrice("B")).thenReturn(8.0);

        // 4.Execute the test (use the service in the SuT)
        stocksPortfolio.addStock(new Stock("A", 2));
        stocksPortfolio.addStock(new Stock("B", 3));
        double result = stocksPortfolio.getTotalValue();

        // 5.Verify the result (assert) and the use of the mock (verify)
        assertEquals(44, result);
        assertThat(result, is(44.0));
        verify(market, times(2)).getPrice(anyString());
    }
}