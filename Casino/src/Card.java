import java.util.*;
public class Card implements Comparable<Card>{
	private String suit;
	private String rank;
	private int pointValue;
	public Card(String cardRank, String cardSuit, int cardPointValue) {
		rank = cardRank;
		suit = cardSuit;
		pointValue = cardPointValue;
	}
	public String suit() {
		return suit;
   }
	public String rank() {
		return rank;
	}
	public int pointValue() {
		return pointValue;
	}
	@Override
	public int compareTo(Card card) {
		if(this.pointValue == card.pointValue()) {
			return 0;
		}
		else if(this.pointValue > card.pointValue()) {
			return 1;
		}
		else {
			return -1;
		}
	}
}