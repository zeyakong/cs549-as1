
/*
 * This class represents a key entity in the lock-key application. Each key
 * has a keyId modeled as strings and case sensitive. The keyId is the key unique identification.
 * The relationship between the Key and the Lock is not stored in this class.
 *
 * @author Zeya Kong.
 * created on Sep 10, 2018
 */
public class Key {
    private String keyId;

    /**
     * The following is a constructor for this class. It accepts one string as
     * parameters and assigns the values for the state variable keyId.
     * @param keyId   the keyId to be assigned.
     */
    public Key(String keyId){
        this.keyId = keyId;
    }

    /**
     * The following method allows a user to get the keyId of the Key.
     *
     * @return the keyId of the Key.
     */
    public String getKeyId() {
        return keyId;
    }

}
