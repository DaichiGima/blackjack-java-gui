
import java.util.ArrayList;
import java.util.List;


public class Player {
    private List<Card> hand;

    public Player() {
        hand = new ArrayList<>();
    }

    // カードを引く
    public void draw(Card card) {
        hand.add(card);
    }
    
 // 手札をリセットする
    public void resetHand() {
        hand.clear();
    }

    // 手札を取得する（正しい位置）
    public List<Card> getHand() {
        return hand;
    }

    // 手札の合計点を計算する
    public int getTotalValue() {
        int total = 0;
        int aceCount = 0;

        for (Card card : hand) {
            String rank = card.getRank();
            if (rank.equals("A")) {
                total += 11;
                aceCount++;
            } else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
                total += 10;
            } else {
                total += Integer.parseInt(rank);
            }
        }

        // エースの値を調整（必要なら11 → 1に）
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    // 手札を表示する
    public void showHand() {
        for (Card card : hand) {
            System.out.println(card);
        }
        System.out.println("合計点: " + getTotalValue());
    }

  
    }
    

