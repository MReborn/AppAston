package storageTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.maximbuza.appaston.storage.UserPasswordStorage;
import org.junit.BeforeClass;
import org.junit.Test;


public class UserPasswordStorageTest extends UserPasswordStorage {
    @Test
    public void existUser() {
        assertTrue(isUserExist("Max"));
    }

    @Test
    public void correctPassword() {
        assertTrue(isPasswordMatch("Boot","11111"));
    }

}
