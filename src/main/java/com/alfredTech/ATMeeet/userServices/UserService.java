package com.alfredTech.ATMeeet.userServices;
import com.alfredTech.ATMeeet.user.User;

import java.util.List;

public interface UserService  {
    void registerUser(User request);
    User login(User user);
    void logout(String email);
    List<User> viewAllUsers();
}
