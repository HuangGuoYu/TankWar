import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client extends Frame{

    int x = 50,y = 50;
    /**
     * 启动 start the frame
     */
    public void launch() {
        this.setLocation(300, 100);
        this.setSize(800, 600);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.CYAN);
        this.setVisible(true);
        //移动线程
        new Thread(()->{
            while(true) {
                repaint();
                try {
                    Thread.sleep(50);
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

        y += 5;
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
}
