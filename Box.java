package game_X_O;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Box extends JFrame
{
	int margeX = 30;
	int nbX=30;
	int step = 20;
	int X[] = {margeX+30, margeX+30+(nbX/3)*step, margeX+30+(nbX/3)*2*step};
	int Y[] = {margeX+(nbX/3)*step-30, margeX+2*(nbX/3)*step-30, margeX+nbX*step-30};
	int Min[] = {margeX, margeX+(nbX/3)*step, margeX+2*(nbX/3)*step};
	int Max[] = {margeX+(nbX/3)*step, margeX+2*(nbX/3)*step, margeX+nbX*step};
	boolean end = true;
	
	JPanel MyPanel;
	DrawXorO DXO;
	
	public Box()
	{
		this.setTitle("X  vs  O");
		this.setSize(margeX*2+step*nbX*2 + 14,margeX*2 + step*nbX+30);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//-------------------------------------------------
		DXO = new DrawXorO(Min,Max);
		//-------------------------------------------------
		MyPanel = new JPanel() 
		{
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				draw_border(g);
				end = followTheGame(DXO.getTurn(),DXO.getWinner(),g);
			}
		};
		this.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				int Mx = e.getX();
				int My = e.getY();
				if(DXO.setxy(Mx, My) && end);
					repaint();
			}
		});
		this.setContentPane(MyPanel);
		this.setVisible(true);
	}
	//----------------decid who is the winner or who will play next----------------
	boolean followTheGame(int who, int winner,Graphics g)
	{
		if(winner==0) {
			if(who%2 == 0)
				TellWho(1,g);
			else
				TellWho(2,g);
			return true;
		}
		else {
			if(winner==1)
				congratulateWinner(1,g);
			else if(winner==2)
				congratulateWinner(2,g);
			else
				GameOver(g);
			return false;
		}
	}
	//-----------------------tell me who will play next-----------------------------
	void TellWho(int N, Graphics g)
	{
		String str=null;
		if(N == 2)
			str = "X";
		else
			str = "O";
		
		g.setColor(Color.blue);
		Font font = new Font("sans serif",Font.PLAIN,40);
		g.setFont(font);
		g.drawString(str+": it is your turn to play", margeX+nbX*step+8*step,margeX+10*step);
	}
	//-----------------------congratulate the winner--------------------------------
	void congratulateWinner(int N, Graphics g)
	{
		String str=null;
		if(N == 1)
			str = "X";
		else
			str = "O";
		
		g.setColor(Color.blue);
		Font font = new Font("sans serif",Font.PLAIN,40);
		g.setFont(font);
		g.drawString(str+": is the winner", margeX+nbX*step+8*step,margeX+10*step);
	}
	//------------------------game is over with no winner---------------------------
	void GameOver(Graphics g) 
	{
		g.setColor(Color.blue);
		Font font = new Font("sans serif",Font.PLAIN,40);
		g.setFont(font);
		g.drawString("No winner No loser", margeX+nbX*step+8*step,margeX+10*step);
	}
	//------------------------draw border of the game-------------------------------
	void draw_border(Graphics g)
	{
		Font font = new Font("sans serif",Font.PLAIN,200);
		g.setColor(Color.red);
		g.drawRect(margeX,margeX,nbX*step,nbX*step);
		g.setFont(font);
		
		//--------------we are drawing the box---------------
		for(int i=margeX; i<margeX + nbX*step ; i+=(nbX/3)*step)
		{
			g.drawLine(i, margeX,i, margeX+nbX*step);
		}
		for(int j=margeX; j<margeX + nbX*step ; j+=(nbX/3)*step)
		{
			g.drawLine(margeX, j,margeX+nbX*step,j);
		}
		int k=0;
		String T[] = DXO.getData();
		for(int i=0 ; i<3 ; i++) {
			for(int j=0 ; j<3 ; j++) {
				g.drawString(T[k],X[i], Y[j]);
				k++;
			}
		}
	}
	//------------------------------------------------------------------------------
	public static void main(String[] args) {
		System.out.println("hello");
		new Box();
	}
}
//-------------------------------class--------------------------
class DrawXorO
{
	private int turn; 
	private int winner;//1->X || 2->Y || 3 no winner
	private int Min[];
	private int Max[];
	private String  Data[] = new String[9];
	private ArrayList<String> PlayerX = new ArrayList<String>();
	private ArrayList<String> PlayerY = new ArrayList<String>();
	public DrawXorO(int Min[], int Max[])
	{
		this.Min = Min;
		this.Max = Max;
		this.turn = 0;
		this.winner = 0;
		for(int i=0 ; i<9 ; i++)
			Data[i] = " ";
	}
	public boolean setxy(int Mx,int My)
	{
		Integer k=1;
		for(int i=0 ; i<3 ; i++)
		{
			for(int j=0 ; j<3 ; j++)
			{
				if((Mx > Min[i] && Mx < Max[i]) && (My > Min[j] && My < Max[j]))
				{
					if(PlayerX.contains(k.toString()) || PlayerY.contains(k.toString()) || winner!=0)
						return false;
					turn++;
					if(turn%2 == 0) {
						this.Data[k-1] = "X";
						PlayerX.add(k.toString());
						if(IsWinner(PlayerX))
						{
							winner = 1;
						}
					}
					else {
						this.Data[k-1] = "O";
						PlayerY.add(k.toString());
						if(IsWinner(PlayerY))
						{
							winner = 2;
						}
						else
						{
							if(winner==0 && turn == 9)
								winner = 3;
						}
					}
					return true;
				}
				k++;	
			}
		}
		return false;
	}
	public String[] getData()
	{
		return this.Data;
	}
	public int getWinner()
	{
		return this.winner;
	}
	int getTurn()
	{
		return this.turn;
	}
	
	boolean swap(String[]str,String a,String b, String c)
	{
		int check = 0;
		for(String i : str)
		{
			if(i.equals(a) || i.equals(b)  || i.equals(c))
				check++;
		}
		if(check == 3)
			return true;
		else
			return false;
	}
	
	public boolean IsWinner(ArrayList<String> Player)
	{
		String str[] = new String[3];
		if(Player.size()>=3)
		{
			str[2] = Player.get(Player.size()-1);
			str[1] = Player.get(Player.size()-2);
			str[0] = Player.get(Player.size()-3);
			if(swap(str,"1","2","3"))
				return true;
			else if(swap(str,"4","5","6"))
				return true;
			else if(swap(str,"7","8","9"))
				return true;
			else if(swap(str,"1","4","7"))
				return true;
			else if(swap(str,"2","5","8"))
				return true;
			else if(swap(str,"3","6","9"))
				return true;
			else if(swap(str,"1","5","9"))
				return true;
			else if(swap(str,"3","5","7"))
				return true;
			else
				return false;
		}
		return false;
	}
	
}