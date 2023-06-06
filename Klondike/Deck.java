package Klondike;

import java.util.ArrayList;

public class Deck {
	//instance variables
	private ArrayList<Card> deck;
	private Card topCard;
	//constructor
	public Deck(ArrayList<Card> d) {
		deck = d;
		topCard = deck.get(deck.size()-1);
	}
	public Deck() {
		deck = new ArrayList<Card>();
	}
	//make ArrayList methods so that accessing deck could be done outside of the class
	public Card get(int i) {
		return deck.get(i);
	}
	public Card set(int i, Card c) {
		return deck.set(i, c);
	}
	public int size() {
		return deck.size();
	}
	public boolean add(Card c) {
		return deck.add(c);
	}
	public void add(int i, Card c) {
		deck.add(i,c);
	}
	public int indexOf(Card c) {
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).equals(c)) {
				return i;
			}
		}
		return -1;
	}
	public Card remove(int i) {
		return deck.remove(i);
	}
	public String toString() { //change the toString method so that it only prints the cards that are revealed
		if(this.size() == 0) {
			return "[empty]";
		}
		ArrayList<String> s = new ArrayList<String>();
		for(int i = 0; i < deck.size(); i++) {
			if(deck.get(i).isRevealed()) {
				s.add(deck.get(i).toString());
			}
			else {
				s.add("hidden");
			}
		}
		return s.toString();
	}
	//getter and setter methods for topCard
	public Card getTopCard() {
		return topCard;
	}
	public void updateTopCard() {
		if(this.size() == 0) {
			topCard = new Card();
		}
		else{
			topCard = deck.get(deck.size()-1);
		}
	}
	//shuffle method
	public void revealCard() {
		topCard.reveal();
	}
	public void shuffle() {
		Card temp = new Card();
		for(int i = 0; i < (int)(Math.random()*6) + 20; i++) {//swap the cards in the deck for a random number of times
			int rand1 = (int) (Math.random()*deck.size());
			int rand2 = (int) (Math.random()*deck.size());
			temp = deck.get(rand1);
			deck.set(rand1, deck.get(rand2));
			deck.set(rand2,  temp);
			this.updateTopCard();
		}
	}
	//addCards method
	//NOTE: AFTER CALLING THIS METHOD PARAMATER Deck d MUST CALL THE updateTopCard() METHOD TO KEEP THE DECK UP TO DATE
	public Deck addCards(Deck d, Card c) {//add cards to deck from c to d.size()-1, remove the cards that were added from d, and return d
		int index = d.indexOf(c);
		for(int i = index; i < d.size(); i+= 0) {//remove cards from d and add them to deck
			deck.add(d.get(i));
			d.remove(i);
		}
		this.updateTopCard();
		return d;
	}
}