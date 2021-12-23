public class Sound {
    //各种音效
    static final String bgm = "data/bgm.wav";
    static final String eat_beans = "data/eat_beans.wav";
    static final String game_over = "data/game_over.wav";
    static final String press_button="data/btn_press.wav";

    private static void play(String filepath, boolean cir) {//描述如何播放音乐，包括位置和是否循环
        MusicPlayer player = new MusicPlayer(filepath, cir);//创建播放器
        player.play();//start to play music
    }
    static public void Btn_press(){play(press_button,false);}
    static public void Game_over(){play(game_over,false);}
    static public void Eat_food(){play(eat_beans,false);}
    static public void BGM(){//播放bgm
        play( bgm, false);
    }
}