/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cards;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author Minh Tue
 */
public final class Card extends JButton {

    private ImageIcon imgCardOpen;
    private ImageIcon imgCardClose;
    private Point location;
    private Rectangle recCard;
    private boolean winInHand;
    private boolean isOpened;
    private int cardID;
    private String cardName;

    public Card(ImageIcon _imageOpen, int ID) {
        this.imgCardOpen = _imageOpen;
        this.cardID = ID;
        this.recCard = new Rectangle(getImgCardOpen().getIconWidth(), getImgCardOpen().getIconHeight());
        this.setWinInHand(false);
        this.setIsOpened(false);
        this.setBorder(null);
    }

    public Card(ImageIcon _imageOpen, ImageIcon _imageClose, int ID) {
        this.cardID = ID;
        this.imgCardOpen = _imageOpen;
        this.imgCardClose = _imageClose;
        this.recCard = new Rectangle(getImgCardOpen().getIconWidth(), getImgCardOpen().getIconHeight());
        this.setWinInHand(false);
        this.setIsOpened(false);
        this.setBorder(null);
    }

    public Card(ImageIcon _imageOpen, ImageIcon _imageClose, int ID, String _cardName) {
        this.cardName = _cardName;
        this.cardID = ID;
        this.imgCardOpen = _imageOpen;
        this.imgCardClose = _imageClose;
        this.recCard = new Rectangle(getImgCardOpen().getIconWidth(), getImgCardOpen().getIconHeight());
        this.setWinInHand(false);
        this.setIsOpened(false);
        this.setBorder(null);
    }

    /**
     * @return the imgCardOpen
     */
    public ImageIcon getImgCard() {
        if (isIsOpened() == true) {
            return getImgCardOpen();
        } else {
            return getImgCardClose();
        }

    }

    /**
     * @param imgCardOpen the imgCardOpen to set
     */
    public void setImgCard(String imgPath) {
        try {
            URL img = getClass().getResource(imgPath);
            setImgCard(new ImageIcon(img));

        } catch (Exception e) {
            System.out.println("Card.setImages() error:\n " + e);
        }
    }

    public void draw(Graphics g, Component c) {
        if (isIsOpened() == true) {
            getImgCardOpen().paintIcon(c, g, location.x, location.y);
        } else {
            getImgCardClose().paintIcon(c, g, location.x, location.y);
        }

        if (isWinInHand()) {
            g.setColor(Color.red);
            g.drawRect(this.location.x, this.location.y, (int) recCard.getWidth(), (int) recCard.getHeight());
            setWinInHand(false);
        }
    }

    /**
     * @param imgCardOpen the imgCardOpen to set
     */
    public void setImgCard(ImageIcon imgCard) {
        if (isIsOpened() == true) {
            this.setImgCardOpen(imgCard);
        } else {
            this.setImgCardClose(imgCard);
        }

    }

    /**
     * @return the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    public void setLocation(int x, int y) {
        Point point = new Point(x, y);
        this.location = point;
    }

    /**
     * @return the recCard
     */
    public Rectangle getRecCard() {
        return recCard;
    }

    /**
     * @param recCard the recCard to set
     */
    public void setRecCard(Rectangle recCard) {
        this.recCard = recCard;
    }

    public boolean contain(Point p) {
        return (this.location.getX() < p.getX() && (p.getX() < this.location.getX() + getRecCard().getWidth())
                && this.location.getY() < p.getY() && p.getY() < (this.location.getY() + getRecCard().getHeight()));
    }

    /**
     * @return the isOpened
     */
    public boolean isIsOpened() {
        return isOpened;
    }

    /**
     * @param isOpened the isOpened to set
     */
    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }

    /**
     * @return the imgCardOpen
     */
    public ImageIcon getImgCardOpen() {
        return imgCardOpen;
    }

    /**
     * @param imgCardOpen the imgCardOpen to set
     */
    public void setImgCardOpen(ImageIcon imgCardOpen) {
        this.imgCardOpen = imgCardOpen;
    }

    /**
     * @return the imgCardClose
     */
    public ImageIcon getImgCardClose() {
        return imgCardClose;
    }

    /**
     * @param imgCardClose the imgCardClose to set
     */
    public void setImgCardClose(ImageIcon imgCardClose) {
        this.imgCardClose = imgCardClose;
    }

    /**
     * @return the cardID
     */
    public int getCardID() {
        return cardID;
    }

    /**
     * @param cardID the cardID to set
     */
    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;

    }

    /**
     * @return the winInHand
     */
    public boolean isWinInHand() {
        return winInHand;
    }

    /**
     * @param winInHand the winInHand to set
     */
    public void setWinInHand(boolean winInHand) {
        this.winInHand = winInHand;
    }
}
