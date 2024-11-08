package service;

import entities.User;
import entities.Wallet;
import repository.Impl.UserRepositoryImpl;
import repository.Impl.WalletRepositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    public static User loggedInUser;
    List<User> users = UserRepositoryImpl.all() ;
    Scanner scanner = new Scanner(System.in);
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    WalletRepositoryImpl walletRepository = new WalletRepositoryImpl();
    public void userSignUp() throws SQLException {
       while(true) {
           System.out.println("Enter username:");
           String username = scanner.nextLine();
           if (AuthenticationService.isUsernameNew(username)) {
               System.out.println("Enter password:");
               String password = scanner.nextLine();
               User user = new User(username, password);
               user =  userRepository.create(user);
               Wallet wallet = new Wallet(0,user);
               walletRepository.create(wallet);
               break;
           }
       }
    }

    public void userLogin(String username, String password) {
        if(!users.isEmpty()){
            for(User user : users){
                if(username.equals(user.getUsername())){
                    if(password.equals(user.getPassword())){
                        loggedInUser = user;
                        System.out.println("Logged in successfully!!");
                    }
                }
            }
        }

    }

    public void userLogout() {
        if(users.isEmpty()){
            System.out.println("No user logged in!");
        }
        else{
            loggedInUser = null;
        }

    }

}
