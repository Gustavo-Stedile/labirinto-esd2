public class Main {
    public static void main(String[] args) {
        Window window = new Window();

        Labyrinth labyrinth = new Labyrinth(3, 3, new Point(0, 0), new Point(2, 1));
        window.use(labyrinth);

        window.onclickLargura(_ -> {
            System.out.println("oi");
            labyrinth.solveLargura();
        });

        window.setVisible(true);
    }
}