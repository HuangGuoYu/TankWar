import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client extends Frame{

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final double PACE = 5;
    int x = 50,y = 50;
    /**
     * 启动 start the frame
     */
    public void launch() {
        this.setLocation(300, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.CYAN);
        this.addKeyListener(new KeyMonitor());
        this.setVisible(true);
        //移动线程
        new Thread(()->{
            while(true) {
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * 重画，复写的父类方法，自动调用
     * @param g
     */
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(c);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.launch();
    }
    /**
     * 重画线程
     */
    private class RepaintThread implements Runnable{

        public void run() {
            while(true) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode(); //获得当前的键代码
            switch (key) {
                case KeyEvent.VK_LEFT:
                    x -= PACE;
                    break;
                case KeyEvent.VK_UP:
                    y -= PACE;
                    break;
                case KeyEvent.VK_RIGHT:
                    x += PACE;
                    break;
                case KeyEvent.VK_DOWN:
                    y += PACE;
                    break;
            }

        }
    }
}
