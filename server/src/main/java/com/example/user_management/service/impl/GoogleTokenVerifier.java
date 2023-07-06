package com.example.user_management.service.impl;

import com.example.user_management.entity.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

public class GoogleTokenVerifier {

    private static final String CLIENT_ID = "910299676468-6cjt8a57ipkf0hg07gcogvj78b2hmi9t.apps.googleusercontent.com";

    public static User verifyToken(String tokenId) {
        try {
            JsonFactory jsonFactory = new JacksonFactory();
            NetHttpTransport transport = new NetHttpTransport();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(tokenId);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                String name = (String) payload.get("name");
                User user = new User();
                user.setFullName(name);
                user.setEmail(email);
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
