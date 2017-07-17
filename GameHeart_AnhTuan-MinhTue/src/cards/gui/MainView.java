/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cards.gui;

import cards.Deck;
import globalVariables.GlobalVariables;
import cards.network.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

/**
 *
 * @author Minh Tue
 */
public class MainView extends JFrame implements ActionListener, MouseWheelListener, Runnable {

    public Deck deck;
    private boolean gameInProgress = false;
    private JMenuBar menuBar;
    private JMenu fileMenu, tableMenu, colourSubMenu, backgroudSubMenu, serverMenu, aboutMenu, cardMenu;
    private JMenuItem fileQuitItem;
    private JMenuItem cardMenuFlipItem;
    private JMenuItem serverConnectItem;
    private JMenuItem serverCreateItem;
    private JMenuItem aboutMenuItem, helpMenuItem;
    private JRadioButtonMenuItem rBtnGreen, rBtnBlue, rBtnPink;
    private JRadioButtonMenuItem rBtnNoImgBackgroud, rBtnImageWaveItem, rBtnImageHinhNen9Item, rBtnImageSpringItem, rBtnImageFlower;
    public JLabel playerLeft, playerTop, playerRight, playerBottom;
    public JLabel playerPointsInHandTop, playerPointsInHandLeft, playerPointsInHandRight, playerPointsInHandBottom;
    public JLabel playerTextPointInHandTop, playerTextPointInHandLeft, playerTextPointInHandRight, playerTextPointInHandBottom;
    public JLabel lbDescription;
    AffineTransform at;
    Timer timer;

    public MainView() {

        super("GameHeart_0812577-0812585");
        setResizable(false);

        this.deck = new Deck();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
        fileQuitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        KeyStroke ctrlQKeyStroke = KeyStroke.getKeyStroke("control Q");
        fileQuitItem.setAccelerator(ctrlQKeyStroke);
        fileMenu.add(fileQuitItem);
        fileQuitItem.addActionListener(this);

        cardMenu = new JMenu("Card");
        cardMenu.setMnemonic(KeyEvent.VK_C);
        menuBar.add(cardMenu);
        cardMenuFlipItem = new JMenuItem("Flip", KeyEvent.VK_F);
        KeyStroke fKeyStroke = KeyStroke.getKeyStroke("F");
        cardMenuFlipItem.setAccelerator(fKeyStroke);
        cardMenu.add(cardMenuFlipItem);
        cardMenuFlipItem.addActionListener(this);

        tableMenu = new JMenu("Table");
        tableMenu.setMnemonic(KeyEvent.VK_T);
        menuBar.add(tableMenu);
        colourSubMenu = new JMenu("Backgound Colour");
        tableMenu.add(colourSubMenu);
        ButtonGroup colour = new ButtonGroup();
        rBtnGreen = new JRadioButtonMenuItem("Green");
        rBtnBlue = new JRadioButtonMenuItem("Blue");
        rBtnPink = new JRadioButtonMenuItem("Pink");
        colourSubMenu.add(rBtnGreen);
        colourSubMenu.add(rBtnBlue);
        colourSubMenu.add(rBtnPink);
        colour.add(rBtnGreen);
        colour.add(rBtnBlue);
        colour.add(rBtnPink);
        rBtnPink.setSelected(true);
        rBtnGreen.addActionListener(this);
        rBtnBlue.addActionListener(this);
        rBtnPink.addActionListener(this);
        backgroudSubMenu = new JMenu("Backgound Image");
        tableMenu.add(backgroudSubMenu);
        ButtonGroup buttonGroup = new ButtonGroup();
        rBtnNoImgBackgroud = new JRadioButtonMenuItem("No Image");
        rBtnImageFlower = new JRadioButtonMenuItem("Flower.jpg");
        rBtnImageHinhNen9Item = new JRadioButtonMenuItem("hinhnen_9.jpg");
        rBtnImageSpringItem = new JRadioButtonMenuItem("spring.jpg");
        rBtnImageWaveItem = new JRadioButtonMenuItem("wave.jpg");

        backgroudSubMenu.add(rBtnNoImgBackgroud);
        backgroudSubMenu.add(rBtnImageFlower);
        backgroudSubMenu.add(rBtnImageHinhNen9Item);
        backgroudSubMenu.add(rBtnImageSpringItem);
        backgroudSubMenu.add(rBtnImageWaveItem);

        buttonGroup.add(rBtnNoImgBackgroud);
        buttonGroup.add(rBtnImageFlower);
        buttonGroup.add(rBtnImageHinhNen9Item);
        buttonGroup.add(rBtnImageSpringItem);
        buttonGroup.add(rBtnImageWaveItem);

        rBtnNoImgBackgroud.addActionListener(this);
        rBtnImageFlower.addActionListener(this);
        rBtnImageHinhNen9Item.addActionListener(this);
        rBtnImageHinhNen9Item.setSelected(true);
        rBtnImageSpringItem.addActionListener(this);
        rBtnImageWaveItem.addActionListener(this);


        serverMenu = new JMenu("Server");
        serverMenu.setMnemonic(KeyEvent.VK_S);
        menuBar.add(serverMenu);
        serverConnectItem = new JMenuItem("Connect");
        serverCreateItem = new JMenuItem("Create server", KeyEvent.VK_C);
        KeyStroke ctrlCKeyStroke = KeyStroke.getKeyStroke("control C");
        serverCreateItem.setAccelerator(ctrlCKeyStroke);
        serverMenu.add(serverConnectItem);
        serverMenu.add(serverCreateItem);
        serverConnectItem.addActionListener(this);
        serverCreateItem.addActionListener(this);

        aboutMenu = new JMenu("Help");
        aboutMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(aboutMenu);
        helpMenuItem = new JMenuItem("Help", KeyEvent.VK_H);
        aboutMenu.add(helpMenuItem);
        helpMenuItem.addActionListener(this);
        aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutMenu.add(aboutMenuItem);
        aboutMenuItem.addActionListener(this);


        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());

        this.addWindowListener(new MyWindowListener());
        this.setBounds(100, 0, 1000, 720);

        this.setBackground(Color.pink);

        this.setContentPane(this.deck);
        playerLeft = new JLabel("Player Left");
        playerLeft.setFont(new Font(null, Font.PLAIN, 14));
        playerLeft.setBounds(40, 7, 100, 20);
        playerLeft.setHorizontalTextPosition(playerLeft.CENTER);
        playerLeft.setForeground(Color.yellow);
        this.add(playerLeft);

        playerTop = new JLabel("Player Top");
        playerTop.setFont(new Font(null, Font.PLAIN, 14));
        playerTop.setBounds(450, 7, 100, 20);
        playerTop.setHorizontalTextPosition(playerTop.CENTER);
        playerTop.setForeground(Color.yellow);
        this.add(playerTop);

        playerRight = new JLabel("Player Right");
        playerRight.setFont(new Font(null, Font.PLAIN, 14));
        playerRight.setBounds(860, 7, 100, 20);
        playerRight.setHorizontalTextPosition(playerRight.CENTER);
        playerRight.setForeground(Color.yellow);
        this.add(playerRight);

        playerBottom = new JLabel("Player Bottom");
        playerBottom.setFont(new Font(null, Font.PLAIN, 14));
        playerBottom.setBounds(190, 500, 100, 20);
        playerBottom.setHorizontalTextPosition(playerBottom.CENTER);
        playerBottom.setForeground(Color.yellow);
        this.add(playerBottom);

        playerTextPointInHandLeft = new JLabel("Points: ");
        playerTextPointInHandLeft.setFont(new Font(null, Font.PLAIN, 14));
        playerTextPointInHandLeft.setBounds(140, 300, 50, 20);
        playerTextPointInHandLeft.setHorizontalTextPosition(playerTextPointInHandLeft.CENTER);
        playerTextPointInHandLeft.setForeground(Color.BLACK);
        this.add(playerTextPointInHandLeft);

        playerTextPointInHandTop = new JLabel("Points: ");
        playerTextPointInHandTop.setFont(new Font(null, Font.PLAIN, 14));
        playerTextPointInHandTop.setBounds(450, 173, 50, 20);
        playerTextPointInHandTop.setHorizontalTextPosition(playerTextPointInHandTop.CENTER);
        playerTextPointInHandTop.setForeground(Color.BLACK);
        this.add(playerTextPointInHandTop);

        playerTextPointInHandRight = new JLabel("Points: ");
        playerTextPointInHandRight.setFont(new Font(null, Font.PLAIN, 14));
        playerTextPointInHandRight.setBounds(775, 300, 50, 20);
        playerTextPointInHandRight.setHorizontalTextPosition(playerTextPointInHandRight.CENTER);
        playerTextPointInHandRight.setForeground(Color.BLACK);
        this.add(playerTextPointInHandRight);

        playerTextPointInHandBottom = new JLabel("Points: ");
        playerTextPointInHandBottom.setFont(new Font(null, Font.PLAIN, 14));
        playerTextPointInHandBottom.setBounds(450, 490, 50, 20);
        playerTextPointInHandBottom.setHorizontalTextPosition(playerTextPointInHandBottom.CENTER);
        playerTextPointInHandBottom.setForeground(Color.BLACK);
        this.add(playerTextPointInHandBottom);

        playerPointsInHandLeft = new JLabel("0");
        playerPointsInHandLeft.setFont(new Font(null, Font.PLAIN, 14));
        playerPointsInHandLeft.setBounds(190, 300, 20, 20);
        playerPointsInHandLeft.setHorizontalTextPosition(playerPointsInHandLeft.CENTER);
        playerPointsInHandLeft.setForeground(Color.BLACK);
        this.add(playerPointsInHandLeft);

        playerPointsInHandRight = new JLabel("0");
        playerPointsInHandRight.setFont(new Font(null, Font.PLAIN, 14));
        playerPointsInHandRight.setBounds(825, 300, 20, 20);
        playerPointsInHandRight.setHorizontalTextPosition(playerPointsInHandRight.CENTER);
        playerPointsInHandRight.setForeground(Color.BLACK);
        this.add(playerPointsInHandRight);

        playerPointsInHandTop = new JLabel("0");
        playerPointsInHandTop.setFont(new Font(null, Font.PLAIN, 14));
        playerPointsInHandTop.setBounds(500, 173, 20, 20);
        playerPointsInHandTop.setHorizontalTextPosition(playerPointsInHandTop.CENTER);
        playerPointsInHandTop.setForeground(Color.BLACK);
        this.add(playerPointsInHandTop);

        lbDescription = new JLabel("Bạn phải chọn 2 chuồn!");
        lbDescription.setFont(new Font(null, Font.PLAIN, 14));
        lbDescription.setBounds(610, 210, 250, 20);
        lbDescription.setHorizontalTextPosition(lbDescription.CENTER);
        lbDescription.setForeground(Color.red);
        lbDescription.setVisible(true);
        this.add(lbDescription);

        playerPointsInHandBottom = new JLabel("0");
        playerPointsInHandBottom.setFont(new Font(null, Font.PLAIN, 14));
        playerPointsInHandBottom.setBounds(500, 490, 20, 20);
        playerPointsInHandBottom.setHorizontalTextPosition(playerPointsInHandBottom.CENTER);
        playerPointsInHandBottom.setForeground(Color.BLACK);
        this.add(playerPointsInHandBottom);


        gameInProgress = true;
        this.setVisible(true);
        this.setMaximizedBounds(new Rectangle(100, 0, 1000, 720));
        timer = new Timer();
        timer.schedule(new GameLoop(), 1000, 1000);
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (gameInProgress) {
            Object source = e.getSource();
            if (source == fileQuitItem) {
                System.exit(0);
            } else if (source == cardMenuFlipItem) {
                this.arrangeCards();
            } else if (source == rBtnGreen) {
                this.setBackground(Color.green);           
            } else if (source == rBtnBlue) {
                this.setBackground(Color.blue);
            } else if (source == rBtnPink) {
                this.setBackground(Color.pink);
            } else if (source == rBtnNoImgBackgroud) {
                    this.deck.setImage("none");
                this.repaint();
            } else if (source == rBtnImageHinhNen9Item) {
                this.deck.setImage("hinhnen_9.jpg");
                this.repaint();
            } else if (source == rBtnImageFlower) {
                this.deck.setImage("Flower.jpg");
                this.repaint();
            } else if (source == rBtnImageSpringItem) {
               this.deck.setImage("spring.jpg");
                this.repaint();
            } else if (source == rBtnImageWaveItem) {
                this.deck.setImage("wave.jpg");
                this.repaint();
            } else if (source == serverConnectItem) {
                doConnectionDialog();
            } else if (source == serverCreateItem) {
                createServer();
                System.out.println("Server Created");
            } else if (source == helpMenuItem) {
                JOptionPane.showMessageDialog(null, "Xem thêm trong file \"Huong dan su dung.doc\"", "Help", JOptionPane.INFORMATION_MESSAGE);
            } else if (source == aboutMenuItem) {
                JOptionPane.showMessageDialog(null, "GameHeart_0812577-0812585\nVersion 1.0\n0812577-Lê Anh Tuấn\n0812585-Nguyễn Minh Tuệ", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
    }

    public void run() {
        arrangeCards();
        update();
        GlobalVariables.played = deck.updateCardPlayed();
        playerBottom.setText(GlobalVariables.nameBottomPlayer);
        playerLeft.setText(GlobalVariables.nameLeftPlayer);
        playerRight.setText(GlobalVariables.nameRightPlayer);
        playerTop.setText(GlobalVariables.nameTopPlayer);
    }

    class GameLoop extends TimerTask {

        @Override
        public void run() {
            arrangeCards();
            update();
            GlobalVariables.played = deck.updateCardPlayed();
            playerBottom.setText(GlobalVariables.nameBottomPlayer);
            playerLeft.setText(GlobalVariables.nameLeftPlayer);
            playerRight.setText(GlobalVariables.nameRightPlayer);
            playerTop.setText(GlobalVariables.nameTopPlayer);
        }
    }

    private void newGame() {
        this.setBounds(100, 0, 1000, 720);
        this.setBackground(Color.green);
        this.deck = new Deck();
        this.setContentPane(this.deck);
        gameInProgress = true;
        this.setVisible(true);
        repaint();
    }

    private void createServer() {
        if (GlobalVariables.server != null) {
            JOptionPane.showMessageDialog(this,
                    "You're a server now!!!", "Cannot create server!!!",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            GlobalVariables.server = new Server();
            GlobalVariables.server.setMainView(this);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Cannot create server!!! \n" + ex.toString(),
                    "Failed!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Server created!!!",
                "Successfull!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void doConnectionDialog() {
        if (GlobalVariables.client != null) {
            JOptionPane.showMessageDialog(this, "You are a client now!!!", "Cannot connect to server!!!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            ConnectServer cServer = new ConnectServer(this);
            cServer.setMainView(this);
            cServer.setLocation(320, 250);
            cServer.setVisible(true);
        }
    }

    public void dealingCards() {
        this.deck.dealingCards();
    }

    public void arrangeCards() {
        if (GlobalVariables.startDealCards == true) {
            this.deck.arrangeCardsFollowID(GlobalVariables.cardsInfo);
            lbDescription.setVisible(true);
            GlobalVariables.startDealCards = false;
        }
    }

    public void update() {


        if (GlobalVariables.PlayerName.equals(GlobalVariables.nameBottomPlayer)) {
            if (GlobalVariables.firstClick == false) {
                lbDescription.setVisible(false);
            }
            playerPointsInHandBottom.setVisible(true);
            playerPointsInHandLeft.setVisible(false);
            playerPointsInHandTop.setVisible(false);
            playerPointsInHandRight.setVisible(false);

            playerTextPointInHandBottom.setVisible(true);
            playerTextPointInHandLeft.setVisible(false);
            playerTextPointInHandTop.setVisible(false);
            playerTextPointInHandRight.setVisible(false);

        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameLeftPlayer)) {
            if (GlobalVariables.firstClick == false) {
                lbDescription.setVisible(false);
            }
            playerPointsInHandBottom.setVisible(false);
            playerPointsInHandLeft.setVisible(true);
            playerPointsInHandTop.setVisible(false);
            playerPointsInHandRight.setVisible(false);

            playerTextPointInHandBottom.setVisible(false);
            playerTextPointInHandLeft.setVisible(true);
            playerTextPointInHandTop.setVisible(false);
            playerTextPointInHandRight.setVisible(false);
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameTopPlayer)) {
            if (GlobalVariables.firstClick == false) {
                lbDescription.setVisible(false);
            }
            playerPointsInHandBottom.setVisible(false);
            playerPointsInHandLeft.setVisible(false);
            playerPointsInHandTop.setVisible(true);
            playerPointsInHandRight.setVisible(false);

            playerTextPointInHandBottom.setVisible(false);
            playerTextPointInHandLeft.setVisible(false);
            playerTextPointInHandTop.setVisible(true);
            playerTextPointInHandRight.setVisible(false);
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameRightPlayer)) {
            if (GlobalVariables.firstClick == false) {
                lbDescription.setVisible(false);
            }
            playerPointsInHandBottom.setVisible(false);
            playerPointsInHandLeft.setVisible(false);
            playerPointsInHandTop.setVisible(false);
            playerPointsInHandRight.setVisible(true);

            playerTextPointInHandBottom.setVisible(false);
            playerTextPointInHandLeft.setVisible(false);
            playerTextPointInHandTop.setVisible(false);
            playerTextPointInHandRight.setVisible(true);
        }
        if (GlobalVariables.cannotClick == true) {
            lbDescription.setVisible(true);
            lbDescription.setText("Bạn không được đánh quân bài này!");
        }
        if (GlobalVariables.cardChaged == true) {
            int iCardIndex = Integer.parseInt(GlobalVariables.cardIsPlayed);
            this.deck.moveCard(iCardIndex);
            if (this.deck.is2Club(iCardIndex) == true) {
                GlobalVariables.firstClick = false;
            }
        }
        if (GlobalVariables.winnerIndex != -1) {
            this.deck.setWinnnerHand(GlobalVariables.winnerIndex);
            switch (GlobalVariables.nextPlayer) {
                case 11:
                    playerPointsInHandLeft.setText(GlobalVariables.PointsLeft + "");
                    playerPointsInHandLeft.setForeground(Color.red);
                    break;
                case 12:
                    playerPointsInHandTop.setText(GlobalVariables.PointsTop + "");
                    playerPointsInHandTop.setForeground(Color.red);
                    break;
                case 13:
                    playerPointsInHandRight.setText(GlobalVariables.PointsRight + "");
                    playerPointsInHandRight.setForeground(Color.red);
                    break;
                case 14:
                    playerPointsInHandBottom.setText(GlobalVariables.PointsBottom + "");
                    playerPointsInHandBottom.setForeground(Color.red);
                    break;
            }
            GlobalVariables.winnerIndex = -1;
        }

        if (GlobalVariables.remove == true) {
            GlobalVariables.iEndCardIndexPlayerLeft--;
            GlobalVariables.iStartCardIndexPlayerTop--;
            GlobalVariables.iEndCardIndexPlayerTop -= 2;
            GlobalVariables.iStartCardIndexPlayerRight -= 2;
            GlobalVariables.iEndCardIndexPlayerRight -= 3;
            GlobalVariables.iStartCardIndexPlayerBottom -= 3;
            GlobalVariables.iEndCardIndexPlayerBottom -= 4;
            this.deck.removeCards(GlobalVariables.listCardBeRemoved);
            GlobalVariables.listCardBeRemoved = new int[4];
            GlobalVariables.remove = false;
            GlobalVariables.cardChaged = false;
        }

    }

    public class MyWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
