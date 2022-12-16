package main.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import main.model.Roles;
import main.model.Users;
import main.repository.RolesRepository;
import main.repository.UsersRespository;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRespository usersRespository;

	@Autowired
	private RolesRepository rolesRespository;

	@Override
	public void createNewAccount(Users user) {
		user.setEnabled(true);// 讓帳號有效

		// 預設加密方式為bcrypt
		user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
		usersRespository.save(user);

		Roles role = new Roles();
		role.setLogin(user.getLogin());
		role.setRole("ROLE_CLIENT");
		rolesRespository.save(role);

	}

	@Override
	public boolean loginExists(String login) {

		return usersRespository.existsByLogin(login);

	}

}
