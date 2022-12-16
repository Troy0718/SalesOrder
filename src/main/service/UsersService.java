package main.service;

import main.model.Users;

public interface UsersService {

	public void createNewAccount(Users user);

	public boolean loginExists(String login);
}
