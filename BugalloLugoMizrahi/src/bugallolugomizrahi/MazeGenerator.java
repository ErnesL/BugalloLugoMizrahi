/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bugallolugomizrahi;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import bugallolugomizrahi.interfaz.*;

/**
 *
 * @author davidmizrahi
 */



public class MazeGenerator extends Canvas
{
        private Ventana1 ventana = new Ventana1();
	private static final Random rand = new Random();
	public static  int WIDTH = 3;
	public static  int HEIGHT = 3;
	private static final int TILE_WIDTH = 10;
	private static final int TILE_HEIGHT = 10;

	public Lista<Vector2I> maze = new Lista<>();
	private Map<Vector2I, Color> colors = new HashMap<>();

	private int step = 0;

	public void paint(Graphics g)
	{
                int xCoordEntry = 0;
                int yCoordEntry = 0;
                int xCoordExit = 0;
                int yCoordExit = 0;
                int entry = generateEntryandEscape()[0];
                int exit = generateEntryandEscape()[1];
                int counter = 0;
                int[][] matrix = new int[HEIGHT][WIDTH];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        matrix[i][j] = counter;
                        ++counter;
                    }
                
            }
                
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if(matrix[i][j] == entry){
                            xCoordEntry = i;
                            yCoordEntry = j;
                        }
                        if(matrix[i][j] == exit){
                            xCoordExit = i;
                            yCoordExit = j;
                        }
                        
                    }
                
            }
                
		super.paint(g);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);
		g.setColor(Color.LIGHT_GRAY);

		g.drawLine(0, 0, 0, HEIGHT * TILE_HEIGHT);
		g.drawLine(0, 0, WIDTH * TILE_WIDTH, 0);
		g.drawLine(WIDTH * TILE_WIDTH, 0, WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);
		g.drawLine(0, HEIGHT * TILE_HEIGHT, WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);

		//Use this line instead of the one below this to see the maze progressively generate
//		List<Vector2I> mazeSteped = maze.subList(0, step);
		Lista<Vector2I> mazeSteped = maze;

		for(int y = 0; y < HEIGHT; y++)
		{
			for(int x = 0; x < WIDTH; x++)
			{
				int current = (y * WIDTH) + x;
				int lower = ((y + 1) * WIDTH) + x;
				if(!mazeSteped.buscar(new Vector2I(current, lower)))
					g.drawLine(x * TILE_WIDTH, (y + 1) * TILE_HEIGHT, (x + 1) * TILE_WIDTH, (y + 1) * TILE_HEIGHT);

				if(!mazeSteped.buscar(new Vector2I(current, current + 1)))
					g.drawLine((x + 1) * TILE_WIDTH, y * TILE_HEIGHT, (x + 1) * TILE_WIDTH, (y + 1) * TILE_HEIGHT);

				if(colors.containsKey(new Vector2I(x, y)))
				{
					g.setColor(colors.get(new Vector2I(x, y)));
					g.fillRect(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
					g.setColor(Color.LIGHT_GRAY);
				}
			}
		}
                g.fillRect(xCoordEntry, yCoordEntry, 5,5);
                g.fillRect(xCoordExit, yCoordExit, 5,5);
	}

	public void generate()
	{
//            maze.agregarElemento(new Vector2I(0,1));
//            maze.agregarElemento(new Vector2I(0,3));
//            maze.agregarElemento(new Vector2I(3,6));
//            maze.agregarElemento(new Vector2I(3,4));
//            maze.agregarElemento(new Vector2I(4,5));
//            maze.agregarElemento(new Vector2I(5,8));
            
            
            
                Lista<Integer> visited = new Lista<>();
		Lista<Vector2I> toVisit = new Lista<>();
//                Lista<Lista<Vector2I>> maze2D = new Lista<>();
//                Lista<Vector2I> list = new Lista<>();
                
                int counter = 0;

//		loadImageIn(visited, "tree.png");

		visited.agregarElemento(0);
		toVisit.agregarElemento(new Vector2I(0, 1));
		toVisit.agregarElemento(new Vector2I(0, WIDTH));
//                list.agregarElemento(new Vector2I(0, 1));

		while(toVisit.size() > 0)
		{
                    
//                    counter++;
			int randomIndex = rand.nextInt(toVisit.size());
//                         if(randomIndex == 0)
//                            list.agregarElemento(toVisit.buscarPorIndice(randomIndex));
//                        else
//                            list.agregarElemento(toVisit.get(randomIndex - 1));
                         
			Vector2I nextPath = (Vector2I)toVisit.deleteNth(randomIndex);
                       
                        

			if(visited.buscar(nextPath.end))
				continue;

			if(nextPath.start > nextPath.end)
				maze.agregarElemento(new Vector2I(nextPath.end, nextPath.start));
//                                list.agregarElemento(nextPath.end);
//                                maze2D.agregarElemento(x);
                                
			else
				maze.agregarElemento(nextPath);

			visited.agregarElemento(nextPath.end);

			int above = nextPath.end - WIDTH;
			if(above > 0 && !visited.buscar(above))
				toVisit.agregarElemento(new Vector2I(nextPath.end, above));

			int left = nextPath.end - 1;
			if(left % WIDTH != WIDTH - 1 && !visited.buscar(left))
				toVisit.agregarElemento(new Vector2I(nextPath.end, left));

			int right = nextPath.end + 1;
			if(right % WIDTH != 0 && !visited.buscar(right))
				toVisit.agregarElemento(new Vector2I(nextPath.end, right));

			int below = nextPath.end + WIDTH;
			if(below < WIDTH * HEIGHT && !visited.buscar(below))
				toVisit.agregarElemento(new Vector2I(nextPath.end, below));
 		}

		// Uncomment this to show the maze progressively generate
//		Timer timer = new Timer(700, (e) ->
//		{
//			step();
//			MazeGenerator.this.repaint();
//		});
//		timer.setRepeats(true);
//		timer.start();
//                list.imprimirValores();
	}

        public int[] generateEntryandEscape1(){
            int[] mat = getRowsandColumns();
            Random newRandom = new Random();
            int ans = newRandom.nextInt(mat.length);
            int entry = 0;
            int exit = 0;
//            for (int i = 0; i < ans; i++) {
//                entry = mat[i];
//                
//            }
            entry = mat[ans];
            mat[ans] = -1;
            ans = newRandom.nextInt(mat.length);
            if(mat[ans] != -1){
                exit = mat[ans];
            }
            mat[ans] = -1;
            
            return new int[]{entry, exit};
        }
        public int[] generateEntryandEscape(){
            int entry = generateEntryandEscape1()[0];
            int exit = generateEntryandEscape1()[1];
            
            while(!proof(entry, exit)){
            int[] mat = getRowsandColumns();
            Random newRandom = new Random();
            int ans = newRandom.nextInt(mat.length);
             entry = 0;
             exit = 0;

            entry = mat[ans];
            mat[ans] = -1;
            ans = newRandom.nextInt(mat.length);
            if(mat[ans] != -1){
                exit = mat[ans];
            }
            mat[ans] = -1;}
            return new int[] {entry, exit};
        }
        public boolean bfsTraversal(int entry, int exit){
            Lista<Vector2I> newMaze = new Lista<>();
            Lista<Integer> visited = new Lista<>();
            Lista<Integer> paths = new Lista<>();
//            newMaze = this.maze;
            Queue<Integer> q = new Queue<>();
            q.enqueue(entry);
            visited.agregarElemento(entry);
            
            while(!q.isEmpty()){
           
                int temp = q.dequeue();
            
                if(temp == exit)return true;
                
            
                for (int i = 0; i < newMaze.size(); i++) {
                    Vector2I aux = (Vector2I)newMaze.buscarPorIndice(i);
//                    Vector2I auxEnd = (Vector2I)newMaze.buscarPorIndice(i);
                
                    if(aux.start == temp && !visited.buscar(aux.end)){
                    
                        int newEntry = aux.end;
                    
                        visited.agregarElemento(newEntry);
                        
                        
                        q.enqueue(newEntry);
                    
                                        
                }
                
            }
            }
            return false;
            
        }
        public boolean proof(int entry, int exit ){
            Lista<Vector2I> newMaze = new Lista<>();
            Lista<Integer> visited = new Lista<>();
            newMaze = this.maze;
            Queue<Integer> q = new Queue<>();
            q.enqueue(entry);
            visited.agregarElemento(entry);
            
            while(!q.isEmpty()){
           
                int temp = q.dequeue();
            
                if(temp == exit)return true;
            
                for (int i = 0; i < newMaze.size(); i++) {
                
                    Vector2I aux = (Vector2I)newMaze.buscarPorIndice(i);
//                    Vector2I auxEnd = (Vector2I)newMaze.buscarPorIndice(i);
                    if(aux.start == temp && !visited.buscar(aux.end)){
                    
                        int newEntry = aux.end;
//                        int newEntry = newMaze.get(i).end;
                    
                        visited.agregarElemento(newEntry);
                        
                        q.enqueue(newEntry);
                    
                                        
                }
                
            }
            }
            visited.imprimirValores();
            return false;
            
        }
        
//        public static String findpaths(ArrayList<Vector2I> lab,
//                              int src, int dst, int v)
//{
//     
//    // Create a queue which stores
//    // the paths
//     lab = MazeGenerator.maze;
//    Queue<ArrayList<Integer>> queue = new Queue<>();
//    String s = "";
// 
//    // Path vector to store the current path
//    ArrayList<Integer> path = new ArrayList<>();
//    path.add(src);
//    queue.enqueue(path);
//     
//    while (!queue.isEmpty())
//    {
//        path = queue.dequeue();
//        int last = path.get(path.size() - 1);
// 
//        // If last vertex is the desired destination
//        // then print the path
//        if (last == dst)
//        {
////           String s = "";
//    int size = path.size();
//    for(Integer p : path)
//    {
//        s+= g.getVertices().buscarPorIndice(p) + "->";
//    }
////    System.out.println();
//    s+="\n";
////    JOptionPane.showMessageDialog(null, s);
//        }
// 
//        // Traverse to all the nodes connected to
//        // current vertex and push new path to queue
//        
////        Integer[] lastNode = g.getEdges()[last];
//
//    
//        Integer[] lastNode = g.getEdges()[last];
////        ArrayList<Integer> lastNode = g.get(last);
//        for(int i = 0; i < lastNode.length; i++)
//        {                   //lastNode[i]
//            if (lastNode[i] != null && isNotVisited(i, path))
//            {
//                ArrayList<Integer> newpath = new ArrayList<>(path);
//                
////                            lastNode[i];
//                newpath.add(i);
//                queue.enqueue(newpath);
//            }
//        }
//    }
//        return s;
//}
//        public static boolean isNotVisited(int x, ArrayList<Integer> path)
//                                    
//{
//    int size = path.size();
//    for(int i = 0; i < size; i++){
//        if (path.get(i) == x)
//            return false;
//    }
//    return true;
//}
        public int[] getRowsandColumns(){
            int[][] matrix = new int[HEIGHT][WIDTH];
            int counter = 0;
            int[] array = new int[WIDTH*4];
            
            boolean flag_0 = false;
            boolean flag_1 = false;
            boolean flag_2 = false;
            boolean flag_3 = false;
            
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = counter;
                    ++counter;
                }
                
            }
            int c = 0;
             
            for (int i = 0; i < matrix.length; i++) {

                for (int j = 0; j < matrix[0].length; j++) {
                    if(j == (WIDTH - 1) && !flag_2){
                        for (int k = 0; k < array.length; k++) {
                           if(k == HEIGHT)break;
                           if(c < array.length)
                                array[c] = matrix[k][j];
                                c++;
                        }
                        flag_2 = true;
                        
                    }
                    else if(i == (HEIGHT - 1) && !flag_3){
                        for (int k = 0; k < array.length; k++) {
                            if(k == WIDTH)break;
                            if(c < array.length)
                                array[c] = matrix[i][k];
                                c++;
                            
                        }
                        flag_3 = true;
                        
                    }
                    else if(j == 0 && !flag_0){
                        for (int k = 0; k < array.length; k++) {
                            if(k == HEIGHT)break;
                            if(c < array.length)
                                array[c] = matrix[k][j];
                                c++;
                            
                        }
                        flag_0 = true;
                    }else if(i == 0 && !flag_1){
                        for (int k = 0; k < array.length; k++) {
                            if(k == WIDTH)break;
                            if(c < array.length)
                                array[c] = matrix[i][k];
                                c++;
                            
                        }
                        flag_1 = true;
                    }
                    
                }
                
            }
            
            
            return array;
        }
	public void loadImageIn(List<Integer> visited, String file)
	{
		try
		{
			BufferedImage image = ImageIO.read(new File("res/" + file));
			for(int y = 0; y < image.getHeight(); y++)
			{
				for(int x = 0; x < image.getWidth(); x++)
				{
					int rgb = image.getRGB(x, y);
					if(rgb != -1)
					{
						visited.add((y * WIDTH) + x);
						colors.put(new Vector2I(x, y), new Color(rgb));
					}
				}
			}
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void step()
	{
		step++;
		if(step >= maze.size())
			step = maze.size() - 1;
	}
        
         public int[] bfs(List<Vector2I> maze, int src, int tar){
    Queue<Integer> q = new Queue<>();
//    maze = this.maze;
    int[] ans = new int[7];
    Lista<Integer> list = new Lista<>();
             for (int i = 0; i < maze.size(); i++) {
                 if(i == 0){
                    list.agregarElemento(maze.get(i).start);
                    list.agregarElemento(maze.get(i).end);
                 } else{
                    list.agregarElemento(maze.get(i).end);
                 }
             }
    int gSize = maze.size();
    boolean[] visited = new boolean[gSize];
//    visited[src] = true;
//    int[] ans = new int[g.getEdges()[0].length];
//    
//    q.enqueue(src);
//        
//    while(!q.isEmpty()){
//            int index = q.dequeue();
//            Integer[] explore = g.getEdges()[index];
//            visited[index] = true;
//            
//            for(int i = 0; i < explore.length; i++){
//            
//                if(explore[i] != null && !visited[index])q.enqueue(i);
//                
//                if(index == tar)ans[i] = g.getEdges()[index][i];
//            }
//            
//        }
        return ans;
    }

	public static void main(String[] args)
	{
		MazeGenerator mazeGen = new MazeGenerator();
		mazeGen.generate();
                int[] test = new int[WIDTH*4];
                test = mazeGen.getRowsandColumns();
                System.out.println(mazeGen.generateEntryandEscape());
//                for (int i = 0; i < test.length; i++) {
//                    System.out.println(test[i]);
//                
//            }
                boolean p = mazeGen.proof(3,8);
                System.out.println("PRUEBA DE PROOF");
                System.out.println(p);
                System.out.println("----------");
                mazeGen.generateEntryandEscape();
                
                int tar = mazeGen.maze.size();
//                mazeGen.bfs(mazeGen.maze, 0, tar);
		mazeGen.setSize(WIDTH, HEIGHT);
        JFrame frame = new JFrame("Laberinto");
        frame.add(mazeGen);
        frame.setSize(WIDTH * 11, HEIGHT * 12);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	}


    /**
     * @param args the command line arguments
     */
   
    

