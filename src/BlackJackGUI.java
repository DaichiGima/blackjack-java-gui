import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class BlackJackGUI {
	static Player player = new Player(); // プレイヤーの手札
	static Player dealer = new Player(); // ディーラー用の手札
	static Deck deck = new Deck(); // 山札を用意
    public static void main(String[] args) {
        // ウィンドウ（フレーム）を作成
        JFrame frame = new JFrame("ブラックジャック");
        frame.setSize(400, 570); // サイズ設定
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // 自由配置

        // ラベルを追加
        JLabel label = new JLabel("ようこそ！ブラックジャックへ");
        label.setBounds(100, 30, 250, 30); // x, y, width, height
        frame.add(label);

        // ボタンを追加
        JButton button = new JButton("カードを引く");
        button.setBounds(120, 100, 150, 40);
        frame.add(button);
        
     // 手札表示用のテキストエリア
        JTextArea handArea = new JTextArea();
        handArea.setBounds(50, 160, 300, 270);
        handArea.setEditable(false); // 編集不可
        frame.add(handArea);
        
     // スタンドボタン
        JButton standButton = new JButton("スタンド");
        standButton.setBounds(120, 450, 150, 40);
        frame.add(standButton);
        standButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // まずスコアを取得
                int playerScore = player.getTotalValue();

                if (playerScore > 21) {
                    handArea.append("あなたのターンは終了しました。\n");
                    handArea.append("あなたはバーストしました！ディーラーの勝ちです。\n");
                    return; // これで処理終了
                }

                // ここからディーラーの処理（21以下なら進む）
                handArea.append("あなたのターンは終了しました。\n");
                handArea.append("ディーラーのターン開始\n");

                while (dealer.getTotalValue() < 17) {
                    Card drawn = deck.drawCard();
                    if (drawn != null) {
                        dealer.draw(drawn);
                    }
                }

                handArea.append("ディーラーの手札:\n");
                for (Card card : dealer.getHand()) {
                    handArea.append(card.toString() + "\n");
                }
                handArea.append("ディーラーの合計点: " + dealer.getTotalValue() + "\n");

                int dealerScore = dealer.getTotalValue();

                if (dealerScore > 21) {
                    handArea.append("ディーラーがバースト！あなたの勝ち！\n");
                } else if (playerScore > dealerScore) {
                    handArea.append("あなたの勝ち！\n");
                } else if (playerScore < dealerScore) {
                    handArea.append("あなたの負け...\n");
                } else {
                    handArea.append("引き分け！\n");
                }
            }
        });
        //リセット
        JButton resetButton = new JButton("リセット");
        resetButton.setBounds(120, 490, 150, 40);
        frame.add(resetButton);
        
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // プレイヤーとディーラーの手札を初期化
                player.resetHand();
                dealer.resetHand();

                // デッキも新しく作り直してシャッフル
                deck = new Deck();

                // 表示を初期状態に戻す
                handArea.setText("ようこそ！ブラックジャックへ\n");

                // ボタンを再び使えるようにする
                button.setEnabled(true);
                standButton.setEnabled(true);
            }
        });

        // ボタンクリック時の動き
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Card drawn = deck.drawCard();
                if (drawn != null) {
                    player.draw(drawn); // 手札に追加
                    
                 

                 // 手札をまとめて表示
                    StringBuilder handText = new StringBuilder();
                    handText.append("あなたの手札:\n");
                    for (Card card : player.getHand()) {
                        handText.append(card.toString()).append("\n");
                    }

                    int total = player.getTotalValue();
                    handText.append("合計点: ").append(total);

                    // ⭐ バーストチェックを追加！
                    if (total > 21) {
                        handText.append("\nバーストしました！あなたの負けです。\n");
                        button.setEnabled(false);        // カードを引くボタンを無効化
                        standButton.setEnabled(false);   // スタンドボタンも無効化
                    }

                    handArea.setText(handText.toString());
                } else {
                    handArea.setText("山札がなくなりました！");
                }
            }
        });

        // ウィンドウを表示
        frame.setVisible(true);
    }
}