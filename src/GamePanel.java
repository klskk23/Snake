import javax.swing.*;
import java.awt.*;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel{
    public Score score;
    public static int u_score;
    public boolean game_over=false;
    public boolean stop=true;
    private Timer timer;
    public Snake snake;
    private boolean repeat=false;//判断食物是否和蛇身体重合,默认不重合
    Image img;
    Food food;
    public GamePanel(LayoutManager layout) {
        super(layout);
            score = new Score();
            food = new Food(18, 18);
            snake = new Snake();
            this.setBounds(0, 30, 640, 640);
            //添加背景图像
            img = new ImageIcon("data/background.png").getImage();
                stop=false;
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {

                @Override
                public void run() {
                    if (!stop) {
                        snake.move();
                        if (!snake.isLive && !game_over) {
                            Sound.Game_over();
                            game_over = true;
                        }//结束音效
                        if (!repeat) {
                            //吃食物判断
                            Node food0 = new Node(food.getX(), food.getY());
                            Node head = snake.getBody().getFirst();
                            if (head.getX() == food0.getX() && head.getY() == food0.getY()) {
                                snake.eat(food);
                                food = new Food();
                                Sound.Eat_food();
                                score.getScore();
                                u_score = score.getN();
                            }
                        } else {
                            food = new Food();

                        }
                        //食物是否和蛇身体重合判断
                        for (int i = 1; i < snake.body.size(); i++) {
                            Node node = snake.body.get(i);
                            if (food.getX() == node.getX() && food.getY() == node.getY()) repeat = true;
                            else repeat = false;
                        }
                        repaint();
                    }
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 200);
        }


    @Override
    public void paint(Graphics g) {
        //背景图绘制
        Graphics2D g2=(Graphics2D) g;
        g2.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);
        //网格线
        g.setColor(Color.BLACK);
        for (int i = 0; i <31; i++) {
            g.drawLine(0, 20 * i, 600, 20 * i);
            g.drawLine(20 * i, 0, 20 * i, 600);
        }
        //绘制snake
        LinkedList<Node>body=snake.getBody();
        g.setColor(Color.orange);
        for(Node node:body){
            g.fillRoundRect(node.getX()*20, node.getY()*20,20,20,50,50);
        }
        g.fillRoundRect(snake.body.getFirst().getX()*20, snake.body.getFirst().getY()*20,25,25,100,100);
        //绘制food
        g.setColor(Color.GREEN);
        g.fillRoundRect(food.getX()*20,food.getY()*20,20,20,50,50);
        //绘制分数
        g2.setColor(Color.pink);

        Font font0=new Font("宋体",Font.BOLD,15);
        g2.setFont(font0);
        g2.drawString("当前得分"+String.valueOf(u_score),520,20);
        Font font1=new Font("宋体",Font.BOLD,40);
        g2.setFont(font1);
        if(game_over){
            g2.drawString("游戏结束",200,300);
            g2.setFont(font0);
            g2.drawString("最终得分"+String.valueOf(u_score),200,360);
        }
    }
}
