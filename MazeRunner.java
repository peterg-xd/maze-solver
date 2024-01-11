package mazeassignment;
/* * * * * Imports * * * * */
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * [MazeRunner.java]
 * This class starts the program
 * @author Peter Gao
 * @version 1.0
 * @since November 2021
 */
public class MazeRunner {

	private static Maze m;

	public static void main(String[] args) {
		String user = JOptionPane.showInputDialog("Input the file name: ");
		try{
			File file = new File("files\\"+user+".txt");
			Scanner sc = new Scanner(file);
			int width = 0, height = 0;
			ArrayList<String> lines = new ArrayList<String>();
			while(sc.hasNextLine()) {
				lines.add(sc.nextLine());
				height++; 
			}
			if(!lines.isEmpty()) {
				width = lines.get(0).length();
			}
			sc.close();

			char map[][] = new char[height][width];
			for(int i=0; i<height; i++) {
				map[i] = lines.get(i).toCharArray();
			}
			m = new Maze(map, height, width);

			JFrame frame = new JFrame();
			Visualizer v = new Visualizer();
			frame.getContentPane().add(v);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1200, 1000);
			frame.setResizable(true);
			frame.setVisible(true);

			MazeSolver ms = new MazeSolver();
			ms.findExit(m);

		}catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
	}

	/**
	 * [Visualizer.java]
	 * This inner class extends JPanel and draws to the panel
	 * @author Peter Gao
	 * @version 1.0
	 * @since November 2021
	 */
	private static class Visualizer extends JPanel {

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			m.draw(g);
			repaint();
		}

	}
}
