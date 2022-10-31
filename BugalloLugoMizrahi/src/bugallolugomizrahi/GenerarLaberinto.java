///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package bugallolugomizrahi;
//
//import java.awt.Canvas;
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
////import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import javax.imageio.ImageIO;
//
///**
// *
// * @author davidmizrahi
// */
//public class GenerarLaberinto extends Canvas {
//    private static final Random rand = new Random();
//	private static final int WIDTH = 8;
//	private static final int HEIGHT = 8;
//	private static final int TILE_WIDTH = 10;
//	private static final int TILE_HEIGHT = 10;
//
//	private Lista<Vector2I> maze = new Lista<>();
//	private Map<Vector2I, Color> colors = new HashMap<>();
//
//	private int step = 0;
//
//	public void paint(Graphics g)
//	{
//		super.paint(g);
//
//		g.setColor(Color.DARK_GRAY);
//		g.fillRect(0, 0, WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);
//		g.setColor(Color.LIGHT_GRAY);
//
//		g.drawLine(0, 0, 0, HEIGHT * TILE_HEIGHT);
//		g.drawLine(0, 0, WIDTH * TILE_WIDTH, 0);
//		g.drawLine(WIDTH * TILE_WIDTH, 0, WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);
//		g.drawLine(0, HEIGHT * TILE_HEIGHT, WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);
//
//		//Use this line instead of the one below this to see the maze progressively generate
////		List<Vector2I> mazeSteped = maze.subList(0, step);
//		Lista<Vector2I> mazeSteped = maze;
//
//		for(int y = 0; y < HEIGHT; y++)
//		{
//			for(int x = 0; x < WIDTH; x++)
//			{
//				int current = (y * WIDTH) + x;
//				int lower = ((y + 1) * WIDTH) + x;
//				if(!mazeSteped.buscar(new Vector2I(current, lower)))
//					g.drawLine(x * TILE_WIDTH, (y + 1) * TILE_HEIGHT, (x + 1) * TILE_WIDTH, (y + 1) * TILE_HEIGHT);
//
//				if(!mazeSteped.buscar(new Vector2I(current, current + 1)))
//					g.drawLine((x + 1) * TILE_WIDTH, y * TILE_HEIGHT, (x + 1) * TILE_WIDTH, (y + 1) * TILE_HEIGHT);
//
//				if(colors.containsKey(new Vector2I(x, y)))
//				{
//					g.setColor(colors.get(new Vector2I(x, y)));
//					g.fillRect(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
//					g.setColor(Color.LIGHT_GRAY);
//				}
//			}
//		}
//	}
//
//	public void generate()
//	{
//            
//            maze.agregarElemento(new Vector2I(0,1));
//            maze.agregarElemento(new Vector2I(0,3));
//            maze.agregarElemento(new Vector2I(2,5));
//            maze.agregarElemento(new Vector2I(5,4));
//            maze.agregarElemento(new Vector2I(4,7));
////            maze.agregarElemento(new Vector2I(0,1));
////            maze.agregarElemento(new Vector2I(0,1));
////            maze.agregarElemento(new Vector2I(0,1));
//            
//            
////		Lista<Integer> visited = new Lista<>();
////		Lista<Vector2I> toVisit = new Lista<>();
////
//////		loadImageIn(visited, "tree.png");
////
////		visited.agregarElemento(0);
////		toVisit.agregarElemento(new Vector2I(0, 1));
////		toVisit.agregarElemento(new Vector2I(0, WIDTH));
////
////		while(toVisit.size() > 0)
////		{
////			int randomIndex = rand.nextInt(toVisit.size());
////			Vector2I nextPath = (Vector2I)toVisit.eliminarElemento(randomIndex);
////
////			if(visited.buscar(nextPath.end))
////				continue;
////
////			if(nextPath.start > nextPath.end)
////				maze.agregarElemento(new Vector2I(nextPath.end, nextPath.start));
////			else
////				maze.agregarElemento(nextPath);
////
////			visited.agregarElemento(nextPath.end);
////
////			int above = nextPath.end - WIDTH;
////			if(above > 0 && !visited.buscar(above))
////				toVisit.agregarElemento(new Vector2I(nextPath.end, above));
////
////			int left = nextPath.end - 1;
////			if(left % WIDTH != WIDTH - 1 && !visited.buscar(left))
////				toVisit.agregarElemento(new Vector2I(nextPath.end, left));
////
////			int right = nextPath.end + 1;
////			if(right % WIDTH != 0 && !visited.buscar(right))
////				toVisit.agregarElemento(new Vector2I(nextPath.end, right));
////
////			int below = nextPath.end + WIDTH;
////			if(below < WIDTH * HEIGHT && !visited.buscar(below))
////				toVisit.agregarElemento(new Vector2I(nextPath.end, below));
//// 		}
//
//		// Uncomment this to show the maze progressively generate
////		Timer timer = new Timer(700, (e) ->
////		{
////			step();
////			MazeGenerator.this.repaint();
////		});
////		timer.setRepeats(true);
////		timer.start();
//	}
//
//	public void loadImageIn(List<Integer> visited, String file)
//	{
//		try
//		{
//			BufferedImage image = ImageIO.read(new File("res/" + file));
//			for(int y = 0; y < image.getHeight(); y++)
//			{
//				for(int x = 0; x < image.getWidth(); x++)
//				{
//					int rgb = image.getRGB(x, y);
//					if(rgb != -1)
//					{
//						visited.add((y * WIDTH) + x);
//						colors.put(new Vector2I(x, y), new Color(rgb));
//					}
//				}
//			}
//		} catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	public void step()
//	{
//		step++;
//		if(step >= maze.size())
//			step = maze.size() - 1;
//	}
//        
//         public int[] bfs(List<Vector2I> maze, int src, int tar){
//    Queue<Integer> q = new Queue<>();
////    maze = this.maze;
//    int[] ans = new int[7];
//    Lista<Integer> list = new Lista<>();
//             for (int i = 0; i < maze.size(); i++) {
//                 if(i == 0){
//                    list.agregarElemento(maze.get(i).start);
//                    list.agregarElemento(maze.get(i).end);
//                 } else{
//                    list.agregarElemento(maze.get(i).end);
//                 }
//             }
//    int gSize = maze.size();
//    boolean[] visited = new boolean[gSize];
////    visited[src] = true;
////    int[] ans = new int[g.getEdges()[0].length];
////    
////    q.enqueue(src);
////        
////    while(!q.isEmpty()){
////            int index = q.dequeue();
////            Integer[] explore = g.getEdges()[index];
////            visited[index] = true;
////            
////            for(int i = 0; i < explore.length; i++){
////            
////                if(explore[i] != null && !visited[index])q.enqueue(i);
////                
////                if(index == tar)ans[i] = g.getEdges()[index][i];
////            }
////            
////        }
//        return ans;
//    }
//}
