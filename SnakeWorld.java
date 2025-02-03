import greenfoot.*;
import java.util.ArrayList;

public class SnakeWorld extends World {
    private Snake snake;
    private int level;
    private ArrayList<int[]> tailPositions = new ArrayList<>();
    public int noms = 0;
    GreenfootSound music = new GreenfootSound("twiTech.mp3");

    private int requiredEats; // Jumlah makanan yang diperlukan untuk membuka portal

    public boolean isLevelStarting = true; // Status apakah level sedang dalam hitungan mundur

    private int countdownTimer = 3; // Hitungan mundur awal (dalam detik)
    private int delayFrames = 5;  // Konversi detik ke frame (1 detik = 60 frame)

    private counter lengthCounter; // Objek counter untuk panjang ekor
    public boolean isGameOver = false;

    public SnakeWorld() {
        this(1); // Mulai dari level 1
    }

    public SnakeWorld(int level) {
        super(30, 30, 20, false);

        this.level = level;
        countdownTimer = 3; // Reset countdown ke 3 detik
        isLevelStarting = true; // Setel ulang status level
        setPaintOrder(counter.class, Portal.class, Boom.class, Tail.class, Snake.class);

        GreenfootImage bg = new GreenfootImage("background.png");
        bg.scale(getWidth() * getCellSize(), getHeight() * getCellSize());// Sesuaikan ukuran gambar dengan dunia
        setBackground(bg);

        // Inisialisasi counter
        lengthCounter = new counter();
        addObject(lengthCounter, 25, 1); // Tambahkan counter ke layar (posisi 25,1)

        // Atur kecepatan berdasarkan level
        int speed = 35 + (level - 1) * 3; // Kecepatan awal 35, bertambah 3 setiap level
        Greenfoot.setSpeed(Math.min(speed, 100)); // Batas maksimum kecepatan adalah 100

        // Hitung jumlah makanan yang dibutuhkan untuk membuka portal
        requiredEats = level * 5; //level 1 butuh 5 makanan, level 2 butuh 10 makanan, dst.

        prepare();
        addControls();
    }

    public void act() {
        if (!music.isPlaying()) {
            music.play();
        }

        if (countdownTimer > 0) {
            showText("Level " + level + " starts in: " + countdownTimer, getWidth() / 2, getHeight() / 2);
            delayFrames--;
            if (delayFrames == 0) {
                countdownTimer--;
                delayFrames = 5;
            }
        } else if (isLevelStarting) {
            isLevelStarting = false; // Akhiri hitung mundur
            showText("", getWidth() / 2, getHeight() / 2); // Bersihkan layar
        }
    }

    public void nextLevel() {
        // Simpan posisi ekor sebelum pindah level
        tailPositions.clear();
        for (int i = 1; i <= snake.length; i++) {
            Tail tail = snake.segments[i];
            tailPositions.add(new int[] { tail.getX(), tail.getY() });
        }

        // Pindah ke dunia baru
        SnakeWorld newWorld = new SnakeWorld(level + 1);
        Greenfoot.setWorld(newWorld);

        // Tambahkan ekor di dunia baru
        newWorld.snake.length = snake.length;
        // Tambahkan segmen ekor di posisi yang tidak bertumpuk dengan kepala
        for (int i = 1; i <= newWorld.snake.length; i++) {
            int[] pos = tailPositions.get(i - 1);
            int tailX = pos[0];
            int tailY = pos[1];

            // Pastikan ekor tidak bertumpuk dengan kepala
            if (tailX == newWorld.snake.getX() && tailY == newWorld.snake.getY()) {
                tailY += 1; // Geser ekor ke bawah jika bertumpuk
            }

            newWorld.snake.segments[i] = new Tail();
            newWorld.addObject(newWorld.snake.segments[i], tailX, tailY);
        }

        // Pastikan portal direset di dunia baru
        if (!newWorld.getObjects(Portal.class).isEmpty()) {
            newWorld.removeObjects(newWorld.getObjects(Portal.class));
        }

    }

    private void prepare() {
        snake = new Snake();
        addObject(snake, 15, 15);

        // Tetapkan margin (ketebalan bingkai dalam sel)
        int margin = 3;

        // Tambahkan makanan sesuai level
        for (int i = 0; i < level + 5; i++) {
            int foodX = Greenfoot.getRandomNumber(getWidth() - margin * 2) + margin; // Pastikan tidak di bingkai
            int foodY = Greenfoot.getRandomNumber(getHeight() - margin * 2) + margin; // Pastikan tidak di bingkai
            addObject(new Food(), foodX, foodY);
        }

        // Tambahkan rintangan sesuai level
        for (int i = 0; i < level + 10; i++) {
            int obstacleX = Greenfoot.getRandomNumber(getWidth() - margin * 2) + margin; // Pastikan tidak di bingkai
            int obstacleY = Greenfoot.getRandomNumber(getHeight() - margin * 2) + margin; // Pastikan tidak di bingkai
            addObject(new Obstacle(), obstacleX, obstacleY);
        }

        // Tampilkan level di layar
        showText("Level: " + level, 20, 1);
    }

    public int getRequiredEats() {
        return requiredEats;
    }

    public void updateCounter(int length) {
        lengthCounter.setValue(length); // Perbarui nilai counter dengan panjang ekor
    }

    public void showGameOver(int length, int level) {
        // Tampilkan layar Game Over
        showText("GAME OVER", getWidth() / 2, getHeight() / 2 - 2);
        showText("Final Length: " + length, getWidth() / 2, getHeight() / 2);
        showText("Level Reached: " + level, getWidth() / 2, getHeight() / 2 + 2);
    }

    private void addControls() {
    // Tambahkan tombol di bagian bawah layar
    addObject(new TouchButton("UP"), 25, 20);
    addObject(new TouchButton("DOWN"), 25, 24);
    addObject(new TouchButton("LEFT"), 22, 22);
    addObject(new TouchButton("RIGHT"), 28, 22);
    }

    public Snake getSnake() {
    return snake;
    }

    public int getLevel() {
        return level; // Kembalikan level saat ini
    }

}
