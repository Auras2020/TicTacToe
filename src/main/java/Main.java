import javax.swing.JApplet;
import javax.swing.JFrame;

public class Main extends JApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Tic Tac Toe");
        window.setContentPane(new SolveTicTacToe(window));
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);  
	}

	public Main() {
	       this.setContentPane(new SolveTicTacToe(null));
	    }
}
