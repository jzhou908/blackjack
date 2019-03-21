import java.util.*;
import java.util.Scanner;
public class Blackjack{
	private ArrayList<Card> yourhand;
	private ArrayList<Card> dealerhand;
	private Scanner in = new Scanner(System.in);
	private Deck b;
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};
	private static final int[] POINT_VALUES =
		{11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	public void newgame(int yourbet) {
		b = new Deck(RANKS, SUITS, POINT_VALUES);
		b.shuffle();
		yourhand = new ArrayList<Card>();
		yourhand.add(b.deal());
		yourhand.add(b.deal());
		yourbet = 0;
		dealerhand = new ArrayList<Card>();
		dealerhand.add(b.deal());
		dealerhand.add(b.deal());
	}
	public void print() {
		System.out.println("Your hand is: ");
		for(Card card: yourhand) {
			System.out.println(card.rank());
		}
		System.out.println("The value of your hand is: " + value(yourhand));
		System.out.println("The dealer's first card is: ");
		System.out.println(dealerhand.get(0).rank());
	}
	public void printwin() {
		System.out.println("Your hand is: ");
		for(Card card: yourhand) {
			System.out.println(card.rank());
		}
		System.out.println("The value of your hand is: " + value(yourhand));
		System.out.println("The dealer's hand is: ");
		for(Card card: dealerhand) {
			System.out.println(card.rank());
		}
		System.out.println("The value of the dealer's hand is: " + value(dealerhand));
	}
	public ArrayList<Card> Sort(ArrayList<Card> a){
		int n = a.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (a.get(j).pointValue() > a.get(j+1).pointValue()) { 
                    Card temp = a.get(j); 
                    a.set(j, a.get(j+1)); 
                    a.set(j+1, temp);
                }
        return a;
	}
	public int value(ArrayList<Card> a) {
		int sum = 0;
		ArrayList<Card> sorted = Sort(a);
		for(int i = 0; i < a.size(); i++) {
			if(sorted.get(i).rank().equals("ace") && sum+11 > 21) {
				sum += 1;
			}
			else {
				sum += sorted.get(i).pointValue();
			}
		}
		return sum;
	}
	public void hitorstand(String answer) {
		if(!answer.equalsIgnoreCase("hit") && !answer.equalsIgnoreCase("stand")) {
			System.out.println("Incorrect input, please put hit or stand.");
			return;
		}
		while(answer.equalsIgnoreCase("hit")) {
			yourhand.add(b.deal2());
			System.out.println("Your hand's value is: " + value(yourhand));
			if(value(yourhand) == 21) {
				return;
			}
			if(value(yourhand) > 21) {
				return;
			}
			if(value(yourhand) == 21) {
				return;
			}
			System.out.println("Do you want to hit or stand now?");
			answer = in.nextLine();
			if(!answer.equalsIgnoreCase("hit") && !answer.equalsIgnoreCase("stand")) {
				System.out.println("Incorrect input, please put hit or stand.");
				return;
			}
		}
	}
	public boolean win() {
		if(value(yourhand) > 21) {
			return false;
		}
		while(value(dealerhand) < 17) {
			dealerhand.add(b.deal());
		}
		if(value(yourhand) == 21 && yourhand.size() == 2) {
			if(value(dealerhand) == 21 && dealerhand.size() == 2) {
				System.out.println("TIE");
				return false;
			}
			else {
				System.out.println("You WON!");
				return true;
			}
		}
		else if(value(yourhand) == 21){
			if(value(dealerhand) == 21) {
				System.out.println("TIE");
				return false;
			}
			else {
				System.out.println("You WON!");
				return true;
			}
		}
		else if(value(yourhand) < 21 && value(dealerhand) > 21) {
			System.out.println("You WON!");
			return true;
		}
		else if(value(yourhand) > value(dealerhand) && value(yourhand) < 21) {
			System.out.println("You WON!");
			return true;
		}
		else if(value(yourhand) == value(dealerhand) && value(yourhand) < 21) {
			System.out.println("You TIED.");
			return false;
		}
		else {
			System.out.println("You LOST! Sorry.");
			return false;
		}
	}
	public void profit(int bet) {
		if(win() == true) {
			System.out.println("You made " + bet + " dollars.");
		}
		else if(value(yourhand) == value(dealerhand) && value(yourhand) <= 21) {
			System.out.println("You tied. You gained and lost nothing.");
		}
		else {
			System.out.println("You lost " + bet + " dollars.");
		}
	}
}