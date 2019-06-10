package wss;

import domain.model.Kweet;
import java.io.IOException;
import java.io.StringReader;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import service.KweetService;

/**
 *
 * @author Jeroen Roovers
 */
@ServerEndpoint("/wss/{username}")
public class WebSocket {

    @Inject
    WebsocketService wsService;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        System.out.println("onOpen::" + session.getId());
        wsService.registerSession(username, session);
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        System.out.println("onClose::" + session.getId());
        wsService.removeSession(username, session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);

        JsonObject object;
        try (JsonReader jsonReader = Json.createReader(new StringReader(message))) {
            object = jsonReader.readObject();
        }
        try {
            String username = object.getString("username");
            String token = object.getString("token");
            String body = object.getString("body");

            wsService.handleOnMessage(username, session, body);
        } catch (NullPointerException | ClassCastException ex) {
            System.out.println("Invalid websocket message.");

            ex.printStackTrace();
        }

    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
        t.printStackTrace();
    }
}
