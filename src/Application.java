/**
 * This class represents an application interface which have all of the required methods, which includes:
 * Searching a user/lock/key.
 * Assigning a key to a user.
 * Assigning a key to a lock.
 * Taking away a key from a user.
 * Taking away a key from a lock.
 * Creating a new key/lock/user.
 * Displaying the keys'/locks'/users' information.
 *
 * @author Zeya Kong.
 * created on Sep 10, 2018
 */
public interface Application {
    /**
     * The following method searches the user for a given userId.If
     * found, it returns the User object with the userId; otherwise, it returns
     * NULL.
     * @param userId the userId of the user to be searched.
     * @return the user object containing the userId,password and key set; NULL otherwise.
     */
    User searchAUserById(String userId);

    /**
     * The following method searches the key for a given keyId.If
     * found, it returns the Key object with the keyId; otherwise, it returns
     * NULL.
     * @param keyId the keyId of the key to be searched.
     * @return the key object containing the keyId; NULL otherwise.
     */
    Key searchAKeyById(String keyId);

    /**
     * The following method searches the lock for a given door name.If
     * found, it returns the Lock object with the lockId and door name; otherwise, it returns
     * NULL.
     * @param door the door name of the lock to be searched.
     * @return the lock object containing the lockId,door name; NULL otherwise.
     */
    Lock searchALockByDoor(String door);

    /**
     * The following method searches the lock for a given lockId.If
     * found, it returns the Lock object with the lockId and door name; otherwise, it returns
     * NULL.
     * @param lockId the lockId of the lock to be searched.
     * @return the lock object containing the lockId,door name; NULL otherwise.
     */
    Lock searchALockById(String lockId);

    /**
     * the following method will print all the keys' information on command line. For
     * each key ,the information contains keyId and all the locks which can be opened by this key. If the Key can not
     * open at least one lock, this function will show invalid information for this key.
     */
    void displayAllKeys();

    /**
     * the following method will print all the users' information on command line, which contains
     * userId and his/her key set. For each user, the key set only show the keyId information.
     */
    void displayAllUsers();

    /**
     * the following method will print all the locks' information on command line, which contains
     * lockId and door name for this lock. For each lock, it shows all the keys' keyId which
     * can open this lock.
     */
    void displayAllLocks();

    /**
     * The following method will take away a key from a user. The key must assign to this user before,
     * otherwise it will return false.
     * @param keyId the keyId of the key to be changed.
     * @param userId the userId of the user to be taken away a key.
     * @returntrue: success; false: keyId/userId is not existed.Or the key does not belong to this user.
     */
    boolean takeAwayAKeyFromUser(String keyId, String userId);

    /**
     * The following method will take away a key from a lock. The key must assign to this lock before,
     * otherwise it will return false.
     * @param keyId the keyId of the key to be changed.
     * @param lockId the lockId of the lock to be taken away a key.
     * @returntrue: success; false: keyId/lockId is not existed.Or the key can't open this lock.
     */
    boolean takeAwayAKeyFromLock(String keyId, String lockId);

    /**
     * The following method will assign a key to a user.The user and the key must be created before and the
     * key must not belong to this user because a user can not have a same key twice in this system.
     * @param keyId the keyId of the key which want to be assigned.
     * @param userId the userId of the user which want to be assigned.
     * @return True: assigning success; false: key/user is not existing or the user already have this
     * key.
     */
    boolean assignAKeyToUser(String keyId,String userId);

    /**
     * The following mehtod is to assign a key and a lock a relationship(relationship means
     * this key can open that lock).
     * @param keyId the keyId of the key which want to be assigned.
     * @param lockId the lockId of the lock which want to be assigned.
     * @return True: assigning success; false: key/lock is not existing or the key and
     * the lock already has a relationship.
     */
    boolean assignAKeyToLock(String keyId,String lockId);

    /**
     * The following method is to create a new User. Notices that the userId must be unique.
     * If any error happened, this method will print the error message without throwing a exception.
     * @param userId Unique identification for a user.
     * @param password user's password.
     */
    void createUser(String userId,String password);

    /**
     * The following method is to create a new Lock. Notices that the keyId and door must be unique.
     * If any error happened, this method will print the error message without throwing a exception.
     * @param lockId Unique identification for a lock.
     * @param door Unique identification for a lock.
     */
    void createLock(String lockId,String door);

    /**
     * The following method is to create a new key. Notices that the keyId must be unique.
     *If any error happened, this method will print the error message without throwing a exception.
     * @param keyId Unique identification for a key.
     */
    void createKey(String keyId);
}
