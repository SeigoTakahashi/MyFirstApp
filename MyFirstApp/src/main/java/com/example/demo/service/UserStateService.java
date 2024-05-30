package com.example.demo.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class UserStateService {
	private final ConcurrentHashMap<String, String> userStates = new ConcurrentHashMap<>();

	public void setUserState(String userId, String state) {
		userStates.put(userId, state);
	}

	public String getUserState(String userId) {
		return userStates.get(userId);
	}

	public void removeUserState(String userId) {
		userStates.remove(userId);
	}
}
