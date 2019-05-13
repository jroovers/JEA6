package wss;

import com.google.gson.Gson;
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
import javax.websocket.server.ServerEndpoint;
import service.KweetService;

/**
 *
 * @author Jeroen Roovers
 */
@ServerEndpoint("/wss")
public class WebSocket {

    @Inject
    KweetService kweetService;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" + session.getId());
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

            Kweet k = kweetService.createKweet(username, body);
            if (k != null) {
                try {
                    Jsonb jsonb = JsonbBuilder.create();
                    String msg = jsonb.toJson(k);
                    System.out.println(msg);
                    session.getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    session.getBasicRemote().sendText("Kweet invalid!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
