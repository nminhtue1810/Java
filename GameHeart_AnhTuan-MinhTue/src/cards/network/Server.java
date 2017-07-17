
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cards.network;

import cards.Card;
import cards.Deck;
import globalVariables.GlobalVariables;
import cards.gui.MainView;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh Tue
 */
public class Server implements Runnable {

    //Các thuộc tính
    public static int _iPort = 2345;
    public ServerSocket _serverSocket;
    public Thread _threadListener;
    public static int _iPlayer = 0;
    private MainView _mainView;
    public String _strCardID = "";
    public static ArrayList<ClientManager> _arrayList_ClientManager;
    public Deck _deck = null;

    //Hàm khởi tạo
    public Server() throws IOException {
        try {
            _arrayList_ClientManager = new ArrayList<ClientManager>();
            _serverSocket = new ServerSocket(_iPort);
            _threadListener = new Thread(this, "Server!");
            _threadListener.start();
        } catch (IOException ioe) {
        }
    }

    
    //Các phương thức

    public static void SendMessage(String argMessage, Socket argClientSocket) {
        try {
            OutputStream _outputStream = argClientSocket.getOutputStream();
            BufferedWriter _bufferedWriter = new BufferedWriter(new OutputStreamWriter(_outputStream));

            _bufferedWriter.write(argMessage);
            _bufferedWriter.newLine();
            _bufferedWriter.flush();

        } catch (IOException io) {
        }
    }

    public static void SendMessageToAllClient(String argMessage, ArrayList<ClientManager> argListClientManager) {
        try {
            for (int i = 0; i < argListClientManager.size(); ++i) {
                OutputStream _outputStream = argListClientManager.get(i)._socketClientTalk.getOutputStream();
                BufferedWriter _bufferedWriter = new BufferedWriter(new OutputStreamWriter(_outputStream));

                _bufferedWriter.write(argMessage);
                _bufferedWriter.newLine();
                _bufferedWriter.flush();
            }
        } catch (IOException io) {
        }
    }

    public void getMainView(MainView argMainView) {
        this.setMainView(argMainView);
    }

    public void run() {
        try {
            do {
                if (4 == _iPlayer) {
                    this.getMainView().dealingCards();
                    this._deck = this.getMainView().deck;
                    listCardsInfo(this._deck);
                    SendMessageToAllClient(_strCardID, _arrayList_ClientManager);
                    SendMessageToAllClient("Start Deal Cards!", _arrayList_ClientManager);
                    String stringNamePlayer = "name " + _arrayList_ClientManager.get(0).getNamePlayer() + " " + _arrayList_ClientManager.get(1).getNamePlayer()
                            + " " + _arrayList_ClientManager.get(2).getNamePlayer() + " " + _arrayList_ClientManager.get(3).getNamePlayer();
                    SendMessageToAllClient(stringNamePlayer, _arrayList_ClientManager);

                }

                Socket talkSocket = _serverSocket.accept();
                _iPlayer++;

                Socket _socketListen = _serverSocket.accept();
                ServerListener _serverListener = new ServerListener(_socketListen, getMainView());
                receiveMessage(_socketListen);

                if (_iPlayer <= 4) {
                    SendMessage(_iPlayer + ".Connect success!!!", talkSocket);
                } else if (4 < _iPlayer) {
                    SendMessage("Room had full!!!!", talkSocket);
                    talkSocket.close();
                    _socketListen.close();
                    continue;
                }

                System.out.println(GlobalVariables.listNamePlayer.get(_iPlayer - 1) + " joined the room!!!");
                _arrayList_ClientManager.add(new ClientManager(_socketListen, talkSocket, GlobalVariables.listNamePlayer.get(_iPlayer - 1)));
            } while (true);
        } catch (Exception e) {
        }
    }

    //Hàm nhận tin nhắn từ client chỉ để lấy tên người chơi, còn các tin nhắn xử lý lá bài sẽ nhận bằng ServerListener
    public void receiveMessage(Socket argListen) {
        InputStream _inputStream = null;

        try {
            _inputStream = argListen.getInputStream();
            BufferedReader _bufferedReader = new BufferedReader(new InputStreamReader(_inputStream));
            String _strReceivedMessage = _bufferedReader.readLine();
            String[] _arrStringTemp = _strReceivedMessage.split(" ");

            if (_arrStringTemp[1].equals("connected!!!!")) {
                GlobalVariables.listNamePlayer.add(_arrStringTemp[0]);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    public void listCardsInfo(Deck argDeck) {
        //cardsID = _deck.getCards().get(0).getCardID();
        for (Card c : argDeck.getCards()) {
            _strCardID += c.getCardID() + " ";
        }
    }

    /**
     * @return the _mainView
     */
    public MainView getMainView() {
        return _mainView;
    }

    /**
     * @param mainView the _mainView to set
     */
    public void setMainView(MainView mainView) {
        this._mainView = mainView;
    }
}
