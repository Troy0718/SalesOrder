package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Users;

@Repository
public interface UsersRespository extends JpaRepository<Users, Integer> {

	public boolean existsByLogin(String login);

}
