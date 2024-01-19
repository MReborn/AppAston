package storageTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.maximbuza.appaston.storage.UserPasswordStorage;
import org.junit.BeforeClass;
import org.junit.Test;


public class UserPasswordStorageTest extends UserPasswordStorage {
    public static UserPasswordStorage userPasswordStorage;
    @BeforeClass
    public static void init(){userPasswordStorage = new UserPasswordStorage();}

    @Test
    public void existUser() {
        assertTrue(userPasswordStorage.isUserExist("Max"));
    }

    @Test
    public void correctPassword() {
        assertTrue(userPasswordStorage.isPasswordCorrect("Boot","11111"));
    }

}
