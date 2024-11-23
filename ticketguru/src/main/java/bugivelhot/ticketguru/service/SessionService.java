package bugivelhot.ticketguru.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

@Service
public class SessionService {

    private final ConcurrentHashMap<String, String> sessionStore = new ConcurrentHashMap<>();

    public String createSession(String username) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, username);
        return sessionId;
    }

    // Retrieve session data (username) by session ID
    public String getSession(String sessionId) {
        return sessionStore.get(sessionId);
    }

    // Remove session (logout)
    public void removeSession(String sessionId) {
        sessionStore.remove(sessionId);
    }

}
