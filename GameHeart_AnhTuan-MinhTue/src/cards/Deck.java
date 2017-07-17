/*
 * To change this template, choose Tools | Templates
 * and open the template fis the editor.
 */
package cards;

import globalVariables.GlobalVariables;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Minh Tue
 */
public class Deck extends JComponent implements MouseListener, MouseMotionListener {

    public static final String IMGDECK_PATH = "/resources/img/";
    public static final String IMGBG_PATH = "/resources/background/";
    private ArrayList<Card> cards;
    private ArrayList<Card> playerCardsLeft;
    private ArrayList<Card> playerCardsTop;
    private ArrayList<Card> playerCardsRight;
    private ArrayList<Card> playerCardsBottom;
    private Card selectedCards = null;
    private ImageIcon backgroundImage = null;
    private boolean khoi_tao1 = true;
    private boolean khoi_tao2 = true;
    private boolean khoi_tao3 = true;
    private boolean dealed = false;
    private boolean played = false;
    public String cardIndexPlayed = "";
    public static int leftPlayFirst = 1;
    public static int rightPlayFirst = 2;
    public static int topPlayFirst = 3;
    public static int bottomPlayFirst = 4;
    public static int PlayerLeftPlay = 11;
    public static int PlayerTopPlay = 12;
    public static int PlayerRightPlay = 13;
    public static int PlayerBottomPlay = 14;
    //public int nextPlayer;

    public Deck() {
        super();
        cards = new ArrayList<Card>();
        playerCardsLeft = new ArrayList<Card>();
        playerCardsTop = new ArrayList<Card>();
        playerCardsRight = new ArrayList<Card>();
        playerCardsBottom = new ArrayList<Card>();
        setDealed(false);
        setPlayed(false);
        int id = 0;
        for (int iLoaiBai = 1; iLoaiBai < 5; ++iLoaiBai) // Cơ, rô, chuồn, bích
        {
            for (int jConBai = 2; jConBai < 15; ++jConBai) {// Ace, 2, 3, 4,...,Queen, King
                String pathImgOpen = IMGDECK_PATH + jConBai + " " + iLoaiBai + ".jpg";
                URL imgURLOpen = this.getClass().getResource(pathImgOpen);
                String pathImgClose = IMGDECK_PATH + "bgCardClose.jpg";
                URL imgURLClose = this.getClass().getResource(pathImgClose);
                ImageIcon imgIconOpen = new ImageIcon(imgURLOpen);
                ImageIcon imgIconClose = new ImageIcon(imgURLClose);
                Card card = new Card(imgIconOpen, imgIconClose, id, jConBai + " " + iLoaiBai);
                card.setLocation(450, 250);
                card.setBorder(null);
                cards.add(card);
                id++;
            }
        }

        this.setBG();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void addCards(Card c, int iLoaiBai) {
        switch (iLoaiBai) {
            case 1:
                playerCardsBottom.add(c);
                break;
            case 2:
                playerCardsLeft.add(c);
                break;
            case 3:
                playerCardsTop.add(c);
                break;
            case 4:
                playerCardsRight.add(c);
                break;
        }
    }

    public void arrangeCardsFollowID(String cardsInfo) {
        String[] cardsIndex = cardsInfo.split(" ");
        int index = 0;
        do {
            for (int i = index; i < 52; ++i) {

                int cardIndex = Integer.parseInt(cardsIndex[index]);
                if (cards.get(i).getCardID() == cardIndex) {
                    Card c = cards.get(i);
                    cards.set(i, cards.get(index));
                    cards.set(index, c);
                    index++;
                    break;
                }
            }
        } while (index < 52);
        updateCardsLocation();
        openMyCard();
        this.repaint();
    }

    public void dealingCards() {
        if (isDealed() == false) {
            for (int i = 0; i < 52; ++i) {
                Random r = new Random();
                int vitri1 = r.nextInt(cards.size() - 2);
                int vitri2 = r.nextInt(cards.size() - 2);

                Card temp = cards.get(vitri2);
                cards.set(vitri2, cards.get(vitri1));
                cards.set(vitri1, temp);
            }
            this.repaint();
            setDealed(true);
        }
    }

    public void updateCardsLocation() {
        int xPos = 30;
        int yPos = 30;
        for (int i = 0; i < cards.size(); ++i) {
            if (i < 13) {
                cards.get(i).setLocation(xPos, yPos);
                yPos += 35;
                playerCardsLeft.add(cards.get(i));
            } else if (13 <= i && i < 26) {
                if (khoi_tao1) {
                    xPos = 200;
                    yPos = 30;
                    khoi_tao1 = false;
                }
                cards.get(i).setLocation(xPos, yPos);
                xPos += 40;
                playerCardsTop.add(cards.get(i));
            } else if (26 <= i && i < 39) {
                if (khoi_tao2) {
                    xPos = 850;
                    yPos = 30;
                    khoi_tao2 = false;
                }
                cards.get(i).setLocation(xPos, yPos);
                yPos += 35;
                playerCardsRight.add(cards.get(i));
            } else {
                if (khoi_tao3) {
                    xPos = 140;
                    yPos = 520;
                    khoi_tao3 = false;
                }
                cards.get(i).setLocation(xPos, yPos);
                xPos += 50;
                playerCardsBottom.add(cards.get(i));
            }
        }
        this.repaint();
    }

    public void openMyCard() {
        if (GlobalVariables.PlayerName.equals(GlobalVariables.nameBottomPlayer)) {
            for (int i = 39; i < 52; ++i) {
                cards.get(i).setIsOpened(true);
            }
            this.repaint();
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameLeftPlayer)) {
            for (int i = 0; i < 13; ++i) {
                cards.get(i).setIsOpened(true);
            }
            this.repaint();
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameTopPlayer)) {
            for (int i = 13; i < 26; ++i) {
                cards.get(i).setIsOpened(true);
            }
            this.repaint();
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameRightPlayer)) {
            for (int i = 26; i < 39; ++i) {
                cards.get(i).setIsOpened(true);
            }
            this.repaint();
        }

    }

    public boolean kiemTraCungLoai() {
        boolean kq = false;
        String[] _strTam = cards.get(GlobalVariables.indexCardOrigin).getCardName().split(" ");
        int iLoaiBaiDanhDauTien = Integer.parseInt(_strTam[1]);
        if (GlobalVariables.PlayerName.equals(GlobalVariables.nameBottomPlayer)) {
            for (int i = GlobalVariables.iStartCardIndexPlayerBottom; i < GlobalVariables.iEndCardIndexPlayerBottom; ++i) {
                String[] _str = cards.get(i).getCardName().split(" ");
                if (iLoaiBaiDanhDauTien == Integer.parseInt(_str[1])) {
                    kq = true;
                    break;
                }
            }
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameLeftPlayer)) {
            for (int i = 0; i < GlobalVariables.iEndCardIndexPlayerLeft; ++i) {
                String[] _str = cards.get(i).getCardName().split(" ");
                if (iLoaiBaiDanhDauTien == Integer.parseInt(_str[1])) {
                    kq = true;
                    break;
                }
            }
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameTopPlayer)) {
            for (int i = GlobalVariables.iStartCardIndexPlayerTop; i < GlobalVariables.iEndCardIndexPlayerTop; ++i) {
                String[] _str = cards.get(i).getCardName().split(" ");
                if (iLoaiBaiDanhDauTien == Integer.parseInt(_str[1])) {
                    kq = true;
                    break;
                }
            }
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameRightPlayer)) {
            for (int i = GlobalVariables.iStartCardIndexPlayerRight; i < GlobalVariables.iEndCardIndexPlayerRight; ++i) {
                String[] _str = cards.get(i).getCardName().split(" ");
                if (iLoaiBaiDanhDauTien == Integer.parseInt(_str[1])) {
                    kq = true;
                    break;
                }
            }
        }
        return kq;
    }

    public boolean chonConBaiCungLoai(int index, int iNextPlayer) {
        boolean kq = false;
        String[] _strTam = cards.get(GlobalVariables.indexCardOrigin).getCardName().split(" ");
        int iLoaiBaiDanhDauTien = Integer.parseInt(_strTam[1]);
        String[] _str = cards.get(index).getCardName().split(" ");
        if (iLoaiBaiDanhDauTien == Integer.parseInt(_str[1])) {
            switch (iNextPlayer) {
                case 11:
                    kq = moveCardLeftToCenter(index);
                    break;
                case 12:
                    kq = moveCardTopToCenter(index);
                    break;
                case 13:
                    kq = moveCardRightToCenter(index);
                    break;
                case 14:
                    kq = moveCardBottomToCenter(index);
                    break;
            }
        }
        return kq;
    }

    public boolean chonConBaiLoaiKhac(int index, int iNextPlayer) {
        boolean kq = false;
        String[] _str = cards.get(index).getCardName().split(" ");
        if (1 != Integer.parseInt(_str[1])) {
            switch (iNextPlayer) {
                case 11:
                    kq = moveCardLeftToCenter(index);
                    break;
                case 12:
                    kq = moveCardTopToCenter(index);
                    break;
                case 13:
                    kq = moveCardRightToCenter(index);
                    break;
                case 14:
                    kq = moveCardBottomToCenter(index);
                    break;
            }
        }
        return kq;
    }

    public boolean daHetCacLoaiBaiKhacCo() {
        boolean kq = true;
        if (GlobalVariables.PlayerName.equals(GlobalVariables.nameBottomPlayer)) {
            for (int i = GlobalVariables.iStartCardIndexPlayerBottom; i < GlobalVariables.iEndCardIndexPlayerBottom; ++i) {
                String[] _str = cards.get(i).getCardName().split(" ");
                if (1 != Integer.parseInt(_str[1])) {
                    kq = false;
                    break;
                }
            }
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameLeftPlayer)) {
            for (int i = 0; i < GlobalVariables.iEndCardIndexPlayerLeft; ++i) {
                String[] _str = cards.get(i).getCardName().split(" ");
                if (1 != Integer.parseInt(_str[1])) {
                    kq = false;
                    break;
                }
            }
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameTopPlayer)) {
            for (int i = GlobalVariables.iStartCardIndexPlayerTop; i < GlobalVariables.iEndCardIndexPlayerTop; ++i) {
                String[] _str = cards.get(i).getCardName().split(" ");
                if (1 != Integer.parseInt(_str[1])) {
                    kq = false;
                    break;
                }
            }
        } else if (GlobalVariables.PlayerName.equals(GlobalVariables.nameRightPlayer)) {
            for (int i = GlobalVariables.iStartCardIndexPlayerRight; i < GlobalVariables.iEndCardIndexPlayerRight; ++i) {
                String[] _str = cards.get(i).getCardName().split(" ");
                if (1 != Integer.parseInt(_str[1])) {
                    kq = false;
                    break;
                }
            }
        }
        return kq;
    }

    public void removeCards(int[] cardsIndex) {
        ArrayList<Card> cardRemove = new ArrayList<Card>();
        for (int i = 0; i < cardsIndex.length; ++i) {
            cardRemove.add(cards.get(cardsIndex[i]));
        }
        for (int i = 0; i < cardRemove.size(); ++i) {
            for (int j = 0; j < cards.size(); ++j) {
                if (cards.get(j).getCardID() == cardRemove.get(i).getCardID()) {
                    cards.remove(j);
                    this.repaint();
                    break;
                }
            }
        }

    }

    public void moveCard(int i) {
        if (0 <= i && i < GlobalVariables.iEndCardIndexPlayerLeft) {
            moveCardLeftToCenter(i);
        } else if (GlobalVariables.iStartCardIndexPlayerTop <= i && i < GlobalVariables.iEndCardIndexPlayerTop) {
            moveCardTopToCenter(i);
        } else if (GlobalVariables.iStartCardIndexPlayerRight <= i && i < GlobalVariables.iEndCardIndexPlayerRight) {
            moveCardRightToCenter(i);
        } else if (GlobalVariables.iStartCardIndexPlayerBottom <= i && i < GlobalVariables.iEndCardIndexPlayerBottom) {
            moveCardBottomToCenter(i);
        }
    }

    public boolean moveCardBottomToCenter(int i) {
        if (GlobalVariables.iStartCardIndexPlayerBottom <= i && i < GlobalVariables.iEndCardIndexPlayerBottom) {
            cards.get(i).setIsOpened(true);
            int yPos = cards.get(i).getLocation().y;
            while (yPos > 360) {
                yPos -= 15;
                cards.get(i).setLocation(new Point(450, yPos));
                this.repaint();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean moveCardLeftToCenter(int i) {
        if (0 <= i && i < GlobalVariables.iEndCardIndexPlayerLeft) {
            cards.get(i).setIsOpened(true);
            int xPos = cards.get(i).getLocation().x;
            while (xPos < 300) {
                xPos += 15;
                cards.get(i).setLocation(new Point(xPos, 300));
                this.repaint();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean moveCardTopToCenter(int i) {
        if (GlobalVariables.iStartCardIndexPlayerTop <= i && i < GlobalVariables.iEndCardIndexPlayerTop) {
            cards.get(i).setIsOpened(true);
            int yPos = cards.get(i).getLocation().y;
            while (yPos < 200) {
                yPos += 15;
                cards.get(i).setLocation(new Point(450, yPos));
                this.repaint();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean moveCardRightToCenter(int i) {
        if (GlobalVariables.iStartCardIndexPlayerRight <= i && i < GlobalVariables.iEndCardIndexPlayerRight) {
            cards.get(i).setIsOpened(true);
            int xPos = cards.get(i).getLocation().x;
            while (xPos > 600) {
                xPos -= 15;
                cards.get(i).setLocation(new Point(xPos, 300));
                this.repaint();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean is2Club(int i) {
        Card c = cards.get(i);
        if (c.getCardID() == 26) {
            return true;
        }
        return false;
    }

    public int PlayerPlayFirst(int i) {
        if (0 <= i && i < 13) {
            return leftPlayFirst;
        } else if (13 <= i && i < 26) {
            return topPlayFirst;
        } else if (26 <= i && i < 39) {
            return rightPlayFirst;
        } else {
            return bottomPlayFirst;
        }
    }

    public int nextPlayer(int i) {
        int nextPlayer = 0;
        if (0 <= i && i < GlobalVariables.iEndCardIndexPlayerLeft) {
            nextPlayer = PlayerTopPlay;
        } else if (GlobalVariables.iStartCardIndexPlayerTop <= i && i < GlobalVariables.iEndCardIndexPlayerTop) {
            nextPlayer = PlayerRightPlay;
        } else if (GlobalVariables.iStartCardIndexPlayerRight <= i && i < GlobalVariables.iEndCardIndexPlayerRight) {
            nextPlayer = PlayerBottomPlay;
        } else if (GlobalVariables.iStartCardIndexPlayerBottom <= i && i < GlobalVariables.iEndCardIndexPlayerBottom) {
            nextPlayer = PlayerLeftPlay;
        }
        return nextPlayer;
    }

    public boolean updateCardPlayed() {
        return isPlayed();
    }

    public void setWinnnerHand(int indexWin) {
        cards.get(indexWin).setWinInHand(true);
        this.repaint();
    }

    public void setAndSendInformationToServer(int index) {
        GlobalVariables.nextPlayer = nextPlayer(index);
        this.cardIndexPlayed = "index " + index + " " + GlobalVariables.nextPlayer;
        GlobalVariables.client.SendMessage(this.cardIndexPlayed);
        this.cardIndexPlayed = "";
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(getBackgroundImage(), 0, 0, this);
        for (int i = 0; i < cards.size(); ++i) {
            cards.get(i).draw(g, this);
        }

    }

    public void mousePressed(MouseEvent me) {
        Point temp = new Point(me.getX(), me.getY());
        for (int i = getTotalCards() - 1; i >= 0; --i) {
            if (cards.get(i).contain(temp)) {
                setSelectedCards(cards.get(i));

                if ((cards.get(i).isIsOpened() == true) && GlobalVariables.firstClick == false) {
                    switch (GlobalVariables.nextPlayer) {

                        case 11:
                            if (daHetCacLoaiBaiKhacCo()) {
                                GlobalVariables.shootTheMoon = true;
                                if (GlobalVariables.firstTimesSendShootTheMoon == true) {
                                    GlobalVariables.client.SendMessage("Heart_broken! 11");
                                }
                                if (this.moveCardLeftToCenter(i) == true) {
                                    setAndSendInformationToServer(i);
                                    GlobalVariables.cannotClick = false;
                                } else {
                                    GlobalVariables.cannotClick = true;
                                }
                            } else {
                                if (GlobalVariables.shootTheMoon == true) {
                                    if (this.moveCardLeftToCenter(i) == true) {
                                        setAndSendInformationToServer(i);
                                        GlobalVariables.cannotClick = false;
                                    } else {
                                        GlobalVariables.cannotClick = true;
                                    }
                                } else {
                                    if (GlobalVariables.indexCardOrigin == -1) {
                                        if (this.chonConBaiLoaiKhac(i, 11) == true) {
                                            setAndSendInformationToServer(i);
                                            GlobalVariables.cannotClick = false;
                                        } else {
                                            GlobalVariables.cannotClick = true;
                                        }
                                    } else {
                                        if (kiemTraCungLoai() == true) {
                                            if (this.chonConBaiCungLoai(i, 11) == true) {
                                                setAndSendInformationToServer(i);
                                                GlobalVariables.cannotClick = false;
                                            } else {
                                                GlobalVariables.cannotClick = true;
                                            }
                                        } else {
                                            chonConBaiLoaiKhac(i, 11);
                                            if (this.chonConBaiLoaiKhac(i, 11) == true) {
                                                setAndSendInformationToServer(i);
                                                GlobalVariables.cannotClick = false;
                                            } else {
                                                GlobalVariables.cannotClick = true;
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        case 12:
                            if (daHetCacLoaiBaiKhacCo()) {
                                GlobalVariables.shootTheMoon = true;
                                if (GlobalVariables.firstTimesSendShootTheMoon == true) {
                                    GlobalVariables.client.SendMessage("Heart_broken! 12");
                                }
                                if (this.moveCardTopToCenter(i) == true) {
                                    setAndSendInformationToServer(i);
                                    GlobalVariables.cannotClick = false;
                                } else {
                                    GlobalVariables.cannotClick = true;
                                }
                            } else {
                                if (GlobalVariables.shootTheMoon == true) {
                                    if (this.moveCardTopToCenter(i) == true) {
                                        setAndSendInformationToServer(i);
                                        GlobalVariables.cannotClick = false;
                                    } else {
                                        GlobalVariables.cannotClick = true;
                                    }
                                } else {
                                    if (GlobalVariables.indexCardOrigin == -1) {
                                        if (this.chonConBaiLoaiKhac(i, 12) == true) {
                                            setAndSendInformationToServer(i);
                                            GlobalVariables.cannotClick = false;
                                        } else {
                                            GlobalVariables.cannotClick = true;
                                        }
                                    } else {
                                        if (kiemTraCungLoai() == true) {
                                            if (this.chonConBaiCungLoai(i, 12) == true) {
                                                setAndSendInformationToServer(i);
                                                GlobalVariables.cannotClick = false;
                                            } else {
                                                GlobalVariables.cannotClick = true;
                                            }
                                        } else {
                                            if (this.chonConBaiLoaiKhac(i, 12) == true) {
                                                setAndSendInformationToServer(i);
                                                GlobalVariables.cannotClick = false;
                                            } else {
                                                GlobalVariables.cannotClick = true;
                                            }
                                        }

                                    }
                                }
                            }
                            break;
                        case 13:
                            if (daHetCacLoaiBaiKhacCo()) {
                                GlobalVariables.shootTheMoon = true;
                                if (GlobalVariables.firstTimesSendShootTheMoon == true) {
                                    GlobalVariables.client.SendMessage("Heart_broken! 13");
                                }
                                if (this.moveCardRightToCenter(i) == true) {
                                    setAndSendInformationToServer(i);
                                    GlobalVariables.cannotClick = false;
                                } else {
                                    GlobalVariables.cannotClick = true;
                                }
                            } else {
                                if (GlobalVariables.shootTheMoon == true) {
                                    if (this.moveCardRightToCenter(i) == true) {
                                        setAndSendInformationToServer(i);
                                        GlobalVariables.cannotClick = false;
                                    } else {
                                        GlobalVariables.cannotClick = true;
                                    }
                                } else {
                                    if (GlobalVariables.indexCardOrigin == -1) {
                                        if (this.chonConBaiLoaiKhac(i, 13) == true) {
                                            setAndSendInformationToServer(i);
                                            GlobalVariables.cannotClick = false;
                                        } else {
                                            GlobalVariables.cannotClick = true;
                                        }
                                    } else {
                                        if (kiemTraCungLoai() == true) {
                                            if (this.chonConBaiCungLoai(i, 13) == true) {
                                                setAndSendInformationToServer(i);
                                                GlobalVariables.cannotClick = false;
                                            } else {
                                                GlobalVariables.cannotClick = true;
                                            }
                                        } else {
                                            if (this.chonConBaiLoaiKhac(i, 13) == true) {
                                                setAndSendInformationToServer(i);
                                                GlobalVariables.cannotClick = false;
                                            } else {
                                                GlobalVariables.cannotClick = true;
                                            }
                                        }
                                    }
                                }
                            }
                            break;

                        case 14:
                            if (daHetCacLoaiBaiKhacCo()) {
                                GlobalVariables.shootTheMoon = true;
                                if (GlobalVariables.firstTimesSendShootTheMoon == true) {
                                    GlobalVariables.client.SendMessage("Heart_broken! 14");
                                }
                                if (this.moveCardBottomToCenter(i) == true) {
                                    setAndSendInformationToServer(i);
                                    GlobalVariables.cannotClick = false;
                                } else {
                                    GlobalVariables.cannotClick = true;
                                }
                            } else {
                                if (GlobalVariables.shootTheMoon == true) {
                                    if (this.moveCardBottomToCenter(i) == true) {
                                        setAndSendInformationToServer(i);
                                        GlobalVariables.cannotClick = false;
                                    } else {
                                        GlobalVariables.cannotClick = true;
                                    }
                                } else {
                                    if (GlobalVariables.indexCardOrigin == -1) {
                                        if (this.chonConBaiLoaiKhac(i, 14) == true) {
                                            setAndSendInformationToServer(i);
                                            GlobalVariables.cannotClick = false;
                                        } else {
                                            GlobalVariables.cannotClick = true;
                                        }
                                    } else {
                                        if (kiemTraCungLoai() == true) {
                                            if (this.chonConBaiCungLoai(i, 14) == true) {
                                                setAndSendInformationToServer(i);
                                                GlobalVariables.cannotClick = false;
                                            } else {
                                                GlobalVariables.cannotClick = true;
                                            }
                                        } else {
                                            if (this.chonConBaiLoaiKhac(i, 14) == true) {
                                                setAndSendInformationToServer(i);
                                                GlobalVariables.cannotClick = false;
                                            } else {
                                                GlobalVariables.cannotClick = true;
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                    }
                    setPlayed(true);
                } else if ((cards.get(i).isIsOpened() == true) && GlobalVariables.firstClick == true) {
                    if (cards.get(i).getCardID() == 26) {
                        this.moveCard(i);
                        setPlayed(true);
                        GlobalVariables.firstClick = false;
                        setAndSendInformationToServer(i);
                    }
                }
                break;
            }
        }
        this.repaint();
    }

    public void mouseDragged(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public int getTotalCards() {
        return getCards().size();
    }

    /**
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setImage(String stringName){
        if (stringName == null ? "none" == null : stringName.equals("none")) {
            setBackgroundImage(null);
        } else {
            String path = IMGBG_PATH + stringName;
            URL url = getClass().getResource(path);
            backgroundImage = new ImageIcon(url);
        }
    }

    /**
     * @param selectedCards the selectedCards to set
     */
    public void setSelectedCards(Card selectedCards) {
        this.selectedCards = selectedCards;
    }

    /**
     * @return the selectedCards
     */
    public Card getSelectedCards() {
        return selectedCards;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    /**
     * @return the backgroundImage
     */
    public Image getBackgroundImage() {
        if (backgroundImage == null) {
            return null;
        } else {
            return backgroundImage.getImage();
        }
    }

    /**
     * @param backgroundImage the backgroundImage to set
     */
    public void setBackgroundImage(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setBG() {
        String path = IMGBG_PATH + "hinhnen_9.jpg";
        URL url = getClass().getResource(path);
        backgroundImage = new ImageIcon(url);
    }

    /**
     * @return the dealed
     */
    public boolean isDealed() {
        return dealed;
    }

    /**
     * @param dealed the dealed to set
     */
    public void setDealed(boolean dealed) {
        this.dealed = dealed;
    }

    /**
     * @return the played
     */
    public boolean isPlayed() {
        return played;
    }

    /**
     * @param played the played to set
     */
    public void setPlayed(boolean played) {
        this.played = played;
    }
}
