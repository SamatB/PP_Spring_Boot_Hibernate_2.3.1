package beganov.web.service;

import beganov.web.model.User;
import beganov.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user, Long id) {
        User userUpdate = userRepository.findById(id).get();
        userUpdate.setName(userUpdate.getName());
        userUpdate.setSurname(userUpdate.getSurname());
        userUpdate.setAge(userUpdate.getAge());
        userRepository.save(user);
    }
}
