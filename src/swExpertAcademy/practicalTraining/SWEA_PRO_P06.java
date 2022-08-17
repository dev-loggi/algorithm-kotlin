package swExpertAcademy.practicalTraining;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
 * */
public class SWEA_PRO_P06 {

    interface UserSolution {
        /**
         * 각 테스트 케이스의 처음에 호출된다.<br/>
         * 재고가 없는 상태가 된다.
         * */
        void init();

        /**
         * mProduct 상품을 mPrice 가격으로 mQuantity 개 구매한다. 구매 ID는 bId이다.<br/>
         * 구매 후에, 가게가 보유 중인 mProduct 상품의 총 개수를 반환한다.<br/>
         *
         * @param bId 구매 ID ( 1 ≤ bId ≤ 1,000,000,000 )
         * @param mProduct 상품 번호 ( 1 ≤ mProduct ≤ 1,000,000,000 )
         * @param mPrice 구매 가격 ( 1,000 ≤ mPrice ≤ 300,000 )
         * @param mQuantity 구매 수량 ( 10 ≤ mQuantity ≤ 100 )
         *
         * @return mProduct 상품의 재고 수량을 반환한다.
         * */
        int buy(int bId, int mProduct, int mPrice, int mQuantity);

        /**
         * 구매 ID가 bId인 구매를 취소한다.
         * bId로 구매했던 상품 수량이 모두 가게에 남아 있을 경우에만, 취소가 가능하다. 한 개라도 부족하다면 취소에 실패하고 -1을 반환한다.<br/>
         * bId로 구매한 상품이 판매 된 적이 있더라도, 환불을 통해 다시 모두 재고가 되었다면 취소가 가능함을 유의하라.<br/>
         * bId로 구매한 내역이 없거나, 이미 취소한 구매 ID라면, 취소에 실패하고 -1을 반환한다.<br/>
         *
         * 취소가 가능하다면, 재고에서 bId로 구매했던 상품을 삭제하고, 가게에 남아 있는 동일 상품의 개수를 반환한다.<br/>
         *
         * @param bId 구매 ID ( 1 ≤ bId ≤ 1,000,000,000 )
         *
         * @return 취소에 실패할 경우, -1을 반환한다.
         * 취소에 성공할 경우, 취소된 상품과 동일한 상품이 가게에 얼마나 남아있는지 그 개수를 반환한다.
         * */
        int cancel(int bId);

        /**
         * mProduct 상품을 mPrice 가격으로 mQuantity 개 판매한다. 판매 ID는 sId이다.<br/>
         * 판매 ID와 구매 ID는 서로 독립적인 ID이기 때문에, 판매 ID 값과 구매 ID 값이 서로 동일한 경우도 있다.<br/>
         * mProduct 상품의 재고 수량이 mQuantity 보다 작다면, 판매에 실패하고 -1을 반환한다.<br/>
         *
         * 판매가 가능하다면, 가장 싸게 구매한 상품부터 판매한다.<br/>
         * 가격이 동일한 경우에는 구매 ID 값이 작은 상품부터 판매한다.<br/>
         * 판매 후에, 총 판매 수익을 반환한다. 개당 판매 수익은 판매 가격에서 구매 가격을 뺀 값이다.<br/>
         *
         * @param sId 판매 ID ( 1 ≤ sId ≤ 1,000,000,000 )
         * @param mProduct 상품 번호 ( 1 ≤ mProduct ≤ 1,000,000,000 )
         * @param mPrice 판매 가격 ( 2,000 ≤ mPrice ≤ 300,000 )
         * @param mQuantity 판매 수량 ( 1 ≤ mQuantity ≤ 500 )
         *
         * @return 판매에 실패할 경우, -1을 반환한다.
         * 판매에 성공할 경우, sId 판매로 발생한 총 수익을 반환한다.
         * */
        int sell(int sId, int mProduct, int mPrice, int mQuantity);

        /**
         * sId로 판매한 상품에 대해 환불해 준다.<br/>
         * 환불해 준 상품의 총 개수를 반환한다.<br/>
         * 환불해 준 상품은 모두 재고로 쌓인다.<br/>
         *
         * sId로 판매한 내역이 없거나, 이미 환불해준 판매 ID라면, 환불에 실패하고 -1을 반환한다.<br/>
         *
         * @param sId 판매 ID ( 1 ≤ sId ≤ 1,000,000,000 )
         *
         * @return 환불에 실패할 경우, -1을 반환한다.
         * 환불에 성공할 경우, 환불해 준 상품의 총 개수를 반환한다.
         * */
        int refund(int sId);
    }

    private final static int CMD_INIT = 1;
    private final static int CMD_BUY = 2;
    private final static int CMD_CANCEL = 3;
    private final static int CMD_SELL = 4;
    private final static int CMD_REFUND = 5;

    private final static SWEA_PRO_P06_UserSolution userSolution = new SWEA_PRO_P06_UserSolution();

    private static boolean run(BufferedReader br) throws Exception {
        int q = Integer.parseInt(br.readLine());

        int id, product, price, quantity;
        int cmd, ans, ret = 0;
        boolean okay = false;

        for (int i = 0; i < q; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case CMD_INIT:
                    userSolution.init();
                    okay = true;
                    break;
                case CMD_BUY:
                    id = Integer.parseInt(st.nextToken());
                    product = Integer.parseInt(st.nextToken());
                    price = Integer.parseInt(st.nextToken());
                    quantity = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = userSolution.buy(id, product, price, quantity);
                    if (ret != ans)
                        okay = false;
//                    log("[%c] CMD_BUY: ans=%d, ret=%d\n", ans==ret?'O':'X', ans, ret);
                    break;
                case CMD_CANCEL:
                    id = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = userSolution.cancel(id);
                    if (ret != ans)
                        okay = false;
//                    log("[%c] CMD_CANCEL: ans=%d, ret=%d\n", ans==ret?'O':'X', ans, ret);
                    break;
                case CMD_SELL:
                    id = Integer.parseInt(st.nextToken());
                    product = Integer.parseInt(st.nextToken());
                    price = Integer.parseInt(st.nextToken());
                    quantity = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = userSolution.sell(id, product, price, quantity);
                    if (ret != ans)
                        okay = false;
//                    log("[%c] CMD_SELL: ans=%d, ret=%d\n", ans==ret?'O':'X', ans, ret);
                    break;
                case CMD_REFUND:
                    id = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = userSolution.refund(id);
                    if (ret != ans)
                        okay = false;
//                    log("[%c] CMD_REFUND: ans=%d, ret=%d\n", ans==ret?'O':'X', ans, ret);
                    break;
                default:
                    okay = false;
                    break;
            }
        }
        return okay;
    }

    private static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream(ProInputFile.number(6)));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}
