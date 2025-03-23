package user;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    List<User> users = new ArrayList<>();

    public void add(User user){
        users.add(user);
    }
    public User getUserByEmail(String email){
        for (User user : users) {
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
    public boolean login(String email, String password){
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean isAlreadyExist(String email){
        for (User user : users) {
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }


}
