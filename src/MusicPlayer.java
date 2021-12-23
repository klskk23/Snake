import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer implements Runnable{
    File soundFile;//音乐文件
    Thread thread;
    boolean cir;//循环
    public MusicPlayer(String filepath,boolean cir){
        this.cir=cir;
        soundFile=new File(filepath);//文件地址
    }
    public void run(){
        byte[] auBuffer=new byte[1024*4096];//创建缓冲区
        do{
            AudioInputStream audioInputStream=null;
            SourceDataLine auline=null;
            try{
                //从文件中获取音频输入流
                audioInputStream= AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format=audioInputStream.getFormat();//获得音频格式
                DataLine.Info info=new DataLine.Info(SourceDataLine.class,format);
                auline=(SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
                auline.start();
                int byteCount=0;
                while(byteCount!=-1){
                    byteCount=audioInputStream.read(auBuffer,0,auBuffer.length);
                    if(byteCount>=0){
                        auline.write(auBuffer,0,byteCount);
                    }
                }
            }
            catch (IOException e){e.printStackTrace();}
            catch (UnsupportedAudioFileException e){e.printStackTrace();}
            catch (LineUnavailableException e){e.printStackTrace();}
            finally {
                auline.drain();
                auline.close();
            }
        }while (cir);
    }
    public void play(){
        thread=new Thread(this);
        thread.start();//开始线程
    }
    public  void stop(){
        thread.stop();//强制关闭
    }
}
