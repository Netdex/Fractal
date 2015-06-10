import javax.swing.JFrame;


public class Fractal extends JFrame {

	public Fractal(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		FractalPanel fp = new FractalPanel();
		this.add(fp);
	}
	
	public static void main(String[] args) {
		Fractal f = new Fractal();
		f.pack();
		f.setVisible(true);
	}
}
