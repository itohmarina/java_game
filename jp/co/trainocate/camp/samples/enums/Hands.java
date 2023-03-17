package jp.co.trainocate.camp.samples.enums;

import java.util.Random;

public enum Hands {
	ROCK("グー", 0),
	SCISSORS("チョキ", 1),
	PAPER("パー", 2);
	
	//表示文字
	private final String display;
	//内部番号
	private final int number;
    
	//コンストラクタ
	Hands(String display, int number){
		this.display = display;
		this.number = number;
	}
	
	
    //ランダムな手を生成
	public static Hands getRandomHand() {
		Random rand = new Random();
		//nextIntは0から引数に指定した値未満の整数を返す
	    return Hands.values()[rand.nextInt(3)];
	}
	
	//表示名を取得
	
	public String getDisplay() {
		return this.display;
	}
	
	//番号を取得
	
	public int getNumber() {
		return this.number;
	}
}
