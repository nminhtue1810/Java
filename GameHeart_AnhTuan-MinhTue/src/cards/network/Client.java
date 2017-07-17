/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cards.network;

import globalVariables.GlobalVariables;
import cards.gui.MainView;
import java.net.*;
import java.io.*;

/**
 *
 * @author Minh Tue
 */
public final class Client {

    //Các thuộc tính
    int _iPort = 2345;
    Socket _socketTalk;
    MainView _mainView;

    //Gởi dữ liệu cho máy khác
    OutputStream _outputStream;
    BufferedWriter _bufferedWriter;

    //Nhận dữ liệu từ máy khác
    InputStream _inputStream;
    BufferedReader _bufferedReader;
    
    // Thread để lắng nghe các message
    ClientListener _clientListener_ListenThread;

    //Hàm khởi tạo
    public Client(MainView argMainView) throws IOException {

        this._mainView = argMainView;
        String _strPlayerName = GlobalVariables.PlayerName;

        try {
            Socket _socketListener = new Socket("localhost", _iPort);
            _clientListener_ListenThread = new ClientListener(_socketListener, _strPlayerName);

            //Tạo Socket mới để send dữ liệu
            _socketTalk = new Socket("localhost", _iPort);
            _outputStream = _socketTalk.getOutputStream();
            _bufferedWriter = new BufferedWriter(new OutputStreamWriter(_outputStream));

            _inputStream = _socketListener.getInputStream();
            _bufferedReader = new BufferedReader(new InputStreamReader(_inputStream));

            SendMessage(_strPlayerName + " connected!!!!");

            //Nếu đã đủ người chơi thì sẽ không cho kết nối tới server
            if (GlobalVariables.fullPlayer == true) {
                _bufferedReader.readLine();
            }
            if (GlobalVariables.played == true) {
                SendMessage(GlobalVariables.cardIsPlayed);
                GlobalVariables.played = false;
            }
        } catch (IOException io) {
            System.out.println("There're some error");
            throw io;
        }
    }

    //Các phương thức
    
    public void SendMessage(String argMessage) {
        try {
            _bufferedWriter.write(argMessage);
            _bufferedWriter.newLine();
            _bufferedWriter.flush();
        } catch (IOException io) {
        }
    }
}
