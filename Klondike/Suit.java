package Klondike;
public class Suit {
	private String color;
	private String name;
	public Suit (int s) {
		if(s == 1) {
			name = "spades";
			color = "black";
		}
		else if(s == 2) {
			name = "clubs";
			color = "black";
		}
		else if(s == 3) {
			name = "hearts";
			color = "red";
		}
		else {
			name = "diamonds";
			color = "red";
		}
	}
	public Suit() {
		name = "";
		color = "";
	}
	public void setSuit(int s) {
		if(s == 1) {
			name = "spades";
			color = "black";
		}
		else if(s == 2) {
			name = "clubs";
			color = "black";
		}
		else if(s == 3) {
			name = "hearts";
			color = "red";
		}
		else {
			name = "diamonds";
			color = "red";
		}
	}
	public String getName() {
		return name;
	}
	public String getColor() {
		return color;
	}
	public boolean equalsColor(Suit s) {
		if (this.getColor().equals(s.getColor())) {
			return true;
		}
		return false;
	}
	public boolean equals(Suit s) {
		if(name.equals(s.getName())) {
			return true;
		}
		return false;
	}
	
}