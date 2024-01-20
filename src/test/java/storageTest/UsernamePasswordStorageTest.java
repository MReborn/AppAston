package storageTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import com.maximbuza.appaston.storage.UsernamePasswordStorage;
import org.junit.Test;


public class UsernamePasswordStorageTest extends UsernamePasswordStorage {
    public static ChangerPasswordRequestDTO changerPasswordRequestDTO;

    @Test
    public void existUser() {
        assertTrue(isUserExist("Max"));
    }

    @Test
    public void correctPassword() {
        assertTrue(isPasswordMatch("Boot", "11111"));
    }


    @Test
    public void changePasswordTest() {
        changerPasswordRequestDTO = new ChangerPasswordRequestDTO();
        changerPasswordRequestDTO.setUsername("Max");
        changerPasswordRequestDTO.setOldPassword("12345");
        changerPasswordRequestDTO.setNewPassword("123456");
        String successChangePasswordResultMessage = "Password was changed successfully.";
        assertEquals(changePassword(changerPasswordRequestDTO),successChangePasswordResultMessage);

    }
}
