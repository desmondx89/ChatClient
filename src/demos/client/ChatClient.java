/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demos.client;

import demos.client.socket.SocketHandler;
import java.net.URI;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author Desmond
 */
public class ChatClient {

    /**
     * @param args the command line arguments
     */
    private static final Logger logger = Logger.getLogger(ChatClient.class.getName());

    public static void main(String[] args) {
        System.out.println("Click inside the log window");
        System.out.println("Type your mesage and press Enter");
        System.out.println("Type exit to close chat");

        try {
            //initialize a new URI Object that points to the WebSocket ChatServer
            URI uri = new URI("ws://localhost:7001/pm/chat");
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();

            Session session = container.connectToServer(new SocketHandler(), uri);

            //Acquire the RemoteEndPoint object to be able to send messages to the ServerEndpoint
            RemoteEndpoint.Async remote = session.getAsyncRemote();

            //intialize a Scanner object to read text from the console
            Scanner s = new Scanner(System.in);

            while (s.hasNextLine()) {
                String message = s.nextLine();
                if (message.equals("exit")) {
                    break;
                }
                remote.sendText(message);
            }
            
            session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE,"Leaving the chat"));

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error Accessing Chat", ex);
        }

    }

}
