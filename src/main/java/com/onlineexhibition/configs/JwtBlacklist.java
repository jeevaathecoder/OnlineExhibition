package com.onlineexhibition.configs;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class JwtBlacklist {
    private Set<String> blacklist;

    public JwtBlacklist() {
        this.blacklist = new HashSet<>();
    }
    public void addToBlacklist(String token) {
        blacklist.add(token);
    }

    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }

}