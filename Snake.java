import greenfoot.*;

public class Snake extends Actor {
    Tail[] segments = new Tail[99];

    int tempX = 0, tempY = 0, length = 0;
    int X = 0, Y = 0;
    int x2, y2;

    boolean moveable = true;
    boolean explode = true;

    private int levelStartDelay = 10;
    public Snake() {
        GreenfootImage image = new GreenfootImage(20, 20);
        image.setColor(Color.GREEN);
        image.fillRect(0, 0, 20, 20);
        setImage(image);
    }

    public void act() {
        SnakeWorld world = (SnakeWorld) getWorld();

        // Cegah pergerakan ular selama countdown
        if (world.isLevelStarting) {
            return; // Jangan lakukan apa-apa selama hitungan mundur
        }

        // Pindahkan ular jika bisa bergerak
        if (moveable) {
            if (levelStartDelay > 0) {
                levelStartDelay--; // Tunggu hingga delay habis
            } else {
                keys(); // Mulai memproses input pengguna
                movement();
                pickup();
                checkCrash(); // Periksa tabrakan
            }
        }

        // Jika menyentuh portal, pindah ke level berikutnya
        Portal portal = (Portal) getOneIntersectingObject(Portal.class);
        if (portal != null) {
            ((SnakeWorld) getWorld()).nextLevel();
        }

        if(moveable==false)
        {
            if(explode==true)
            {
                Greenfoot.playSound("Explosion.wav");
                explode=false;
            }
            Greenfoot.setSpeed(100);
            getWorld().showText("Game Over!", getWorld().getWidth() / 2, getWorld().getHeight() / 2); // Tampilkan pesan
            getWorld().addObject(new Boom(), Greenfoot.getRandomNumber(30), Greenfoot.getRandomNumber(30));

            // Tampilkan layar Game Over dengan skor dan level
            world.showGameOver(length, world.getLevel());
        }
    }

    private void movement() {
        tempX = getX();
        tempY = getY();

        // Pindahkan kepala ular
        setLocation(tempX + X, tempY + Y);

        // Pindahkan ekor mengikuti kepala
        x2 = tempX;
        y2 = tempY;
        for (int i = 1; i <= length; i++) {
            int prevX = segments[i].getX();
            int prevY = segments[i].getY();

            segments[i].setLocation(x2, y2);
            x2 = prevX;
            y2 = prevY;
        }
    }

    public void setDirection(String direction) {
        switch(direction) {
            case "UP":
                if(Y != 1) {
                    X = 0;
                    Y = -1;
                }
                break;
            case "DOWN":
                if(Y != -1) {
                    X = 0;
                    Y = 1;
                }
                break;
            case "LEFT":
                if(X != 1) {
                    X = -1;
                    Y = 0;
                }
                break;
            case "RIGHT":
                if(X != -1) {
                    X = 1;
                    Y = 0;
                }
                break;
        }
    }

    private void keys() {
        if (Greenfoot.isKeyDown("w") && Y != 1) {
            X = 0;
            Y = -1;
        }
        if (Greenfoot.isKeyDown("s") && Y != -1) {
            X = 0;
            Y = 1;
        }
        if (Greenfoot.isKeyDown("a") && X != 1) {
            X = -1;
            Y = 0;
        }
        if (Greenfoot.isKeyDown("d") && X != -1) {
            X = 1;
            Y = 0;
        }
    }

    private void pickup() {
        // Jika menyentuh makanan, tambahkan panjang ular
        Food food = (Food) getOneIntersectingObject(Food.class);
        if (food != null) {
            length++;
            getWorld().removeObject(food);

            // Tambahkan ekor baru
            segments[length] = new Tail();
            getWorld().addObject(segments[length], x2, y2);

            // Perbarui counter panjang ekor
            SnakeWorld world = (SnakeWorld) getWorld();
            world.updateCounter(length);

            // Jika panjang mencapai 5, tambahkan portal dan hapus semua makanan
            if (length >= world.getRequiredEats()) {
                if (getWorld().getObjects(Portal.class).isEmpty()) { // Periksa apakah portal belum ada
                    getWorld().addObject(new Portal(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
                    getWorld().removeObjects(getWorld().getObjects(Food.class));
                    getWorld().removeObjects(getWorld().getObjects(Obstacle.class));
                }
            }
        }
    }

    private void checkCrash() {
        // Jika ular menabrak pinggiran dunia
        if(getX()>=28 || getX()<=1 || getY()<=1 || getY()>=28)
        {
            moveable=false;
        }

        // Jika ular menabrak obstacle
        Obstacle obstacle = (Obstacle) getOneIntersectingObject(Obstacle.class);
        if (obstacle != null) {
            moveable = false;
        }

        for (int i = 1; i <= length; i++) {
            if (segments[i] != null && intersects(segments[i])) {
                moveable = false;
            }
        }
    }
}
