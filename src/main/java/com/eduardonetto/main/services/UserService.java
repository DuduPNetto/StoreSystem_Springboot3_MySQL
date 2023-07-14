package com.eduardonetto.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.repositories.UserRepository;
import com.eduardonetto.main.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id = " + id));
	}

	public User insert(User user) {
		return repository.save(user);
	}

	public void delete(Long id) {
		User user = findById(id);
		repository.delete(user);
	}

	public User update(Long id, User user) {
		User entity = findById(id);
		updateUser(entity, user);
		return repository.save(entity);
	}

	private void updateUser(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}

}
