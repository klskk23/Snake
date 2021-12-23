import java.util.Random;

public class Food {
    private int x;
    private int y;
    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Food(){
        Random r=new Random();
        //生成横坐标
        this.x=r.nextInt(30);
        this.y=r.nextInt(30);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
