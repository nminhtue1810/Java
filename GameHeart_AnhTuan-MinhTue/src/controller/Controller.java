/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import cards.*;
import cards.gui.*;

/**
 *
 * @author Minh Tue
 */
public class Controller {

    public Deck table;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controller controller;

        if (args.length == 0) {
            controller = new Controller(true);
        }
    }
    public MainView main;

    public Controller(boolean isServer) {
        this.setView(new MainView());
    }

    private void setView(MainView main) {
        this.main = main;
    }
}
