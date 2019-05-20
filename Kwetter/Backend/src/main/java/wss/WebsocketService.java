/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wss;

import domain.model.Kweet;
import domain.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.Session;
import service.KweetService;
import service.UserService;

@Stateless
public class WebsocketService {

    @Inject
    KweetService kweetService;

    @Inject
    UserService userService;

    private Map<String, ArrayList<Session>> sessions;

    public WebsocketService() {
        this.sessions = new HashMap<>();
    }

    public void registerSession(String username, Session session) {
        if (sessions.containsKey(username)) {
            sessions.get(username).add(session);
        } else {
            sessions.put(username, new ArrayList<>());
            sessions.get(username).add(session);
        }
    }

    public void removeSession(String username, Session session) {
        if (sessions.containsKey(username)) {
            sessions.get(username).remove(session);
            if (sessions.get(username).size() == 0) {
                sessions.remove(username);
            }
        }
    }

    public void handleOnMessage(String username, Session client, String body) {
        Kweet k = kweetService.createKweet(username, body);
        if (k != null) {
            try {
                Jsonb jsonb = JsonbBuilder.create();
                String msg = jsonb.toJson(k);
                System.out.println(msg);

                User author = userService.getUserbyUsername(username);
                List<User> followers = userService.getFollowersByUser(author);

                client.getBasicRemote().sendText(msg);
                for (User f : followers) {
                    if (sessions.containsKey(f.getUsername())) {
                        for (Session s : sessions.get(f.getUsername())) {
                            s.getBasicRemote().sendText(msg);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                client.getBasicRemote().sendText("Kweet invalid!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
