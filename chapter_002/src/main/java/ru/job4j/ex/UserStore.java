package ru.job4j.ex;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User user = null;
        for (User current : users) {
            if (current.getUsername() != null) {
                if (current.getUsername().equals(login)) {
                    user = current;
                    break;
                }
            }
        }
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername().length() < 3) {
            throw new UserInvalidException("Invalid user");
        } else
            return true;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Ilya Pavlov");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException ui) {
            ui.printStackTrace();
            System.out.println("Invalid user");
        } catch (UserNotFoundException unf) {
            unf.printStackTrace();
            System.out.println("User not found");
        }
    }
}
