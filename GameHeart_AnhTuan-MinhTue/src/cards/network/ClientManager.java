/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cards.network;

import java.net.Socket;

/**
 *
 * @author Minh Tue
 */
public class ClientManager {

    //Thuộc tính
    Socket _socketClientListen = null;
    Socket _socketClientTalk = null;
    private String _strPlayerName;

    //Hàm khởi tạo
    public ClientManager(Socket argListen, Socket argTalk, String argName){
        this._socketClientListen = argListen;
        this._socketClientTalk = argTalk;
        this._strPlayerName = argName;
    }

    //Các phương thức

    public String getNamePlayer() {
        return _strPlayerName;
    }
  
    public void setNamePlayer(String namePlayer) {
        this._strPlayerName = namePlayer;
    }
}
