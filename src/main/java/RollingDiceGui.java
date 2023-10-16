import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.jar.JarEntry;


public class RollingDiceGui extends JFrame {
    public RollingDiceGui(){
        super("Rolling, Double Dice");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700,700));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        addGuiComponent();
    }
    private void addGuiComponent(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        //1. Banner
        JLabel bannerImg = ImgService.loadImage("resources/banner.png");
        bannerImg.setBounds(45,25,600,100);
        jPanel.add(bannerImg);

        //2. Dices
        JLabel diceOneImg = ImgService.loadImage("resources/dice1.jpeg");
        diceOneImg.setBounds(100,200,200,200);
        jPanel.add(diceOneImg);

        JLabel diceTwoImg = ImgService.loadImage("resources/dice2.png");
        diceTwoImg.setBounds(100,200,200,200);
        jPanel.add(diceTwoImg);

        JLabel diceThreeImg = ImgService.loadImage("resources/dice3.jpeg");
        diceThreeImg.setBounds(100,200,200,200);
        jPanel.add(diceThreeImg);

        JLabel diceFourImg = ImgService.loadImage("resource/dice4.png");
        diceFourImg.setBounds(100,200,200,200);
        jPanel.add(diceFourImg);

        JLabel diceFiveImg = ImgService.loadImage("resources/dice5.png");
        diceFiveImg.setBounds(100,200,200,200);
        jPanel.add(diceFiveImg);

        JLabel diceSixImg = ImgService.loadImage("resources/dice6.png");
        diceSixImg.setBounds(100,200,200,200);
        jPanel.add(diceSixImg);

        //3. Roll Button.
        Random rand = new Random();
        JButton rollButton = new JButton("Roll");
        rollButton.setBounds(250,500,200,50);
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollButton.setEnabled(false);

                //roll for 3 seconds.
                long startTime = System.currentTimeMillis();
                Thread rollThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long endTime = System.currentTimeMillis();
                        try {
                            while (endTime - startTime/100F <3){
                                //roll dice
                                int diceOne = rand.nextInt(1,7);
                                int diceTwo = rand.nextInt(1,7);

                                //update dice images.
                                ImgService.updateImage(diceOneImg, "resource/dice" + diceOne + "jpeg");
                                ImgService.updateImage(diceTwoImg, "resource/dice" + diceOne + "jpeg");

                                repaint();
                                revalidate();

                                //sleep thread
                                Thread.sleep(60);

                                endTime = System.currentTimeMillis();

                            }
                            rollButton.setEnabled(true);
                        }catch (InterruptedException e){
                            System.out.println("Threading error " + e);
                        }
                    }
                });

                rollThread.start();
            }
        });

        this.getContentPane().add(jPanel);

        }

    }

