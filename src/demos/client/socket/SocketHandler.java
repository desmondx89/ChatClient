/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demos.client.socket;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author Desmond
 */
@ClientEndpoint
public class SocketHandler {

    private static final Logger logger = Logger.getLogger(SocketHandler.class.getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.log(Level.INFO, "WebSocket session started");
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        logger.log(Level.INFO, "Websocket session ended " + reason.getReasonPhrase());
    }
    
    @OnError
    public void onError(Session session, Throwable ex){
        logger.log(Level.INFO, "WebSocket session errored",ex.getMessage());
    }
    
    @OnMessage
    public void onMessage(String message){
        logger.log(Level.INFO,">"+message);
    }
}
