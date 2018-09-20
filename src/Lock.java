
/*
 * This class represents a lock entity in the lock-key application. Each lock
 * has a lockId and a door name it can open, both modeled as strings and case sensitive.
 * The lockId and door are unique identifier for a Lock because the system required
 * that one door has only one lock. The relationship between the Key and the Lock is not stored in this class.
 *
 * @author Zeya Kong.
 * created on Sep 10, 2018
 */
public class Lock {
    private String lockId;
    private String door;

    /**
     * The following is a constructor for this class. It accepts two strings as
     * parameters and assigns the values for the two state variables.
     * @param lockId   the lockId to be assigned.
     * @param door the door name of this lock
     */
    public Lock(String lockId,String door){
        this.lockId = lockId;
        this.door = door;
    }

    /**
     * The following method allows a user to get the lockId of the lock.
     *
     * @return the lockId of the lock.
     */
    public String getLockId() {
        return lockId;
    }

    /**
     * The following method allows a user to get the door name of the lock.
     *
     * @return the door name of the lock.
     */
    public String getDoor() {
        return door;
    }
}
