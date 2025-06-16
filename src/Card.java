public class Card {
    private String suit; // スート（例：ハート、スペード）
    private String rank; // ランク（例：A, 2〜10, J, Q, K）

    // コンストラクタ：スートとランクを受け取って初期化
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // スートを取得するメソッド
    public String getSuit() {
        return suit;
    }

    // ランクを取得するメソッド
    public String getRank() {
        return rank;
    }

    // カードの点数を取得するメソッド
    public int getValue() {
        switch (rank) {
            case "A":
                return 11; // エースは11点
            case "K":
            case "Q":
            case "J":
                return 10; // 絵札はすべて10点
            default:
                return Integer.parseInt(rank); // 数字はそのまま点数
        }
    }

    // カード情報を文字として表示するためのメソッド
    @Override
    public String toString() {
        return suit + "の" + rank;
    }
}