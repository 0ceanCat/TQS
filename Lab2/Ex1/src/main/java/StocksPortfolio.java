import java.util.ArrayList;
import java.util.List;

/**
 * @author wy
 * @date 2021/3/23 10:01
 */
public class StocksPortfolio {
    private String name;
    private IStockMarket market;
    private List<Stock> stocks;

    public StocksPortfolio() {
        this.stocks = new ArrayList<>();
    }

    public StocksPortfolio(IStockMarket market) {
        this();
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IStockMarket getMarket() {
        return market;
    }

    public void setMarket(IStockMarket market) {
        this.market = market;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double value = 0.0;

        for (Stock stock : stocks) {
            value += stock.getQuantity() * market.getPrice(stock.getName());
        }

        return value;
    }

}
