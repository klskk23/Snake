import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameFrame extends JFrame {
    private boolean stop=false;//弹出结算面板
    private Timer timer;
    GamePanel gamePanel;
    Score score;
    //public Timer timer0;
    boolean stop0=true;
    private boolean bgm_control_jud = true;//防止打开多个bgm;
    public static Font font1 = new Font("宋体", Font.BOLD, 20);//定义字体1

    public GameFrame() throws HeadlessException {
        Container container = getContentPane();

        //设置窗体属性
        this.setTitle("贪吃蛇 BY 废旧螺栓机甲");
        this.setSize(640, 690);
        this.setLocation(100, 100);
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置键盘监听
        this.keyEvent();
        //开始按钮,重置游戏
        JButton game_start = new JButton();
        game_start.setText("继续游戏");
        game_start.setForeground(Color.red);
        game_start.setFont(font1);
        game_start.setBounds(0, 0, 150, 30);
        game_start.setBackground(new Color(135, 206, 250));
        game_start.setFocusable(false);
        container.add(game_start);

        //BGM操控
        JButton bgm_control = new JButton();
        bgm_control.setText("背景音乐");
        bgm_control.setForeground(Color.yellow);
        bgm_control.setFont(font1);
        bgm_control.setBounds(300, 0, 150, 30);
        bgm_control.setBackground(new Color(100, 149, 237));
        bgm_control.setFocusable(false);
        container.add(bgm_control);
        bgm_control.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sound.Btn_press();
                if (bgm_control_jud) {
                    bgm_control_jud = false;
                    Sound.BGM();
                }
            }
        });
        //暂停按钮
        JButton stop_game = new JButton();
        stop_game.setText("暂停游戏");
        stop_game.setBounds(150, 0, 150, 30);
        stop_game.setForeground(Color.orange);
        stop_game.setFont(font1);
        stop_game.setBackground(new Color(147, 112, 219));
        stop_game.setFocusable(false);
        container.add(stop_game);

        //计分板按钮
        JButton score_get = new JButton();
        score_get.setText("重新开始，结束和得分");//等待补充分数
        score_get.setBounds(450, 0, 180, 30);
        score_get.setForeground(Color.MAGENTA);
        score_get.setFont(new Font("宋体",Font.BOLD,14));
        score_get.setBackground(new Color(238, 232, 170));
        container.add(score_get);
        score_get.setFocusable(false);

        //JPanel对象
        gamePanel = new GamePanel(null);
        container.add(gamePanel);
        game_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sound.Btn_press();
                if(!stop0){
                    stop0=true;gamePanel.stop=false;
                }
            }
        });
        stop_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sound.Btn_press();
                if(stop0){stop0=false;gamePanel.stop=true;}
            }
        });
        score_get.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sound.Btn_press();
                GameFrame.this.end();
            }
        });
        timer=new Timer();
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                if(!stop)if(!gamePanel.snake.isLive){end();stop=true;}
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 100);
    }
//键盘监听
    private void keyEvent() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
        if(gamePanel.snake.getDirection()!=Direction.DOWN)gamePanel.snake.setDirection(Direction.UP);
        break;
    case KeyEvent.VK_DOWN:
        if(gamePanel.snake.getDirection()!=Direction.UP)gamePanel.snake.setDirection(Direction.DOWN);
        break;
    case KeyEvent.VK_LEFT:
        if(gamePanel.snake.getDirection()!=Direction.RIGHT)gamePanel.snake.setDirection(Direction.LEFT);
        break;
    case KeyEvent.VK_RIGHT:
        if(gamePanel.snake.getDirection()!=Direction.LEFT)gamePanel.snake.setDirection(Direction.RIGHT);
        break;
}

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }
    //结算
    public  int end(){
        score=new Score(GameFrame.this);
        score.setVisible(true);
        return score.getScore();
    }
    public static void main(String[]args){
        new GameFrame();
    }
}
