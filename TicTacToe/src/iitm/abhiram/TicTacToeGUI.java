package iitm.abhiram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class TicTacToeGUI extends JFrame implements MouseListener
{
	public static void main(String[] args) throws Exception
	{
		TicTacToeGUI Abhiram3T = new TicTacToeGUI();
	}
	/* Setting up the container for the GUI */
	private Container c;
	static int N = 3;
	final int ratio = 460/N;
	private JPanel panelMain = new JPanel(new GridLayout(N, N));
	private JPanel[][] cells = new JPanel[N][N];
	public String[][] strBoard = new String[N][N]; 
	
	private Color winColor = Color.GREEN;
	
	/* Setting up the ImageIcons */
	private ImageIcon zero = new ImageIcon(System.getProperty("user.dir")+"/pieces/O.png");
    private ImageIcon ex = new ImageIcon(System.getProperty("user.dir")+"/pieces/X.png");
    private ImageIcon bgd = new ImageIcon(System.getProperty("user.dir")+"/pieces/bgd.png");
    
    /* Setting up the game values */
    private char startMove = 'O';
    private char move = 'O';
    private boolean playerStart = true;
    
    /* Artificial Intelligence : 0 - No AI, 1 - Easy, 2 - Hard */
    private int AI = 1;
    Random rand = new Random();
	
	/* The Constructor */
	public TicTacToeGUI() throws Exception
	{
		c = getContentPane();
		setBounds(400, 100, 470, 525);
        setBackground(new Color(204, 204, 204));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Abhiram's TicTacToe");
        setResizable(true);          
        c.setLayout(null); 
        panelMain.setBounds(3, 3, 460, 460);
        panelMain.setBackground(new Color(255, 255, 255));           
        c.add(panelMain);
        
        /* Initializing the actionlisteners to be used for the menus */
        newGame ng = new newGame(); 
        exit ex = new exit();
        about ab = new about();
        setN setn = new setN();
        moveStart mover = new moveStart();
        AIStart ais = new AIStart();
        ArtifIntel ai1 = new ArtifIntel(1);
        ArtifIntel ai2 = new ArtifIntel(2);
        ArtifIntel ai0 = new ArtifIntel(0);
        
        /* Create a new menubar */
        JMenuBar menuBar = new JMenuBar();
        /* The File Menu */
        JMenu menu = new JMenu("File");
    
        JMenuItem menuItem;
        JMenu newGame = new JMenu("New Game");
        JMenu singlePlayer = new JMenu("Single Player");
        JMenuItem twoPlayer = new JMenuItem("Two Player");
        JMenuItem easy = new JMenuItem("easy");
        JMenuItem hard = new JMenuItem("hard");
        singlePlayer.add(easy);
        singlePlayer.add(hard);
        newGame.add(singlePlayer);
        newGame.add(twoPlayer);
        twoPlayer.addActionListener(ng);
        twoPlayer.addActionListener(ai0);
        easy.addActionListener(ng);
        hard.addActionListener(ng);
        easy.addActionListener(ai1);
        hard.addActionListener(ai2);
        newGame.getAccessibleContext().setAccessibleDescription("This starts a new game");
        menu.add(newGame);        
        
        menuItem = new JMenuItem("About");
        menuItem.addActionListener(ab);
        menuItem.getAccessibleContext().setAccessibleDescription("About the Game");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(ex);
        menuItem.getAccessibleContext().setAccessibleDescription("This Quits the Game");
        menu.add(menuItem);
        
        menuBar.add(menu);
        /* The File Menu Ends */
        
        /* The Options Menu */
        JMenu optionsMenu = new JMenu("Options");
        JCheckBox radioButton;
        
        menuItem = new JMenuItem("Set N x N");
        menuItem.addActionListener(setn);
        optionsMenu.add(menuItem);
 
        radioButton = new JCheckBox("Start with X from the Next game");
        radioButton.addActionListener(mover);
        optionsMenu.add(radioButton);
        
        radioButton = new JCheckBox("AI starts first");
        radioButton.addActionListener(ais);
        optionsMenu.add(radioButton);
        
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);
        
        /* ReInitializing the board */
        for(int i = 0; i < N; i++ ) for(int j = 0; j < N; j++ )
        {
        	strBoard[i][j] = " ";
        }
        
        drawBoard(); 
        if(playerStart == false && AI == 1)
        {
        	AIEasyMove();
        }
        arrangePieces();
        show();
	}
	private void AIEasyMove() throws Exception 
	{
		int aiX;
    	int aiY;
    	do
    	{
    		aiX = rand.nextInt(N);
        	aiY = rand.nextInt(N);
    	}
    	while(strBoard[aiY][aiX] != " ");
    	if(move == 'X') 
    	{
    		this.strBoard[aiY][aiX] = "X";
    		move = 'O';
    	}
    	if(move == 'O') 
    	{
    		if(strBoard[aiY][aiX] == " ") 
    		{
    			this.strBoard[aiY][aiX] = "O";
    			move = 'X';
    		}
    	}
		
	}
	private void drawBoard() throws FileNotFoundException
    { 
    	for (int y = 0; y < N; y++)
    		for (int x = 0; x < N; x++)
    		{
            	cells[y][x] = new JPanel(new BorderLayout());
            	cells[y][x].addMouseListener(this); 
            	panelMain.add(cells[y][x]);
            	cells[y][x].setBackground(Color.white);
            	cells[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
            	
    		}
    }
	private void arrangePieces() throws Exception
    {     
    	for(int y = 0; y < N; y++)       
        	for(int x = 0; x < N; x++) 
        	{                
        		this.cells[y][x].removeAll();
        		/* Adding the image to the cells */
                this.cells[y][x].add(this.getPieceObject(strBoard[y][x]), BorderLayout.CENTER);         	
                this.cells[y][x].validate();
        	}          
    }
	/* Function to get the JLabel for a particular imageicon */
	private JLabel getPieceObject(String strPieceName)
    {
          JLabel lblTemp;
          if(strPieceName.equals("O"))
        	  lblTemp = new JLabel(this.zero);
          else if(strPieceName.equals("X"))
        	  lblTemp = new JLabel(this.ex);
          else
        	  lblTemp = new JLabel();
          return lblTemp;
    }	
	@Override
	/* The onMouseClick Listener */
	public void mouseClicked(MouseEvent m)
	{
		Object source = m.getSource();
		JPanel pnlTemp = (JPanel) source;	
		/* Evaluating the coordinates of the mouseclick */
		int intX = (pnlTemp.getX()/ratio);             
        int intY = (pnlTemp.getY()/ratio);
        boolean valid = isValidMove(intY, intX);
        
        /* Player's Turn */
		if( move == 'O')
		{   
	        if(this.strBoard[intY][intX].equals(" "))
	        {
	        	this.strBoard[intY][intX] = "O";
	            move = 'X';
	        }    
		}
		if( move == 'X' )
		{
			if(this.strBoard[intY][intX].equals(" "))
            {
            	this.strBoard[intY][intX] = "X";
            	move = 'O';
            }
		}
		try 
		{
			arrangePieces();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(isGameOver())
		{
			if(move == 'O') 
			{
				JOptionPane.showMessageDialog(null, "X wins! Put treat!");
			}
			else if(move == 'X') 
			{
				JOptionPane.showMessageDialog(null, "O wins! Put treat!");
			}
			c.removeAll();
			return;
		}
		if(isDraw())
		{
			if(!isGameOver()) JOptionPane.showMessageDialog(null, "The Game ended in a Draw");
			c.removeAll();
			return;
		}
		
	
        /* The easy artificial intelligence */
		if(AI == 1 && !isGameOver() && !isDraw() && valid )
        {
			try 
			{
				AIEasyMove();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
        	
        }
		/* The perfect AI */
		if(AI == 2 && !isGameOver() && !isDraw() )
        {
        	
        	
        }
		try 
		{
			arrangePieces();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}	
		

        if(isGameOver())
		{
			if(move == 'O') 
			{
				JOptionPane.showMessageDialog(null, "X wins! Put treat!");
			}
			else if(move == 'X') 
			{
				JOptionPane.showMessageDialog(null, "O wins! Put treat!");
			}
			/* When the AI has won, we must uncolor the player's position*/
			cells[intY][intX].setBackground(Color.white);
			cells[intY][intX].validate();
			c.removeAll();
		}
		if(isDraw())
		{
			if(!isGameOver()) JOptionPane.showMessageDialog(null, "The Game ended in a Draw");
			
			/* When the AI has won, we must uncolor the player's position*/
			cells[intY][intX].setBackground(Color.white);
			cells[intY][intX].validate();
			c.removeAll();
		}
		
		
	}
	private boolean isValidMove(int x, int y)
	{
		if(strBoard[x][y] ==" ") return true;
		else return false;
	}
	private boolean isDraw()
	{
		for( int j = 0; j < N; j++ )
		{
			for(int i = 0; i < N; i++)
			{
				if(strBoard[i][j] ==" ") return false;
			}
		}
		return true;
	}
	private boolean isGameOver()
	{

		for( int i = 0; i < N; i++ )
		{
			for(int j = 0; j < N-1; j++)
			{
				if(strBoard[i][j] != strBoard[i][j+1]) break;
				if(j==N-2 && strBoard[i][j]!=" ") 
				{
					paintRow(i);	
					return true;
				}
			}
		}
		for( int j = 0; j < N; j++ )
		{
			for(int i = 0; i < N-1; i++)
			{
				if(strBoard[i][j] != strBoard[i+1][j]) break;
				if(i==N-2 && strBoard[i][j]!=" ") 
				{
					paintCol(j);
					return true;
				}
			}
		}
		for( int i = 0; i < N-1; i++ )
		{
			if(strBoard[i][i] != strBoard[i+1][i+1]) break;
			if(i==N-2 && strBoard[i][i]!=" ") 
			{
				paintDiag1();
				return true;
			}
		}
		for( int i = 0; i < N-1; i++ )
		{
			if(strBoard[i][N-1-i] != strBoard[i+1][N-i-2]) break;
			if(i==N-2 && strBoard[i][N-1-i]!=" ") 
			{
				paintDiag2();
				return true;
			}
		}
		
		return false;
	}
	private void paintRow(int i) 
	{
		for(int j = 0; j < N; j++ )
		{
			cells[i][j].setBackground(winColor);
		}	
	}
	private void paintCol(int j) 
	{
		for(int i = 0; i < N; i++ )
		{
			cells[i][j].setBackground(winColor);
		}	
	}
	private void paintDiag1() 
	{
		for(int i = 0; i < N; i++ )
		{
			cells[i][i].setBackground(winColor);
		}	
	}
	private void paintDiag2() 
	{
		for(int i = 0; i < N; i++ )
		{
			cells[i][N-1-i].setBackground(winColor);
		}	
	}
	/* ColorPlay using MouseEntered() & MouseExited() */
	@Override
	public void mouseEntered(MouseEvent m) 
	{	
		Cursor cursor = new Cursor(java.awt.Cursor.CROSSHAIR_CURSOR);
		c.setCursor(cursor);
		Object source = m.getSource();
		JPanel pnlTemp = (JPanel) source;		
		int intX = (pnlTemp.getX()/ratio);             
        int intY = (pnlTemp.getY()/ratio);
        if(move == 'O') cells[intY][intX].setBackground(Color.cyan);
        else cells[intY][intX].setBackground(Color.yellow);
	}

	@Override
	public void mouseExited(MouseEvent m) 
	{
		Object source = m.getSource();
		JPanel pnlTemp = (JPanel) source;		
		int intX = (pnlTemp.getX()/ratio);             
        int intY = (pnlTemp.getY()/ratio);
        cells[intY][intX].setBackground(Color.white);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	/* The newGame Actionlistener to begin a new Game */
	private class newGame implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent a) 
		{
			c.removeAll();
			c = getContentPane();
			setBounds(400, 100, 470, 525);
	        setBackground(new Color(204, 204, 204));
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setTitle("Abhiram's TicTacToe");
	        setResizable(true);          
	        c.setLayout(null); 
	        panelMain.setBounds(3, 3, 460, 460);
	        panelMain.setBackground(new Color(255, 255, 255));           
	        c.add(panelMain);
	        			
	        for(int i = 0; i < N; i++ ) for(int j = 0; j < N; j++ )
	        {
	        	strBoard[i][j] = " ";
	        }   
	        move = startMove;  
	        if(playerStart == false && AI == 1)
	        {
	        	try 
	        	{
					AIEasyMove();
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
	        }
	        try 
	        {
				arrangePieces();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
	        for(int i = 0; i < N; i++ ) for(int j = 0; j < N; j++ )
	        {
	        	cells[i][j].setBackground(Color.white);
	        } 
	             
		}
		
	}
	private class exit implements ActionListener
    {	
		public void actionPerformed(ActionEvent e) 
		{	
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?") == 0) System.exit(0);
		}
    }
	class about extends JFrame  implements ActionListener
    {
  		Container d= new Container();
	  	public about()
	  	{
		  	d = getContentPane();
		  	setBounds(100, 100, 470, 300);
			setBackground(new Color(204, 204, 204));
		  	setDefaultCloseOperation(HIDE_ON_CLOSE);
			setTitle("About TicTacToe");
		 	setResizable(false);          
		  	d.setLayout(null);
		  	panelMain2.setBounds(3, 3, 460, 460);
		  	panelMain2.setBackground(new Color(255, 255, 255));  
		  	d.add(panelMain2);
	  	}
	  	private JPanel panelMain2 = new JPanel(new BorderLayout());
	  	private ImageIcon credits = new ImageIcon(System.getProperty("user.dir")+"/pieces/StrikerChess.png");  	
	  	JLabel creditsLabel = new JLabel(credits);  	      	
	  	{
	  		panelMain2.setBackground(Color.WHITE);
	  		panelMain2.removeAll();
	  		panelMain2.add(creditsLabel, BorderLayout.NORTH);
	  		panelMain2.validate();
	  	}
			@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) 
		{	
			show();			
		}
	  	  
    }
	class moveStart implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(startMove == 'X') startMove = 'O';
			else startMove = 'X';			
		}		
	}
	class setN implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String nstring = JOptionPane.showInputDialog("Enter the value of N");
			int n = Integer.parseInt(nstring);
			if(n <= 2)
			{
				JOptionPane.showMessageDialog(null, "You cannot play TicTacToe on a 2x2 or smaller!");
			}
			if(n > 10)
			{
				if(JOptionPane.showConfirmDialog(null, "WARNING : There may be too many rows and colums. Do you wish to continue?") == 0)
				{
					removeAll();
					show(false);
					try
					{
						N = n;
						TicTacToeGUI four = new TicTacToeGUI();
					} catch (Exception e1) 
					{
						e1.printStackTrace();
					}	
				}
			}
			else if (n >= 3)
			{
				removeAll();
				show(false);
				try
				{
					N = n;
					TicTacToeGUI four = new TicTacToeGUI();
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}	
		}
	}
	class ArtifIntel implements ActionListener
	{
		int difficulty;
		ArtifIntel(int n)
		{
			this.difficulty = n;
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			AI = this.difficulty;
		}
	}
	class AIStart implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent m) 
		{
			if(playerStart == true ) playerStart = false;
			else playerStart = true;
		}
		
	}
}
