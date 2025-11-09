package minigame;

/**
 *
 * @author Sodium
 */
//-1 день следования pep8 
import com.sun.tools.javac.Main;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class MiniGame extends JFrame{
//    задание переменных
    
    private static MiniGame game;
    
    private static Image[] src = new Image[5];
    private static int[] zone = new int[4];
//    private static Dimond[] dimonds = new Dimond[4];
    
    
    private static int maxSrc = 3;
    private static int curSrc = 1;
    public static void main(String[] args) throws IOException {
        
        game = new MiniGame();

        String texture = "src" + curSrc;
        src[0] = ImageIO.read(MiniGame.class.getResourceAsStream(texture + "/back.png"));
        src[1] = ImageIO.read(MiniGame.class.getResourceAsStream(texture + "/1.png"));
        src[3] = ImageIO.read(MiniGame.class.getResourceAsStream(texture + "/2.png"));
        src[2] = ImageIO.read(MiniGame.class.getResourceAsStream(texture + "/thing.png"));
        src[4] = ImageIO.read(MiniGame.class.getResourceAsStream(texture + "/thing.png"));
        
        

        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setLocation(200, 50);
        game.setSize(848, 480);
        game.setResizable(true);
//        game.setVisible(true);
        game.setTitle("Picker");
        
        GameFif gf = new GameFif();
        game.add(gf);
        game.setVisible(true);
    }
    
    
    
//получает размер изображения с учётом процентика    
    public static int[] getISize(JPanel window, Image img, double percent){
        int imgw = img.getWidth(null);
        int imgh = img.getHeight(null);


        double perw = (double)window.getWidth() / imgw;
        double perh = (double)window.getHeight() / imgh; 
        
        double scale = min(perw, perh);   
        scale *= percent / 100.0;
        
        
        int wid = (int) (imgw * scale);
        int hei = (int) (imgh * scale);
        return new int[]{wid, hei};
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//рисует изображения по заданым параметрам, убрать потом мусор    
    public static void Paint(Graphics g, Image img, int x, int y, double percent, JPanel window){
//        g.fillOval(x, y, 100, 100;
//        int ww = this.getWidth();
//        int hw = this.getHeight();
        
//        int wid = (int) (620 / 100 * percent);
//        int hei = (int) (480 / 100 * percent);


        int[] wh = getISize(window, img, percent);

        zone[0] = x;
        zone[1] = y;
        zone[2] = wh[0];
        zone[3] = wh[1];
//        int wid = (int) src[index].getWidth(null) / 100 * percent;
//        int hei = (int) src[index].getHeight(null) / 100 * percent;

        
        
        g.drawImage(img, x, y, wh[0], wh[1], null);
//чекбокс если что машинки
//        g.drawRect(zone[0], zone[1], zone[2], zone[3]);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//получает текущее расположение    
    public static int[] getCur(JPanel window, String wtf, Image img, double per){
        
        int[] wh = getISize(window, img, per);
        
        
                
                
        if ("center".equals(wtf)){
            int x = (window.getWidth() / 2  - wh[0] / 2);
            int y = (window.getHeight() / 2 - wh[1] / 2);

            return new int[]{x, y};
        }
        else return new int[]{-1, -1};
    }
    
   
//инициализируем графику    
    public static class GameFif extends JPanel{
        private Timer minitimer; 
        private int counter = 0;
        private int oldcounter = 10;
        private int animateCar = 0;
        private List<Diamond> diamonds = new ArrayList<>();
        
        
        private Random rnd = new Random();
        
//        под анимацию
        private boolean action = false;
        
        private Timer timerD;
//        private long curtime = System.nanoTime();
        private int gh = 60;
        private int frame = 0;
        private int dimondframe = 0;
        
        private double curstep = 1.0;
        private int beasy = 0;
        private int chckpoint = 10;
        

        
private boolean turn = true;        
private boolean yesno = true;    
private Timer timer;
 public GameFif(){
     
     //таймер для алмазов
        timer = new Timer(1, e -> {
            updateDiamonds();
    // анимация для машинки
            if (animateCar == -1){
                frame = 0;
                curstep = 1.0;
                animateCar = 0;
            }
            else if(animateCar == 1)
            {
                frame++;
                double p = frame / (double) gh; // 0..1
//                цикличная функция
//                curstep = 1.0 - 0.1 * Math.sin(Math.PI * p);
                curstep -= 0.001 * 2;
                if (frame >= gh) {
                    animateCar = 0;
                    frame = 0;
                    curstep = 1.0;
                }
            }
            repaint();
        });
        timer.start();
     
     
     addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();

                    
                    if (mouseX >= 0 && mouseX <= 50 &&
                        mouseY >= 0 && mouseY <= 50){
                        try {

                            curSrc++;        
                            String texture = "src" + curSrc;
                            src[0] = ImageIO.read(MiniGame.class.getResourceAsStream(texture + "/back.png"));
                            src[1] = ImageIO.read(MiniGame.class.getResourceAsStream(texture + "/1.png"));
                            src[3] = ImageIO.read(MiniGame.class.getResourceAsStream(texture + "/2.png"));
                            src[2] = ImageIO.read(MiniGame.class.getResourceAsStream(texture + "/thing.png"));
                            src[4] = ImageIO.read(MiniGame.class.getResourceAsStream(texture + "/thing.png"));
                            
                            if (curSrc == maxSrc){
                                curSrc = 0;
                            }
                            
                        } catch (IOException ex) {
                            System.getLogger(MiniGame.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }
                    }
                    
                    
                    
//указываем чекбоксы
    //                int x1 = 0, y1 = 0, w = 848, h = 480;
    //                zone = x y w h
                    if (mouseX >= zone[0] && mouseX <= zone[0] + zone[2] &&
                        mouseY >= zone[1] && mouseY <= zone[1] + zone[3]) {
//                        AnimeteThis();



                        if (animateCar == 1){
                            animateCar = -1;
                        }
                        else animateCar = 1;

//                        filtr = true;
//                        while(filtr){
//                            AnimeteThis();
//                        }
                        
                        counter++;
//                        repaint();

                        if (counter == chckpoint){
                            if (turn){
                                src[4] = src[1]; 
                                src[1] = src[3];
                                turn = false;
                            }
                            else{
                                src[3] = src[1]; 
                                src[1] = src[4];
                                turn = true;
                            }

                            
                            for(int rh = 0; rh <= chckpoint; rh++){
                                Diamond neva = new Diamond(rnd.nextInt(getWidth() - 50), getHeight(), 50, 50, 1 + rnd.nextDouble() * 2);
                                diamonds.add(neva);      
                                
                            }
                            chckpoint += 100;
                        }

                        Diamond neva = new Diamond(rnd.nextInt(getWidth() - 50), getHeight(), 50, 50, 1 + rnd.nextDouble());
                        diamonds.add(neva);
                    }

                }
            });
     
 
 }       
                    
        
        
        
        
        
        
        
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
//            int width = this.getWidth();
//получаем цент для текущего объекта
//            int[] xy = getCur(this, "center", src[1], 50);
//            int[] xyf = getCur(this, "center", src[2], 50);

//это задний фон типо типо
            Paint(g, src[0], 0, 0, 110, this);
            
            
//тут условия для анимации
            double percent = 50.0 * curstep;
//            double step = (double) (percent / 10) / gh;  
//            double curstep = percent;
            if (action){ 
                int[] xy = getCur(this, "center", src[1], percent - curstep);
                Paint(g, src[1], xy[0], xy[1], percent - curstep, this);
//                if (counter > oldcounter){
//                    finallyDimond = true;
////                    DimondTime();
//                }
//                if (finallyDimond){
//                    oldcounter += 10;
////                    Random random = new Random();
////                    int rand = random.nextInt(xy[0], xy[0] + 20);
////                      dimonds[0] = new Diamond(0.0, 0.0, 100.0, 50.0);
////                      dimonds[0].move();
//                    Paint(g, src[2], xy[0], xy[1] + beasy, 50.0, this);
//                }

                
//                g.drawRect(zone[0], zone[1], zone[2], zone[3]);
//                curstep -= step;
            }
            else{
                int[] xy = getCur(this, "center", src[1], percent);
                
                Paint(g, src[1], xy[0], xy[1], percent, this);
//                g.drawRect(zone[0], zone[1], zone[2], zone[3]);
            }
//            Paint(g, src[2], xyf[0], xyf[1], 50, this);

            for (Diamond d : diamonds){
//            int[] whx = getISize(this, src[2], 1 + rnd.nextDouble() * 10);
//            Graphics g, JPanel panel, Image img
              d.draw(g, this, src[2]);
            }
            

        // рисуем счётчик вытыкивавкивней
            g.setColor(java.awt.Color.black);
            g.setFont(new java.awt.Font("Verdana", java.awt.Font.BOLD, 24));
            g.drawString("Тыканей: " + counter, 20, 40);
//            curtime = System.nanoTime();
//            g.drawString("" + curtime, 20, 160);

//        }
   
// нажатия мышки       
//        public GameFif() {
        




        }
        
        
        
//private boolean filtr = true;     
//private long oldtime;
//таймер надо не забыть его сделать более по человечески        
        private void AnimeteThis(){
            action = true;
            frame = 0;
            curstep = 1.0;
            if (timer != null && timer.isRunning()){
                timer.stop();
            }
            
//            if (filtr){
//                curtime = System.nanoTime();
//                oldtime = curtime;
//                filtr = false;
//            }
//            else if(oldtime + 00000500000000 > System.nanoTime()){
//                repaint();
//                frame++;
//                curstep -= 0.001 * 2;
//                if (frame >= gh){
//                    curstep = 1.0;
//                    action = false;  
//            }
//            }    
//                else{
//                    action = true;
//                    filtr = false;
//                }

            
            timer = new Timer(1, new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    repaint();
                    frame++;
                    curstep -= 0.001 * 2;

                    if (frame >= gh){
                        curstep = 1.0;
                        
                        action = false;
                        timer.stop();
                    }
                }
            });
            timer.start();
        }
//        
//        private void DimondTime(){
////            action = true;
//                                
//            dimondframe = 0;
//            beasy = 1;
//            curstep = 1.0;
//            if (timerD != null && timerD.isRunning()){
//                timerD.stop();
//            }
//            
//            
//                timerD = new Timer(50, new ActionListener(){
//                public void actionPerformed(ActionEvent e){
//                    repaint();
//                    frame++;
////                    curstep -= 0.001 * 2;
//                    beasy += 10;
//                    if (frame >= gh){
////                        curstep = 1.0;
//                        beasy = 1;
//                        finallyDimond = false;
//                        timerD.stop();
//                    }
//                }
//            });
//            timerD.start();
//        }
        
        
        private void updateDiamonds() {
            Iterator<Diamond> iter = diamonds.iterator();
            while (iter.hasNext()) {
                Diamond d = iter.next();
                d.update();
                if (d.y < 0) iter.remove(); // удаляем, если ушёл за верх
            }
        }
        
       
        

        
        
    }
}
