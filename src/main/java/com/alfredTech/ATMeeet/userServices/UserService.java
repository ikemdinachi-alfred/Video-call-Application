package com.alfredTech.ATMeeet.userServices;
import com.alfredTech.ATMeeet.user.User;

public interface UserService  {
    void registerUser(User request);
    User login(User user);
}
