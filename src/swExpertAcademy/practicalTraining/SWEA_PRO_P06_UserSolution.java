package swExpertAcademy.practicalTraining;

import java.util.*;

/**
 * [No. 6] 14616. [Pro] 가게관리 (8/18)<br/><br/>
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초<br/>
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내<br/>
 *
 * <br/>[제약사항]<br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 상품 종류는 600 이하이다.<br/>
 * 3. 각 테스트 케이스에서 buy() 함수의 호출 횟수는 30,000 이하이다.<br/>
 * 4. 각 테스트 케이스에서 sell() 함수의 호출 횟수는 30,000 이하이다.<br/>
 * 5. 각 테스트 케이스에서 모든 함수의 호출 횟수 총합은 80,000 이하이다.<br/>
 *
 * HashMap, AVL Tree
 * */
public class SWEA_PRO_P06_UserSolution implements SWEA_PRO_P06.UserSolution {

    static class Product {

        static class Purchase implements Comparable<Purchase> {
            int bId, price, initialQuantity, quantity;

            public Purchase(int bId, int price, int quantity) {
                this.bId = bId;
                this.price = price;
                this.initialQuantity = quantity;
                this.quantity = quantity;
            }

            @Override
            public int compareTo(Purchase o) {
                if (price != o.price) {
                    return price - o.price;
                } else {
                    return bId - o.bId;
                }
            }
        }

        int number, totalQuantity;

        TreeSet<Purchase> purchases = new TreeSet<>();

        public Product(int number) {
            this.number = number;
        }

        public int buy(int bId, int mPrice, int mQuantity) {
            purchases.add(new Purchase(bId, mPrice, mQuantity));

            return totalQuantity += mQuantity;
        }

        public int cancel(int bId) {
            Iterator<Purchase> it = purchases.iterator();

            while (it.hasNext()) {
                Purchase purchase = it.next();
                if (purchase.bId != bId || purchase.initialQuantity != purchase.quantity)
                    continue;

                totalQuantity -= purchase.quantity;
                it.remove();
                return totalQuantity;
            }

            return -1;
        }

        private void log(String s, Object ... args) {
            System.out.printf(s + "\n", args);
        }
    }

    static class SaleHistory {

        static class Sale {
            int bId, price, initialQuantity, quantity;

            public Sale(int bId, int price, int initialQuantity, int quantity) {
                this.bId = bId;
                this.price = price;
                this.initialQuantity = initialQuantity;
                this.quantity = quantity;
            }
        }

        int sId, productNumber, totalQuantity;

        private ArrayList<Sale> sales = new ArrayList<>();

        public SaleHistory(int sId, int productNumber) {
            this.sId = sId;
            this.productNumber = productNumber;
        }

        public void add(int bId, int price, int initialQuantity, int quantity) {
            sales.add(new Sale(bId, price, initialQuantity, quantity));
            totalQuantity += quantity;
        }
    }

    private HashMap<Integer, Product> products;         // Key: Product Number, Value: Product
    private HashMap<Integer, Integer> buyInfos;         // Key: bId, Value: product number
    private HashMap<Integer, SaleHistory> saleInfos;    // Key: sId, Value: SaleHistory

    @Override
    public void init() {
        products = new HashMap<>();
        buyInfos = new HashMap<>();
        saleInfos = new HashMap<>();
    }

    @Override
    public int buy(int bId, int mProduct, int mPrice, int mQuantity) {
        Product product = products.get(mProduct);

        if (product == null) {
            product = new Product(mProduct);
            products.put(mProduct, product);
        }

        buyInfos.put(bId, mProduct);
        return product.buy(bId, mPrice, mQuantity);
    }

    @Override
    public int cancel(int bId) {
        Integer productNumber = buyInfos.get(bId);
        if (productNumber == null)
            return -1;

        Product product = products.get(productNumber);
        if (product == null)
            return -1;


        int quantity = product.cancel(bId);
        if (quantity != -1) {
            buyInfos.remove(bId);
        }

        return quantity;
    }

    @Override
    public int sell(int sId, int mProduct, int mPrice, int mQuantity) {
        Product product = products.get(mProduct);

        if (product == null || product.totalQuantity < mQuantity)
            return -1;

        int revenue = 0;

        SaleHistory history = new SaleHistory(sId, mProduct);
        saleInfos.put(sId, history);

        Iterator<Product.Purchase> it = product.purchases.iterator();

        while (it.hasNext()) {
            Product.Purchase purchase = it.next();

            if (purchase.quantity < mQuantity) { // 재고 수량 < mQuantity
                revenue += (mPrice - purchase.price) * purchase.quantity;

                product.totalQuantity -= purchase.quantity;

                mQuantity -= purchase.quantity;

                buyInfos.remove(purchase.bId);
                history.add(purchase.bId, purchase.price, purchase.initialQuantity, purchase.quantity);
                it.remove();

            } else { // 재고 수량 >= mQuantity
                revenue += (mPrice - purchase.price) * mQuantity;

                product.totalQuantity -= mQuantity;
                purchase.quantity -= mQuantity;

                history.add(purchase.bId, purchase.price, purchase.initialQuantity, mQuantity);

                mQuantity = 0;

                // 재고 수량 == mQuantity
                if (purchase.quantity == 0) {
                    buyInfos.remove(purchase.bId);
                    it.remove();
                }
            }

            if (product.purchases.isEmpty())
                products.remove(product.number);

            if (mQuantity == 0)
                break;
        }

        return revenue;
    }

    @Override
    public int refund(int sId) {
        SaleHistory saleHistory = saleInfos.get(sId);

        // sId로 판매한 내역이 없거나, 이미 환불해준 판매 sId 이면
        if (saleHistory == null)
            return -1;

        Product product = products.get(saleHistory.productNumber);

        if (product == null) {
            product = new Product(saleHistory.productNumber);
            products.put(saleHistory.productNumber, product);
        }

        for (SaleHistory.Sale sales : saleHistory.sales) {
            if (!buyInfos.containsKey(sales.bId)) { // 구매 정보에서 지워진 경우
                buyInfos.put(sales.bId, product.number);

                Product.Purchase purchase = new Product.Purchase(sales.bId, sales.price, sales.initialQuantity);
                purchase.quantity = sales.quantity;

                product.purchases.add(purchase);

                product.totalQuantity += sales.quantity;
            } else { // 구매 정보에 남아있는 경우
                Iterator<Product.Purchase> it = product.purchases.iterator();

                while (it.hasNext()) {
                    Product.Purchase purchase = it.next();

                    if (purchase.bId == sales.bId) {
                        purchase.quantity += sales.quantity;
                        break;
                    }
                }

                product.totalQuantity += sales.quantity;
            }
        }

        saleInfos.remove(sId);

        return saleHistory.totalQuantity;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    private void printProductStocks(int productNumber) {
        Product product = products.get(productNumber);
        if (product == null) {
            log("    product = null");
            return;
        }

        for (Product.Purchase purchase : product.purchases) {
            log("    %s", purchase);
        }
    }

    private void printSaleHistory(SaleHistory history) {
        for (SaleHistory.Sale sale : history.sales) {
            log("        %s", sale);
        }
    }
}