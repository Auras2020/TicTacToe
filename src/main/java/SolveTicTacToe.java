import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SolveTicTacToe extends JPanel implements Runnable, KeyListener, MouseListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 500, HEIGHT = 500;
    
    private Thread thread;
    private boolean running;
    
    private Table patrat;
    private Table[][] table=new Table[3][3];
    private Table first;
    private List<Table> x=new ArrayList<>();
    private List<Table> o=new ArrayList<>();
    
    private Borders border;
    private Borders[][] borders=new Borders[2][4];
    
    private int count=0;
    
    private int xCoor = 200;
    private int yCoor = 200;
    private int patratSize = 30;
    
    private int xCoor1 = 190;
    private int yCoor1 = 190;
    private int widthBorder=130;
    private int heightBorder=10;
    
    private boolean X=false;
    private boolean O=false;
    private boolean draw=false;
    private boolean winX=false;
    private boolean winO=false;
    
    private int ticks = 0;
	
    public SolveTicTacToe(JFrame window) {
    	addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.addMouseListener(this); 
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        borders=new Borders[2][4];
        table=new Table[3][3];
        start();
    }
    
    public void tick() {
    	if(ticks==0) {
    		for(int i=0; i<3; i++) {
    			for(int j=0; j<3; j++) {
    				patrat=new Table(xCoor+i*40, yCoor+j*40, patratSize);
    				table[i][j]=patrat;
    			}
    		}
    		for(int i=0; i<2; i++) {
    			for(int j=0; j<4; j++) {
    				if(i==0) {
    					border=new Borders(xCoor1, yCoor1+j*40, widthBorder, heightBorder);
    				    borders[i][j]=border;
    				}
    				else {
    					border=new Borders(xCoor1+j*40, yCoor1, heightBorder, widthBorder);
    				    borders[i][j]=border;
    				}
    			}
    		}
    	}
    	
    	ticks++;
    	if(ticks>10) {
    		ticks=1;
    	}
    }
    
    public void play() {
    	for(int i=0; i<3; i++) {
    		if((x.contains(table[i][0]) && x.contains(table[i][1]) && x.contains(table[i][2]))
    		|| (x.contains(table[0][i]) && x.contains(table[1][i]) && x.contains(table[2][i]))
    		|| (x.contains(table[0][0]) && x.contains(table[1][1]) && x.contains(table[2][2]))
    		|| (x.contains(table[0][2]) && x.contains(table[1][1]) && x.contains(table[2][0]))){
    			winX=true;
    			break;
    		}
    		else if((o.contains(table[i][0]) && o.contains(table[i][1]) && o.contains(table[i][2]))
    	    		|| (o.contains(table[0][i]) && o.contains(table[1][i]) && o.contains(table[2][i]))
    	    		|| (o.contains(table[0][0]) && o.contains(table[1][1]) && o.contains(table[2][2]))
    	    		|| (o.contains(table[0][2]) && o.contains(table[1][1]) && o.contains(table[2][0]))) {
    			winO=true;
    			break;
    		}
    	}
    	if(x.size()+o.size()==9 && winX==false && winO==false) {
			draw=true;
		}
    }
    
    public void paint(Graphics g) {
    	g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        
        for(int i=0; i<3; i++) {
        	for(int j=0; j<3; j++) {
        		if(table[i][j].isSelected()==true) {
        			table[i][j].setColor(Color.YELLOW);
        		}
        		else if(table[i][j].isSelected()==false) {
        			table[i][j].setColor(Color.WHITE);
        		}
        		table[i][j].draw(g);
        	}
        }
        
        for(int i=0; i<3; i++) {
        	for(int j=0; j<3; j++) {
        		if(table[i][j].isSelected()==true) {
        			if(X==true) {
        				if(table[i][j].isChecked()==false) {
        					if((o.size()==0 && x.size()==0) || o.size()==x.size()+1 || 
        							(count%2==0 && x.contains(first))) {
        						x.add(table[i][j]);
        					    count++;
        					    if(count==1) {
        					    	first=table[i][j];
        					    }
        					    table[i][j].setChecked(true);
        					}
        					
        				}
        			}
        			else if(O==true) {
        				if(table[i][j].isChecked()==false) {
        					if((x.size()==0 && o.size()==0) || x.size()==o.size()+1 ||
        							(count%2==0 && o.contains(first))) {
        						o.add(table[i][j]);
        					    count++;
        					    if(count==1) {
        					    	first=table[i][j];
        					    }
        					    table[i][j].setChecked(true);
        					}	
        				}
        				
        			}
        		}
        	}
        }
        
        g.setColor(Color.BLACK);
        
        for(int i=0; i<x.size(); i++) {
        	g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        	g.drawString("X", x.get(i).getxCoor()+5, x.get(i).getyCoor()+25);
        }
        
        for(int i=0; i<o.size(); i++) {
        	g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        	g.drawString("O", o.get(i).getxCoor()+5, o.get(i).getyCoor()+25);
        }
        
        play();
        if(draw==true) {
        	g.setColor(Color.RED);
        	g.drawString("REMIZA", 100, 100);
        	stop();
        }
        else if(winX==true) {
        	g.setColor(Color.RED);
        	g.drawString("VICTORIE X", 100, 100);
        	stop();
        }
        else if(winO==true) {
        	g.setColor(Color.RED);
        	g.drawString("VICTORIE O", 100, 100);
        	stop();
        }
        
        for(int i=0; i<2; i++) {
        	for(int j=0; j<4; j++) {
        		borders[i][j].draw(g);
        	}
        }
        
    }
    
    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
 
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_X) {
			X=true;
			O=false;
		}
		if(key==KeyEvent.VK_O) {
			X=false;
			O=true;
		}
		if(key==KeyEvent.VK_ENTER) {
			if(running==false) {
				X=false;
				O=false;
				winX=false;
				winO=false;
				draw=false;
				borders=new Borders[2][4];
				table=new Table[3][3];
				x=new ArrayList<>();
				o=new ArrayList<>();
				count=0;
				ticks=0;
				repaint();
				start();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 while (running) {
	            tick();
	            repaint();
	        }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();   // Save the x coord of the click
        int y = e.getY();   // Save the y coord of the click
        
        for(int i=0; i<3; i++) {
        	for(int j=0; j<3; j++) {
        		 if(x>=table[i][j].getxCoor() && x<=table[i][j].getxCoor()+patratSize &&
        				 y>=table[i][j].getyCoor() && y<=table[i][j].getyCoor()+patratSize) {
        			 table[i][j].setSelected(true);
        			 X=false;
        			 O=false;
        		 }
        		 else {
        			 table[i][j].setSelected(false);
        		 }
        	}
        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
}
