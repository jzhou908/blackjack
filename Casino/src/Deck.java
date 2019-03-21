import java.util.*;
public class Deck extends ArrayList<Card>{
	private List<Card> cards;
	private int size;
	public Deck(String[] ranks, String[] suits, int[] values) {
		 cards = new ArrayList<Card>();
		for (int j = 0; j < ranks.length; j++) {
			for (String suitString : suits) {
				cards.add(new Card(ranks[j], suitString, values[j]));
			}
		}
		size = cards.size();
		shuffle();
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public int size() {
		return size;
	}
	public void shuffle() {
		for (int k = cards.size() - 1; k > 0; k--) {
			int howMany = k + 1;
			int start = 0;
			int randPos = (int) (Math.random() * howMany) + start;
			Card temp = cards.get(k);
			cards.set(k, cards.get(randPos));
			cards.set(randPos, temp);
		}
		size = cards.size();
	}
	public Card deal() {
		if (isEmpty()) {
			return null;
		}
		size--;
		Card c = cards.get(size);
		return c;
	}
	public Card deal2() {
		if (isEmpty()) {
			return null;
		}
		size--;
		Card c = cards.get(size);
		System.out.println("The card you got was: " + c.rank());
		return c;
	}
}