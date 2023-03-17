package jp.co.trainocate.camp.samples.window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jp.co.trainocate.camp.samples.enums.Hands;
import jp.co.trainocate.camp.samples.enums.Status;

public class MainWindow {
	//ゲームを表示するフレーム
  private final JFrame frame;
  //メッセージを表示するフレーム
  private final JLabel messageLabel;
  //グーのボタン
  private final JButton rockButton;
  //チョキのボタン
  private final JButton scissorsButton;
  //パーのボタン
  private final JButton paperButton;
  
  //プレイ状況のステータス
  private Status playState;
  //相手が出した手
  private Hands opponentHand;
  
  //コンストラクタ
  public MainWindow() {
	  //画面生成
	  this.frame = new JFrame("じゃんけんゲーム！");
	  this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  //画面サイズを指定
	  this.frame.setBounds(200, 200, 600, 400);
	  
	  var pane = this.frame.getContentPane();
	  
	  //このcanvasに対して、ボタンやラベルを配置していく
	  var canvas = new JPanel();
	  //自由レイアウトに変更する
	  canvas.setLayout(null);
	  
	  //ラベル
	  this.messageLabel = new JLabel("じゃーんけーん・・・");
	  this.messageLabel.setBounds(20, 20, 400, 30);
	  canvas.add(this.messageLabel);
	  
	  //ボタンを作成する
	  
	  //グー
	  this.rockButton = new JButton(Hands.ROCK.getDisplay());
	  this.rockButton.setBounds(100, 100, 100, 40);
	  this.rockButton.addActionListener((e) -> this.selectHand(Hands.ROCK));
	  canvas.add(this.rockButton);
	  //チョキ
	  this.scissorsButton = new JButton(Hands.SCISSORS.getDisplay());
	  this.scissorsButton.setBounds(250, 100, 100, 40);
	  this.scissorsButton.addActionListener((e) -> this.selectHand(Hands.SCISSORS));
	  canvas.add(this.scissorsButton);
	  //パー
	  this.paperButton = new JButton(Hands.PAPER.getDisplay());
	  this.paperButton.setBounds(400, 100, 100, 40);
	  this.paperButton.addActionListener((e) -> this.selectHand(Hands.PAPER));
	  canvas.add(this.paperButton);
	  
	  //画面にCanvasを追加
	  pane.add(canvas);
  }
  
  //画面表示
  public void show() {
	  this.init();
	  this.frame.setVisible(true);
  }
  
  //ゲームの初期化
  public void init() {
	  //相手の手をリセットし、待ち状態にする
	  this.opponentHand = Hands.getRandomHand();
	  this.playState = Status.Wait;
  }
  
  //自分の手を選んだときの処理
  public void selectHand(Hands selected) {
	  //入力待ちでなければ処理はしない
	  if (this.playState != Status.Wait) {
		  return;
	  }
	  
	  //勝ち負けの判定
	  switch ((selected.getNumber() - opponentHand.getNumber() + 3) % 3) {
	    case 0:
		  //引き分けなので継続
		  this.messageLabel.setText("あーいこーで・・・");
		  //手を出し直す
		  this.init();
		  break;
	    case 1:
	      //負け
	      this.messageLabel.setText(String.format("相手が出したのは「%s」なのであなたの負けです。", this.opponentHand.getDisplay()));
	      //ゲーム終了
	      this.playState = Status.Done;
	      break;
	    case 2:
	      this.messageLabel.setText(String.format("相手が出したのは「%s」なのであなたの勝ちです。", this.opponentHand.getDisplay()));
	      //ゲーム終了
	      this.playState = Status.Done;
	      break;
	  }
  }
}
