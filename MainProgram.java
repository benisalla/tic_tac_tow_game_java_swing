package game_X_O;

import java.awt.Color;

public class MainProgram {
	
	public static void main(String[] args) {
		rect a = new rect();
		System.out.println(a);
	}
}

class rect
{
	private float W;
	private float H;
	Color c;
	
	public rect(float W,float H)
	{
		this.W = W;
		this.H = H;
	}
	public rect(rect a)
	{
		this.W = a.W;
		this.H = a.H;
	}
	public rect()
	{
		this(0,0);
	}
	public float getW() {
		return W;
	}


	public void setW(float w) {
		W = w;
	}


	public float getH() {
		return H;
	}


	public void setH(float h) {
		H = h;
	}


	@Override
	public String toString()
	{
		return "rect[ "+this.W+" X "+this.H+" ]";
	}
}