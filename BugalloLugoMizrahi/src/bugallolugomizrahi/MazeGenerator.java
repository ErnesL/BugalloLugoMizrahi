/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bugallolugomizrahi;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import bugallolugomizrahi.interfaz.*;

/**
 *
 * @author davidmizrahi
 */
public class MazeGenerator extends Canvas {

    private Ventana1 ventana = new Ventana1();
    private static final Random rand = new Random();
    public static int WIDTH = 10;
    public static int HEIGHT = 10;
    public static int TILE_WIDTH = 10;
    public static int TILE_HEIGHT = 10;

    public Lista<Vector2I> maze = new Lista<>();
    private Map<Vector2I, Color> colors = new HashMap<>();

    public void paint(Graphics g) {

        super.paint(g);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);
        g.setColor(Color.LIGHT_GRAY);

        g.drawLine(0, 0, 0, HEIGHT * TILE_HEIGHT);
        g.drawLine(0, 0, WIDTH * TILE_WIDTH, 0);
        g.drawLine(WIDTH * TILE_WIDTH, 0, WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);
        g.drawLine(0, HEIGHT * TILE_HEIGHT, WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);

        Lista<Vector2I> mazeSteped = maze;

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int current = (y * WIDTH) + x;
                int lower = ((y + 1) * WIDTH) + x;
                if (!mazeSteped.buscar(new Vector2I(current, lower))) {
                    g.drawLine(x * TILE_WIDTH, (y + 1) * TILE_HEIGHT, (x + 1) * TILE_WIDTH, (y + 1) * TILE_HEIGHT);
                }

                if (!mazeSteped.buscar(new Vector2I(current, current + 1))) {
                    g.drawLine((x + 1) * TILE_WIDTH, y * TILE_HEIGHT, (x + 1) * TILE_WIDTH, (y + 1) * TILE_HEIGHT);
                }

                if (colors.containsKey(new Vector2I(x, y))) {
                    g.setColor(colors.get(new Vector2I(x, y)));
                    g.fillRect(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
                    g.setColor(Color.LIGHT_GRAY);
                }
            }
        }

        int randInt = Randomizer.generate(1, 4);
        int randInt2 = Randomizer.generate(1, 4);
        while (randInt == randInt2) {
            randInt2 = Randomizer.generate(1, 4);
        }

        int entry = entryEscape(randInt);
        g.setColor(Color.green);
//        System.out.println(entry);
        switch (randInt) {
            case (1):
                g.fillRect((WIDTH * TILE_WIDTH) - TILE_WIDTH * (WIDTH - entry), 0, TILE_WIDTH, TILE_HEIGHT);//BORDE SUPERIOR 1
                break;
            case (3):
                g.fillRect((WIDTH * TILE_WIDTH) - TILE_WIDTH * (WIDTH - entry), (HEIGHT * TILE_HEIGHT) - TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);//BORDE INFERIOR 3
                break;
            case (4):
                g.fillRect(0, (HEIGHT * TILE_HEIGHT) - TILE_HEIGHT * (HEIGHT - entry), TILE_WIDTH, TILE_HEIGHT);//BORDE IZQUIERDO 4
                break;
            case (2):
                g.fillRect((WIDTH * TILE_WIDTH) - TILE_WIDTH, (HEIGHT * TILE_HEIGHT) - (TILE_HEIGHT * (HEIGHT - entry)), TILE_WIDTH, TILE_HEIGHT);//BORDE DERECHO 2
                break;
        }
        int exit = entryEscape(randInt2);
        g.setColor(Color.red);
        switch (randInt2) {
            case (1):
                g.fillRect((WIDTH * TILE_WIDTH) - TILE_WIDTH * (WIDTH - exit), 0, TILE_WIDTH, TILE_HEIGHT);//BORDE SUPERIOR 1
                break;
            case (3):
                g.fillRect((WIDTH * TILE_WIDTH) - TILE_WIDTH * (WIDTH - exit), (HEIGHT * TILE_HEIGHT) - TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);//BORDE INFERIOR 3
                break;
            case (4):
                g.fillRect(0, (HEIGHT * TILE_HEIGHT) - TILE_HEIGHT * (HEIGHT - exit), TILE_WIDTH, TILE_HEIGHT);//BORDE IZQUIERDO 4
                break;
            case (2):
                g.fillRect((WIDTH * TILE_WIDTH) - TILE_WIDTH, (HEIGHT * TILE_HEIGHT) - (TILE_HEIGHT * (HEIGHT - exit)), TILE_WIDTH, TILE_HEIGHT);//BORDE DERECHO 2
                break;
        }

    }

    public void generate() {

        Lista<Integer> visited = new Lista<>();
        Lista<Vector2I> toVisit = new Lista<>();
        visited.agregarElemento(0);
        toVisit.agregarElemento(new Vector2I(0, 1));
        toVisit.agregarElemento(new Vector2I(0, WIDTH));

        while (toVisit.size() > 0) {

            int randomIndex = rand.nextInt(toVisit.size());
            Vector2I nextPath = (Vector2I) toVisit.deleteNth(randomIndex);

            if (visited.buscar(nextPath.end)) {
                continue;
            }

            if (nextPath.start > nextPath.end) {
                maze.agregarElemento(new Vector2I(nextPath.end, nextPath.start));
            } else {
                maze.agregarElemento(nextPath);
            }

            visited.agregarElemento(nextPath.end);
            int above = nextPath.end - WIDTH;
            if (above > 0 && !visited.buscar(above)) {
                toVisit.agregarElemento(new Vector2I(nextPath.end, above));
            }
            int left = nextPath.end - 1;
            if (left % WIDTH != WIDTH - 1 && !visited.buscar(left)) {
                toVisit.agregarElemento(new Vector2I(nextPath.end, left));
            }
            int right = nextPath.end + 1;
            if (right % WIDTH != 0 && !visited.buscar(right)) {
                toVisit.agregarElemento(new Vector2I(nextPath.end, right));
            }
            int below = nextPath.end + WIDTH;
            if (below < WIDTH * HEIGHT && !visited.buscar(below)) {
                toVisit.agregarElemento(new Vector2I(nextPath.end, below));
            }
        }

    }

    public int entryEscape(int randInt) {
        Random random = new Random();
        int entry = 0;

        switch (randInt) {
            case 1:
                //BORDE SUPERIOR
                entry = random.nextInt(WIDTH - 1);
                break;
            case 2:
                //BORDE DERECHO
                entry = random.nextInt(HEIGHT - 1);
                break;
            case 3:
                //BORDE INFERIOR
                entry = random.nextInt(WIDTH - 1);
                break;
            case 4:
                //BORDE IZQUIERDO
                entry = random.nextInt(HEIGHT - 1);
                break;
        }
        return entry;
    }

}
