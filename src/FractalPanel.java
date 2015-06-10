import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class FractalPanel extends JPanel {

	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;

	private static int ITERATIONS = 5;
	private static int BRANCHES = 4;
	private static int BRANCH_LENGTH = 175;

	public FractalPanel() {
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.WHITE);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				int keycode = event.getKeyCode();
				if (keycode == KeyEvent.VK_UP) {
					if (BRANCHES < 20) {
						BRANCHES++;
						repaint();
					}
				} else if (keycode == KeyEvent.VK_DOWN) {
					if (BRANCHES > 1) {
						BRANCHES--;
						repaint();
					}
				} else if (keycode == KeyEvent.VK_LEFT) {
					if (ITERATIONS > 1) {
						ITERATIONS--;
						repaint();
					}
				} else if (keycode == KeyEvent.VK_RIGHT) {
					if (ITERATIONS < 6) {
						ITERATIONS++;
						repaint();
					}
				}
			}
		});
	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawString("Branches: " + BRANCHES, 10, 15);
		g.drawString("Iteration: " + ITERATIONS, 10, 30);
		
		Point2D start = new Point2D.Double(WIDTH / 2, HEIGHT / 2);
		drawNFlake(g, start, ITERATIONS, 1);
	}

	public void drawNFlake(Graphics2D g, Point2D lastPoint, int iterations,
			double size) {
		if (iterations == 0)
			return;
		double baseAngle = Math.PI * 2 / BRANCHES;

		for (int i = 0; i < BRANCHES; i++) {
			double angle = baseAngle * (i + 1);
			Point2D p = new Point2D.Double(BRANCH_LENGTH * size
					* Math.sin(angle) + lastPoint.getX(), BRANCH_LENGTH * size
					* Math.cos(angle) + lastPoint.getY());
			if((Math.abs(p.getX() - lastPoint.getX()) + Math.abs(p.getY() - lastPoint.getY())) / 2 < 0.2)
				return;
			g.draw(new Line2D.Double(lastPoint, p));
			drawNFlake(g, p, iterations - 1, size / 3);
		}
//		drawFractal(g, lastPoint, iterations - 1, size / 3);
	}

	@Override
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D) gr;
		render(g);
	}
}
