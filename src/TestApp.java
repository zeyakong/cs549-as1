/**
 * This is a test class to test the Application.It will test all the functions of the Application.
 *
 * @author Zeya Kong.
 * created on Sep 10, 2018
 */
public class TestApp {
    public static void main(String[] args) {
        Application app = new ApplicationImpl();
        //create 5 keys.
        app.createKey("1");
        app.createKey("2");
        app.createKey("3");
        app.createKey("4");
        app.createKey("5");
        //this might print a error message.
        app.createKey("5");
        //display all keys. all of the keys are invalid because the can't open any lock at that time.
        app.displayAllKeys();

        //create 5 locks.
        app.createLock("a","wings1");
        app.createLock("b","wings2");
        app.createLock("c","wings3");
        app.createLock("d","wings4");
        app.createLock("e","wings5");

        //assign the relationship between keys and locks.
        app.assignAKeyToLock("1","a");
        app.assignAKeyToLock("1","b");
        app.assignAKeyToLock("2","c");
        app.assignAKeyToLock("2","a");
        app.assignAKeyToLock("3","d");
        app.assignAKeyToLock("4","e");
        app.assignAKeyToLock("5","e");
        //this might print error msg. Double assigning.
        app.assignAKeyToLock("5","e");

        //create two users.
        app.createUser("kong1","123");
        app.createUser("kong2","1234");
        app.assignAKeyToUser("1","kong1");
        app.assignAKeyToUser("2","kong1");
        app.assignAKeyToUser("3","kong1");
        app.assignAKeyToUser("4","kong1");
        app.assignAKeyToUser("5","kong2");
        app.assignAKeyToUser("3","kong2");

        //display all information.
        app.displayAllKeys();
        app.displayAllLocks();
        app.displayAllUsers();

        //take away a key from a lock.
        app.takeAwayAKeyFromLock("2","a");
        //display the keys to check above method.
        System.out.println("-------------");
        app.displayAllKeys();

        //take away a key from a user.
        app.takeAwayAKeyFromUser("3","kong1");
        //display the user information to check above method.
        app.displayAllUsers();
    }
}
