public class Labyrinth {
    private String[][] labyrinth;
    private int w, h;

    public Labyrinth(int w, int h) {
        this.labyrinth = new String[h][w];
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                labyrinth[y][x] = " ";

        this.w = w; this.h = h;
    }

    public int w() {
        return w;
    }

    public int h() {
        return h;
    }

    public void set(int x, int y, String value) {
        this.labyrinth[y][x] = value;
    }

    public void show() {
        for (String[] row : this.labyrinth) {
            for (String col : row) 
                System.out.print(col);

            System.out.println();
        }
    }
}