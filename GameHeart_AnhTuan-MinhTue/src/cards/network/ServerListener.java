/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cards.network;

import cards.Card;
import globalVariables.GlobalVariables;
import cards.gui.MainView;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh Tue
 */
public class ServerListener implements Runnable {

    //Các thuộc tính
    private String _strPlayerName;
    Socket _socketServerListen;
    Thread _threadServerListen;
    public String _strReceivedMessage;
    MainView _mainView;

    //Hàm khởi tạo
    ServerListener(Socket argListen, MainView _mainV) {
        try {
            this._mainView = _mainV;
            GlobalVariables.clientConnected = false;
            _socketServerListen = argListen;
            _threadServerListen = new Thread(this, "player");
            _threadServerListen.start();

        } catch (Exception ex) {
            System.out.println("There's an error in server listen process!!!");
        }
    }

    public void run() {

        BufferedReader _bufferedReader = null;
        InputStream _inputStream;

        try {
            _inputStream = _socketServerListen.getInputStream();
            _bufferedReader = new BufferedReader(new InputStreamReader(_inputStream));

            do {
                _strReceivedMessage = _bufferedReader.readLine();
                String[] _arrStringTemp = _strReceivedMessage.split(" ");
                if (_arrStringTemp[0].equals("Heart_broken!")) {
                    Server.SendMessageToAllClient(_strReceivedMessage, Server._arrayList_ClientManager);
                }

                if (_arrStringTemp[0].equals("index")) {
                    GlobalVariables.cardIsPlayed = _arrStringTemp[1];

                    GlobalVariables.strRemove += " " + _arrStringTemp[1];
                    int cardPlayed = Integer.parseInt(_arrStringTemp[1]);
                    GlobalVariables.listNextPlayer[GlobalVariables.iTotalCard] = Integer.parseInt(_arrStringTemp[2]);
                    GlobalVariables.listCardPlayed[GlobalVariables.iTotalCard] = cardPlayed;
                    GlobalVariables.iTotalCard++;
                    Server.SendMessageToAllClient(_strReceivedMessage + " " + GlobalVariables.listCardPlayed[0], Server._arrayList_ClientManager);
                    if (4 == GlobalVariables.iTotalCard) {
                        GlobalVariables.strPlayerWin += " " + this.winner(GlobalVariables.listCardPlayed, GlobalVariables.listNextPlayer);
                        Server.SendMessageToAllClient(GlobalVariables.strPlayerWin, Server._arrayList_ClientManager);
                        try {
                            this._threadServerListen.sleep(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Server.SendMessageToAllClient(GlobalVariables.strRemove, Server._arrayList_ClientManager);
                        GlobalVariables.strRemove = "remove";
                        GlobalVariables.iTotalCard = 0;
                        GlobalVariables.strPlayerWin = "winner";
                        GlobalVariables.listCardPlayed = new int[4];
                    }
                }
                System.out.println(_strReceivedMessage);
            } while (true);

        } catch (IOException io) {
        } finally {

            try {
                // Đóng kết nối
                _bufferedReader.close();
                _socketServerListen.close();
                _threadServerListen.stop();
            } catch (IOException io) {
                System.out.println("There's an error in server listen process!!!");
            }
        }
    }

    public void setNamePlayer(String namePlayer) {
        this._strPlayerName = namePlayer;
    }

    public String getNamePlayer() {
        return _strPlayerName;
    }

    public String winner(int[] listCardsPlayed, int[] listNextPlayer) {
        Card c = _mainView.deck.getCards().get(listCardsPlayed[0]);
        int[] cardsID = new int[4];
        cardsID[0] = c.getCardID();
        int indexWin = listCardsPlayed[0];
        int nextPlayer;
        if (listNextPlayer[0] == 11) {
            nextPlayer = 14;
        } else {
            nextPlayer = listNextPlayer[0] - 1;
        }
        String[] strCardType = c.getCardName().split(" ");
        int _iConBai = Integer.parseInt(strCardType[0]);
        int _iLoaiBai = Integer.parseInt(strCardType[1]);
        for (int i = 1; i < listCardsPlayed.length; ++i) {
            String[] strCardTypeTemp = _mainView.deck.getCards().get(listCardsPlayed[i]).getCardName().split(" ");
            cardsID[i] = _mainView.deck.getCards().get(listCardsPlayed[i]).getCardID();
            int _iConBaiTam = Integer.parseInt(strCardTypeTemp[0]);
            int _iLoaiBaiTam = Integer.parseInt(strCardTypeTemp[1]);
            if (_iLoaiBai == _iLoaiBaiTam) {
                if (_iConBai < _iConBaiTam) {
                    _iConBai = _iConBaiTam;
                    indexWin = listCardsPlayed[i];
                    if (listNextPlayer[i] == 11) {
                        nextPlayer = 14;
                    } else {
                        nextPlayer = listNextPlayer[i] - 1;
                    }
                }
            }
        }
        int totalMark = this.markInHand(cardsID);
        return indexWin + " " + nextPlayer + " " + totalMark;
    }

    public int markInHand(int[] cardsID) {
        int totalMark = 0;
        for (int i = 0; i < cardsID.length; ++i) {
            if (cardsID[i] == 49) {
                totalMark += 13;
            }
            if (0 <= cardsID[i] && cardsID[i] < 13) {
                totalMark += 1;
            }
        }
        return totalMark;
    }
}
