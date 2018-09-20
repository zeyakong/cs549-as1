import java.util.ArrayList;
import java.util.List;

/*
 * This class represents a user in the lock-key application. Each user has a userId and a password,
 * both modeled as strings and case sensitive. The key set named keys.
 * The userId is a unique identifier for a User. A List called keys stores all the key objects which belongs to this user.
 *
 * @author Zeya Kong.
 * created on Sep 10, 2018
 */
public class User {
    private String userId;
    private String password;
    private List<Key> keys;

    /**
     * The following is a constructor for this class. It accepts two strings as
     * parameters and assigns the values for the two state variables. This constructor will also
     * initialize a ArrayList as the key set named keys.
     *
     * @param userId   the userId to be assigned.
     * @param password the password to be assigned.
     */
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
        keys = new ArrayList<>();
    }

    /**
     * The following method allows a user to get the userId.
     *
     * @return the userId of the user.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * The following method allows a user to get the password.
     *
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * The following method allows a user to get all the key he/she own.
     *
     * @return the key List of the keys.
     */
    public List<Key> getKeys() {
        return keys;
    }

    /**
     * The following method allows a user to add a key to his/her key set.
     * @return the result of the adding. If the adding is successful, it will return true,
     * otherwise false.
     */
    public boolean addKey(Key key) {
        return keys.add(key);
    }
}