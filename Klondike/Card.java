package Klondike;
public class Card {
	private int value;
	private Suit suit;
	private boolean revealed;
	public Card (int v, Suit s, boolean r) {
		value = v;
		suit = s;
		revealed = r;
	}
	public Card() {
		value = 0;
		suit = new Suit();
		revealed = false;
	}
	public int getValue() {
		return value;
	}
	public Suit getSuit() {
		return suit;
	}
	public boolean isRevealed() {
		return revealed;
	}
	public void reveal() {
		revealed = true;
	}
	public void setValue(int v) {
		value = v;
	}
	public void setSuit(Suit s) {
		suit = s;
	}
	public String toString() {
		if(revealed) {
			return value + " " + suit.getName();
		}
		return "hidden";
	}
	public boolean equals(Card c) {
		if(value == c.getValue() && suit.equals(c.getSuit()) && (revealed == c.isRevealed())){
			return true;
		}
		return false;
	}
}