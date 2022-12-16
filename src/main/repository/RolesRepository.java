package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

}
