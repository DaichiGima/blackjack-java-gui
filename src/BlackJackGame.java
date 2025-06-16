import java.util.Scanner;

public class BlackJackGame {
    private Deck deck;
    private Player player;
    private Player dealer;
    private Scanner scanner;

    public BlackJackGame() {
        deck = new Deck();
        player = new Player();
        dealer = new Player();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("ブラックジャックを始めます！");

        // プレイヤーとディーラーに2枚ずつ配る
        player.draw(deck.drawCard());
        player.draw(deck.drawCard());
        dealer.draw(deck.drawCard());
        dealer.draw(deck.drawCard());

        // プレイヤーの手札を表示
        System.out.println("あなたの手札：");
        player.showHand();

        // プレイヤーのターン
        while (true) {
            System.out.print("カードを引きますか？ (h=ヒット / s=スタンド): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("h")) {
                player.draw(deck.drawCard());
                player.showHand();
                if (player.getTotalValue() > 21) {
                    System.out.println("バーストしました！あなたの負けです。");
                    return;
                }
            } else if (input.equalsIgnoreCase("s")) {
                break;
            } else {
                System.out.println("無効な入力です。");
            }
        }

        // ディーラーのターン
        System.out.println("\nディーラーの手札：");
        dealer.showHand();
        while (dealer.getTotalValue() < 17) {
            System.out.println("ディーラーがカードを引きます...");
            dealer.draw(deck.drawCard());
            dealer.showHand();
        }

        int playerTotal = player.getTotalValue();
        int dealerTotal = dealer.getTotalValue();

        System.out.println("\n結果発表！");
        System.out.println("あなたの合計: " + playerTotal);
        System.out.println("ディーラーの合計: " + dealerTotal);

        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("あなたの勝ちです！");
        } else if (playerTotal == dealerTotal) {
            System.out.println("引き分けです！");
        } else {
            System.out.println("あなたの負けです。");
        }
    }
}
