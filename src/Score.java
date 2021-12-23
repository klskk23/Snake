import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score extends JDialog {
    public int n=0;
    public Score(GameFrame owner) {
        super(owner,"结算",false);
        Container container=getContentPane();
        this.setLayout(null);
        this.setFocusable(false);
        this.setBounds(300,200,200,200);
        JLabel score=new JLabel();
        score.setText("你的得分是"+GamePanel.u_score);
        score.setFont(GameFrame.font1);
        score.setBounds(30,0,200,50);
        container.add(score);
        JButton game_start = new JButton();

        game_start.setText("重新开始");
        game_start.setForeground(Color.red);
        game_start.setBounds(17, 100, 150, 30);
        game_start.setBackground(new Color(135, 206, 250));
        game_start.setFocusable(false);
        container.add(game_start);
        game_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sound.Btn_press();
                new GameFrame();

            }
        });
        JButton game_over = new JButton();
        game_over.setText("结束游戏");
        game_over.setForeground(Color.red);
        game_over.setBounds(17, 50, 150, 30);
        game_over.setBackground(new Color(135, 206, 250));
        game_over.setFocusable(false);
        container.add(game_over);
        game_over.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sound.Btn_press();
                System.exit(0);
            }
        });
    }

    public Score() {

    }
    public int getScore(){
        n=n+10;
        return n;
}
    public int getN() {
        return n;
    }
}
