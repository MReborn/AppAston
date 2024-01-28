package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.ChangePasswordDTO;
import com.maximbuza.appaston.dto.UserDTO;
import com.maximbuza.appaston.entity.UserEntity;
import com.maximbuza.appaston.exception.*;
import com.maximbuza.appaston.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;
    UserDTO userDTO;
    UserEntity userEntity;
    ChangePasswordDTO changePasswordDTO;
    List<UserDTO> userDTOList;
    @Before
    public void setUp() {
         userDTO = new UserDTO();
         userEntity = new UserEntity();
         changePasswordDTO = new ChangePasswordDTO();
    }

    @Test(expected = BadDataException.class) // 5 тестов сервиса по входу юзера
    public void signInUser_WhenUsernameIncorrect() {
        userDTO.setUsername("");
        userDTO.setPassword("4444");
        userService.signInUser(userDTO);
    }

    @Test(expected = NotFoundException.class)
    public void signInUser_WhenUsernameNotFound() {
        userDTO.setUsername("Kira");
        userDTO.setPassword("4444");
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(null);
        userService.signInUser(userDTO);
    }

    @Test(expected = BadDataException.class)
    public void signInUser_WhenPasswordIncorrect() {
        userDTO.setUsername("Lil");
        userDTO.setPassword("");
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(userEntity);
        userService.signInUser(userDTO);
    }

    @Test(expected = UnauthorizedException.class)
    public void signInUser_WhenPasswordIsWrong() {
        userDTO.setUsername("Lil");
        userDTO.setPassword("1999");
        userEntity.setUsername("Lil");
        userEntity.setPassword("999");
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(userEntity);
        userService.signInUser(userDTO);
    }

    @Test
    public void signInUser_WhenAllCorrect() {
        userDTO.setUsername("Lil");
        userDTO.setPassword("999");
        userEntity.setUsername("Lil");
        userEntity.setPassword("999");
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(userEntity);
        assertEquals(userService.signInUser(userDTO), "Successful login. Congratulations");
    }


    @Test(expected = BadDataException.class) // 5 тестов сервиса регистрации юзера
    public void signUpUser_WhenUsernameIncorrect() {
        userDTO.setUsername("");
        userDTO.setPassword("123");
        userService.signUpUser(userDTO);
    }

    @Test(expected = ConflictException.class)
    public void signUpUser_WhenUserHasAlreadyAdded() {
        userDTO.setUsername("Lil");
        userDTO.setPassword("123");
        userEntity.setUsername("Lil");
        userEntity.setPassword("999");
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(userEntity);
        userService.signUpUser(userDTO);
    }

    @Test(expected = BadDataException.class)
    public void signUpUser_WhenPasswordIncorrect() {
        userDTO.setUsername("Mks");
        userDTO.setPassword("");
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(null);
        userService.signUpUser(userDTO);
    }

    @Test
    public void signUpUser_WhenUserHasBeenAdded() {
        userDTO.setUsername("Mks");
        userDTO.setPassword("321");
        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(null);
        assertEquals(userService.signUpUser(userDTO), "User has been added:\nlogin: " + userDTO.getUsername() + "\npassword: " + userDTO.getPassword());
    }




    @Test(expected = BadDataException.class) // 6 тестов сервиса смены пароля
    public void changePassword_WhenUsernameIncorrect() {
        changePasswordDTO.setUsername("");
        userService.changePassword(changePasswordDTO);
    }

    @Test(expected = NotFoundException.class)
    public void changePassword_WhenUsernameNotFound() {
        changePasswordDTO.setUsername("Kira");
        when(userRepository.findByUsername(changePasswordDTO.getUsername())).thenReturn(null);
        userService.changePassword(changePasswordDTO);
    }

    @Test(expected = BadDataException.class)
    public void changePassword_WhenOldPasswordIncorrect() {
        changePasswordDTO.setUsername("Lil");
        changePasswordDTO.setOldPassword("");
        changePasswordDTO.setNewPassword("2222");
        userEntity.setUsername("Lil");
        userEntity.setPassword("555");
        when(userRepository.findByUsername(changePasswordDTO.getUsername())).thenReturn(userEntity);
        userService.changePassword(changePasswordDTO);
    }

    @Test(expected = BadDataException.class)
    public void changePassword_WhenNewPasswordIncorrect() {
        changePasswordDTO.setUsername("Lil");
        changePasswordDTO.setOldPassword("3333");
        changePasswordDTO.setNewPassword("");
        userEntity.setUsername("Lil");
        userEntity.setPassword("555");
        when(userRepository.findByUsername(changePasswordDTO.getUsername())).thenReturn(userEntity);
        userService.changePassword(changePasswordDTO);
    }

    @Test(expected = UnauthorizedException.class)
    public void changePassword_WhenPasswordIsWrong() {
        changePasswordDTO.setUsername("Lil");
        changePasswordDTO.setOldPassword("99");
        changePasswordDTO.setNewPassword("2222");
        userEntity.setUsername("Lil");
        userEntity.setPassword("555");
        when(userRepository.findByUsername(changePasswordDTO.getUsername())).thenReturn(userEntity);
        userService.changePassword(changePasswordDTO);
    }

    @Test
    public void changePassword_WhenPasswordWasChanged() {
        changePasswordDTO.setUsername("Lil");
        changePasswordDTO.setOldPassword("999");
        changePasswordDTO.setNewPassword("2222");
        userEntity.setUsername("Lil");
        userEntity.setPassword("999");
        when(userRepository.findByUsername(changePasswordDTO.getUsername())).thenReturn(userEntity);
        assertEquals(userService.changePassword(changePasswordDTO), "Password was changed successfully. Your new login details:\nusername: " +
                changePasswordDTO.getUsername() + "\npassword: " + changePasswordDTO.getNewPassword());
    }

    @Test
    public void getAllUsers_WhenListUsersIsNOTEmpty(){
        userDTOList = new ArrayList<>();
        userDTO.setUsername("Lil");
        userDTO.setPassword("999");
        userDTOList.add(userDTO);
        when(userRepository.getAllUsersFromBd()).thenReturn(userDTOList);
        assertEquals(userService.getAllUsers(),"List of user usernames and passwords:\n"+userDTOList.toString());
    }

    @Test
    public void getAllUsers_WhenListUsersIsEmpty() {
        when(userRepository.getAllUsersFromBd()).thenThrow(new NotFoundException("No users in the repository"));
        assertThrows(NotFoundException.class, () -> userService.getAllUsers());
    }
}
