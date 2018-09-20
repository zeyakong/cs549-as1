import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation for the application interface. The state variable called CAPACITY is
 * the lock-and-key capacity of the system, default as 200.It indicates the the system allows the
 * number of keys and locks when it run.The variables users, keys and locks are the data storage of this system.
 * Both are List and the index of the list will be used by a state variable map as the relationship table.
 * The map is a two-dimensional array which stores the relationship between the keys and the locks.
 * For example, map[1][2] = 1 means that the key 1 can open lock 2(1 and 2 both are their index of the keys List and the locks List);
 * map[3][4] = 0 means that the key 3 can not open the lock 4.
 *
 * @author Zeya Kong.
 * created on Sep 10, 2018
 */
public class ApplicationImpl implements Application {
    private static final int CAPACITY = 200;
    private List<User> users;
    private List<Key> keys;
    private List<Lock> locks;
    //int[key][lock] = 0: can not open | 1 : can open
    private int[][] map;

    /**
     * The following is a constructor for this class. It will initialize state variables:
     * users, keys and locks are ArrayList. map is a array.
     */
    public ApplicationImpl() {
        users = new ArrayList<>();
        keys = new ArrayList<>();
        locks = new ArrayList<>();
        map = new int[CAPACITY][CAPACITY];
    }

    /**
     * The following method implement the method to search a User by using the userId. If the userId
     * is existing, it will return the user object. NULL Otherwise.
     *
     * @param userId the userId of the user to be searched.
     * @return it will return the user object for this userId. NULL Otherwise.
     */
    @Override
    public User searchAUserById(String userId) {
        for (User u : users) {
            //compare the userId to find the user.
            if (u.getUserId().equals(userId)) {
                return u;
            }
        }
        return null;
    }

    /**
     * The following method implement the method to search a Key by using the keyId. If the keyId
     * is right, it will return the Key object. NULL Otherwise.
     *
     * @param keyId the keyId of the key to be searched.
     * @return it will return the Key object for this keyId. NULL Otherwise.
     */
    @Override
    public Key searchAKeyById(String keyId) {
        for (Key key : keys) {
            //compare the keyId to find the Key.
            if (key.getKeyId().equals(keyId)) return key;
        }
        return null;
    }

    /**
     * The following method implement the method to search a Lock by using the door name. If the door name
     * is right, it will return the Lock object. NULL Otherwise.
     *
     * @param door the door name of the lock to be searched.
     * @return it will return the Lock object for this door. NULL Otherwise.
     */
    @Override
    public Lock searchALockByDoor(String door) {
        for (Lock lock : locks) {
            //compare the door name to find the Lock.
            if (lock.getDoor().equals(door)) return lock;
        }
        return null;
    }

    /**
     * The following method implement the method to search a Lock by using the lockId. If the lockId
     * is right, it will return the Lock object. NULL Otherwise.
     *
     * @param lockId the lockId of the lock to be searched.
     * @return it will return the Lock object for this lockId. NULL Otherwise.
     */
    @Override
    public Lock searchALockById(String lockId) {
        for (Lock lock : locks) {
            //compare the door name to find the Lock.
            if (lock.getLockId().equals(lockId)) return lock;
        }
        return null;
    }

    /**
     * the following method will print all the keys' information on command line. For
     * each key ,the information contains keyId and all the locks it can open. If the Key can not
     * open at least one lock, this function will show invalid information for this key.
     */
    @Override
    public void displayAllKeys() {
        System.out.println("\nAll keys:");
        //Traversing the whole key list.
        for (int i = 0; i < keys.size(); i++) {
            Key key = keys.get(i);
            //check if the key is valid or not.
            if (isValidKey(key.getKeyId())) {
                //valid key must can open at least one lock. print this information.
                System.out.println("The key: " + key.getKeyId() + " can open lock(s):");
                //Traversing the relationship map to find which lock can be opened.
                for (int j = 0; j < map[i].length; j++) {
                    //value is more than 0 means that this key i can open this lock j.
                    if (map[i][j] > 0) {
                        //print this information.
                        System.out.println("LockID:" + locks.get(j).getLockId() + " ,door: " + locks.get(j).getDoor());
                    }
                }
                System.out.println();
            } else {
                //print the invalid key information.
                System.out.println("The key: " + key.getKeyId() + "is not valid because it can't open any one lock.");
            }
        }
    }

    /**
     * the following method will print all the users' information on command line, which contains
     * userId and his/her key set. For each user, the key set only show the keyId information.
     */
    @Override
    public void displayAllUsers() {
        //Traversing all the user list.
        for (User u : users) {
            // for one user, get his/her keys' list.
            List<Key> keys = u.getKeys();
            System.out.println("\nThe User:" + u.getUserId() + " has key(s): ");
            //Traversing the key list to print all keys' information.
            for (Key key : keys) {
                System.out.print(key.getKeyId() + "  ");
            }
        }
        System.out.println();
    }

    /**
     * the following method will print all the locks' information on command line, which contains
     * lockId and door name for this lock. For each lock, it shows all the keys' keyId which
     * can open this lock.
     */
    @Override
    public void displayAllLocks() {
        System.out.println("\nAll locks:");
        //Traversing the whole lock list.
        for (int i = 0; i < locks.size(); i++) {
            //get the current lock i;
            Lock lock = locks.get(i);
            // check if the lock i is valid or not.
            if (isValidLock(lock.getLockId())) {
                System.out.println("The lock: " + lock.getLockId() + ",door:" + lock.getDoor() + " has key(s):");
                for (int j = 0; j < map.length; j++) {
                    //means lock i can be opened by key j.
                    if (map[j][i] > 0) {
                        System.out.println("KeyID:" + keys.get(j).getKeyId());
                    }
                }
                System.out.println();
            } else {
                //print the invalid lock information.
                System.out.println("The lock: " + lock.getLockId() + "is not valid because no key can open it.\n");
            }
        }
    }

    /**
     * The following method will take away a key from a user. The key must assign to this user before,
     * otherwise it will return false.
     *
     * @param keyId  the keyId of the key to be changed.
     * @param userId the userId of the user to be taken away a key.
     * @return true: success; false: keyId/userId is not existed.Or the key does not belong to this user.
     */
//    @Override
//    public boolean takeAwayAKeyFromUser(String keyId, String userId) {
//        //check if the key and user is existed or not.
//        if (searchAKeyById(keyId) != null && searchAUserById(userId) != null) {
//            //get current key and user object.
//            Key key = searchAKeyById(keyId);
//            User user = searchAUserById(userId);
//            //remove this key from this user.
//            return user.getKeys().remove(key);
//        } else return false;
//    }

    @Override
    public boolean takeAwayAKeyFromUser(String keyId, String userId) {
        //check if the key and user is existed or not.
        User user;
        Key key;
        if ((key = searchAKeyById(keyId)) != null &
                (user =  searchAUserById(userId)) != null) {
            //remove this key from this user.
            return user.getKeys().remove(key);
        } else return false;
    }


    /**
     * The following method will take away a key from a lock. The key must assign to this lock before,
     * otherwise it will return false.
     *
     * @param keyId  the keyId of the key to be changed.
     * @param lockId the lockId of the lock to be taken away a key.
     * @returntrue: success; false: keyId/lockId is not existed.Or the key can't open this lock.
     */
    @Override
    public boolean takeAwayAKeyFromLock(String keyId, String lockId) {
        //check if the key and lock is existed or not.
        if (searchAKeyById(keyId) != null && searchALockById(lockId) != null) {
            //get current key's and lock's index.
            int keyIndex = keys.indexOf(searchAKeyById(keyId));
            int lockIndex = locks.indexOf(searchALockById(lockId));
            //this key and this lock don't have relationship. Can't take away.
            if (map[keyIndex][lockIndex] == 0) return false;
            else {
                //change the table and return true.
                map[keyIndex][lockIndex] = 0;
                return true;
            }
        } else return false;
    }

    /**
     * The following method will assign a key to a user.The user and the key must be created before and the
     * key must not belong to this user because a user can not have a same key twice in this system.
     *
     * @param keyId  the keyId of the key which want to be assigned.
     * @param userId the userId of the user which want to be assigned.
     * @return True: assigning success; false: key/user is not existing or the user already have this
     * key.
     */
    @Override
    public boolean assignAKeyToUser(String keyId, String userId) {
        //check if the userId or keyId is valid or not.
        if (searchAKeyById(keyId) != null && searchAUserById(userId) != null) {
            //get the object key and user.
            Key key = searchAKeyById(keyId);
            User user = searchAUserById(userId);
            //The index is more than -1 means the user's key list has this key. So, it can't add twice.
            if (user.getKeys().indexOf(key) > -1) return false;
            //add this key to user's key list.
            else return user.getKeys().add(key);
        } else return false;
    }

    /**
     * The following method is to assign a key and a lock a relationship(relationship means
     * this key can open that lock).
     *
     * @param keyId  the keyId of the key which want to be assigned.
     * @param lockId the lockId of the lock which want to be assigned.
     * @return True: assigning success; false: key/lock is not existing or the key and
     * the lock already has a relationship.
     */
    @Override
    public boolean assignAKeyToLock(String keyId, String lockId) {
        //check if the key and lock are existed or not.
        if (searchAKeyById(keyId) != null && searchALockById(lockId) != null) {
            //get the index of the key and the lock named as keyIndex and lockIndex.
            int keyIndex = keys.indexOf(searchAKeyById(keyId));
            int lockIndex = locks.indexOf(searchALockById(lockId));
            //map value represents the relationship. If the value is more than zero, it means this key
            //has been assigned to this lock before.
            if (map[keyIndex][lockIndex] > 0) return false;
            else {
                //change the map table to store this information
                map[keyIndex][lockIndex] = 1;
                return true;
            }
        } else return false;
    }

    /**
     * The following method is to create a new User. Notices that the userId must be unique.
     *
     * @param userId   Unique identification for a user.
     * @param password user's password.
     */
    @Override
    public void createUser(String userId, String password) {
        //check if the userId is unique or not
        if (searchAUserById(userId) == null) {
            users.add(new User(userId, password));
        } else {
            System.out.println("Error: this Id has been used!");
        }
    }

    /**
     * The following method is to create a new Lock. Notices that the keyId and door must be unique.
     *
     * @param lockId Unique identification for a lock.
     * @param door   Unique identification for a lock.
     */
    @Override
    public void createLock(String lockId, String door) {
        for (Lock lock : locks) {
            //both lockId and door must be not used before.
            if (lock.getLockId().equals(lockId) || lock.getDoor().equals(door)) {
                System.out.println("Error: this Id or door has been used!");
                //The return statement is used to stop the function.
                return;
            }
        }
        //create the lock object and add it into locks list.
        locks.add(new Lock(lockId, door));
    }

    /**
     * The following method is to create a new key. Notices that the keyId must be unique.
     *
     * @param keyId Unique identification for a key.
     */
    @Override
    public void createKey(String keyId) {
        //check the keyId.
        if (searchAKeyById(keyId) == null) {
            //add this new Key object into keys list.
            keys.add(new Key(keyId));
        } else {
            System.out.println("Error: this Id has been used!");
        }
    }

    /**
     * The following function is to check if the key can open least one lock.If not, it means
     * that the key is invalid.
     *
     * @param keyId the keyId of the Key want to be checked.
     * @return True: valid; False: invalid
     */
    private boolean isValidKey(String keyId) {
        //first, check if the keyId is existing or not.
        if (searchAKeyById(keyId) != null) {
            //got the key object index called index.
            int index = keys.indexOf(searchAKeyById(keyId));
            //search the map.
            for (int i = 0; i < map[0].length; i++) {
                //if the value is more than zero,it means there is a lock can be opened by this key
                if (map[index][i] > 0) return true;
            }
            return false;
        } else return false;
    }

    /**
     * The following function is to check if the lock can be opened by at least one key.If not, it means
     * that the lock is invalid.
     *
     * @param lockId the lockId of the lock want to be checked.
     * @return True: valid; False: invalid
     */
    private boolean isValidLock(String lockId) {
        //first, check if the lockId is existing or not.
        if (searchALockById(lockId) != null) {
            //got the lock object index called index.
            int index = locks.indexOf(searchALockById(lockId));
            //search the map.
            for (int i = 0; i < map.length; i++) {
                //if the value is more than zero,it means there is a key can open this lock.
                if (map[i][index] > 0) return true;
            }
            return false;
        } else return false;
    }
}
