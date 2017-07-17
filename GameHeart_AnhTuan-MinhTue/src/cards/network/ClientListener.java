package cards.network;

import globalVariables.GlobalVariables;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Minh Tue
 */
public class ClientListener implements Runnable {

    //Các thuộc tính
    String _strPlayerName;
    Socket _socketClientListen = null;
    Thread _threadClientListening;
    public String _strReceivedMessage;

    //Hàm khởi tạo
    public ClientListener(Socket argSocketClient, String argName) {
        this._strPlayerName = argName;
        this._socketClientListen = argSocketClient;
        _threadClientListening = new Thread(this, _strPlayerName);
        _threadClientListening.start();
    }

    //Các phương thức
    public void run() {
        BufferedReader _bufferedReader = null;
        InputStream _inputStream;

        try {
            _inputStream = _socketClientListen.getInputStream();
            _bufferedReader = new BufferedReader(new InputStreamReader(_inputStream));

            do {
                _strReceivedMessage = _bufferedReader.readLine();
                String _strNumber = _strReceivedMessage.substring(0, 1);
                String _strNameOfPlayer = _strReceivedMessage.substring(0, 4);
                String _strPlayerIndex = _strReceivedMessage.substring(0, 5);
                String _strCardsDes = _strReceivedMessage.substring(0, 6);
                String[] _strShootMoon = _strReceivedMessage.split(" ");
                if (_strShootMoon[0].equals("Heart_broken!")) {
                    GlobalVariables.shootTheMoon = true;

                    if (GlobalVariables.firstTimesSendShootTheMoon == true) {
                        GlobalVariables.firstTimesSendShootTheMoon = false;
                        GlobalVariables.PlayerShootTheMoon = Integer.parseInt(_strShootMoon[1]);
                    } else {
                        GlobalVariables.firstTimesSendShootTheMoon = false;
                        GlobalVariables.PlayerShootTheMoon = 0;
                    }
                }

                if (_strReceivedMessage.equals("Room had full!!!!")) {
                    GlobalVariables.fullPlayer = true;
                    break;
                }

                if (isStringNumber(_strNumber)) {
                    GlobalVariables.cardsInfo = _strReceivedMessage;
                }

                if (_strReceivedMessage.equals("Start Deal Cards!")) {
                    GlobalVariables.startDealCards = true;
                }

                if (_strNameOfPlayer.equals("name")) {
                    String[] _arrayNamePlayer = _strReceivedMessage.split(" ");
                    GlobalVariables.nameBottomPlayer = _arrayNamePlayer[1];
                    GlobalVariables.nameLeftPlayer = _arrayNamePlayer[2];
                    GlobalVariables.nameTopPlayer = _arrayNamePlayer[3];
                    GlobalVariables.nameRightPlayer = _arrayNamePlayer[4];
                }

                if (_strPlayerIndex.equals("index")) {
                    String[] stringTemp = _strReceivedMessage.split(" ");
                    GlobalVariables.cardIsPlayed = stringTemp[1];
                    GlobalVariables.cardChaged = true;
                    GlobalVariables.nextPlayer = Integer.parseInt(stringTemp[2]);
                    GlobalVariables.indexCardOrigin = Integer.parseInt(stringTemp[3]);
                }

                if (_strCardsDes.equals("winner")) {
                    String[] _strPlayerWin = _strReceivedMessage.split(" ");
                    GlobalVariables.winnerIndex = Integer.parseInt(_strPlayerWin[1]);
                    GlobalVariables.nextPlayer = Integer.parseInt(_strPlayerWin[2]);
                    GlobalVariables.indexCardOrigin = -1;
                    switch (GlobalVariables.nextPlayer) {
                        case 11:
                            GlobalVariables.PointsLeft += Integer.parseInt(_strPlayerWin[3]);
                            break;
                        case 12:
                            GlobalVariables.PointsTop += Integer.parseInt(_strPlayerWin[3]);
                            break;
                        case 13:
                            GlobalVariables.PointsRight += Integer.parseInt(_strPlayerWin[3]);
                            break;
                        case 14:
                            GlobalVariables.PointsBottom += Integer.parseInt(_strPlayerWin[3]);
                            break;
                    }

                } else if (_strCardsDes.equals("remove")) {

                    String[] _strTypeCardsRemove = _strReceivedMessage.split(" ");
                    GlobalVariables.listCardBeRemoved[0] = Integer.parseInt(_strTypeCardsRemove[1]);
                    GlobalVariables.listCardBeRemoved[1] = Integer.parseInt(_strTypeCardsRemove[2]);
                    GlobalVariables.listCardBeRemoved[2] = Integer.parseInt(_strTypeCardsRemove[3]);
                    GlobalVariables.listCardBeRemoved[3] = Integer.parseInt(_strTypeCardsRemove[4]);
                    GlobalVariables.remove = true;
                }


                System.out.println(_strReceivedMessage);
            } while (true);
        } catch (IOException io) {
            System.out.println(io.toString());
        } finally {
            try {
                try {
                    _threadClientListening.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
                }

                _bufferedReader.close();
            } catch (IOException io) {
                System.out.println(io.toString());
            }
        }
    }

    private boolean isStringNumber(String argTemp) {
        try {
            if (0 <= Integer.parseInt(argTemp) && Integer.parseInt(argTemp) <= 9) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
