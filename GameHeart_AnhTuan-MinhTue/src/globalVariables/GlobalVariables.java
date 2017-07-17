/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package globalVariables;

import cards.network.*;
import java.util.ArrayList;

/**
 *
 * @author Minh Tue
 */
public class GlobalVariables {

    public static ArrayList<String> listNamePlayer = new ArrayList<String>();
    public static String PlayerName = "Player";
    public static String nameLeftPlayer = "Player Left";
    public static String nameTopPlayer = "Player Top";
    public static String nameBottomPlayer = "Player Bottom";
    public static String nameRightPlayer = "Player Right";
    public static Server server = null;
    public static Client client = null;
    public static boolean fullPlayer = false;
    public static boolean clientConnected = false;
    public static String cardsInfo = "";
    public static boolean startDealCards = false;
    public static boolean canPlay = true;
    public static boolean played = false;
    public static boolean firstClick = true;
    public static boolean cannotClick = false;
    public static boolean cardChaged = false;
    public static String cardIsPlayed = "";
    public static int nextPlayer;
    public static int iTotalCard;
    public static String strPlayerWin = "winner";
    public static int winnerIndex = -1;
    public static String strRemove = "remove";
    public static int[] listCardBeRemoved = new int[4];
    public static int[] listCardPlayed = new int[4];
    public static int[] listNextPlayer = new int[4];
    public static boolean remove = false;
    public static int iStartCardIndexPlayerBottom = 39;
    public static int iEndCardIndexPlayerBottom = 52;
    public static int iEndCardIndexPlayerLeft = 13;
    public static int iStartCardIndexPlayerTop = 13;
    public static int iEndCardIndexPlayerTop = 26;
    public static int iStartCardIndexPlayerRight = 26;
    public static int iEndCardIndexPlayerRight = 39;
    public static int PointsLeft = 0;
    public static int PointsTop = 0;
    public static int PointsRight = 0;
    public static int PointsBottom = 0;
    public static int indexCardOrigin = -1;
    public static boolean shootTheMoon = false;
    public static int PlayerShootTheMoon;
    public static boolean firstTimesSendShootTheMoon = true;
}
