package com.alfredTech.ATMeeet.userServices;

import com.alfredTech.ATMeeet.exceptions.UserExistException;
import com.alfredTech.ATMeeet.exceptions.UserNotFoundException;
import com.alfredTech.ATMeeet.exceptions.WrongDetailsException;
import com.alfredTech.ATMeeet.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@Service
public class UserServiceImpl implements UserService {
    private static final List<User> users = new ArrayList<>();


    @Override
    public void registerUser(User request) {
        request.setStatus("online");
        if (userExists(request.getEmail())) {
            throw new UserExistException("User already exists");
        }
        users.add(request);
    }

    @Override
    public User login(User user) {
        var userIndex = IntStream.range(0, users.size())
                .filter(count-> users.get(count).getEmail().equals(user.getEmail()))
                .findFirst()
                .orElseThrow(()-> new UserExistException("user not found"));
        var connectedUser = users.get(userIndex);
        if(!connectedUser.getPassword().equals(user.getPassword())) {
           throw new WrongDetailsException("wrong password");
        }
        connectedUser.setStatus("online");
        return connectedUser;
    }

    @Override
    public void logout(String email) {
        var userIndex = IntStream.range(0, users.size())
                .filter(count-> users.get(count).getEmail().equals(email))
                .findAny()
                .orElseThrow(()-> new UserNotFoundException("user not found"));
        users.get(userIndex).setStatus("offline");


    }

    @Override
    public List<User> viewAllUsers() {
        return users;
    }
    private boolean userExists(String email) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }
}
