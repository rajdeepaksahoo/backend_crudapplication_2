package com.backend_crudapplication_1.backend_crudapplication_1;

import com.backend_crudapplication_1.backend_crudapplication_1.UserEntity.UserEntity;
import com.backend_crudapplication_1.backend_crudapplication_1.controller.UserController;
import com.backend_crudapplication_1.backend_crudapplication_1.service.UserServiceImp;
import com.backend_crudapplication_1.backend_crudapplication_1.usernotfound_exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	@InjectMocks
	UserController controller;

	@Mock
	UserServiceImp service;

	@Test
	void testAdd() {
		UserEntity user = new UserEntity();
		user.setUserId(1L);
		user.setPassword("123");
		user.setFirstName("a");
		user.setFirstName("b");
        user.setEmailId("sahoorajdeepak3@gmail.com");

		Map<Long, HttpStatus> map = new HashMap<>();
		map.put(user.getUserId(), HttpStatus.CREATED);

		Mockito.when(service.addUser(user)).thenReturn(user);

		ResponseEntity<Map<Long, HttpStatus>> expectedResponse = ResponseEntity.ok(map);
		ResponseEntity<Map<Long, HttpStatus>> actualResponse = controller.add(user);

		Assertions.assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void testViewAll() {
		List<UserEntity> list = new ArrayList<>();
		UserEntity user = new UserEntity();
		user.setUserId(1L);
		user.setPassword("123");
		user.setFirstName("a");
		user.setFirstName("b");
        user.setEmailId("sahoorajdeepak3@gmail.com");
		list.add(user);

		Mockito.when(service.listOfAllUsers()).thenReturn(list);

		ResponseEntity<List<UserEntity>> expected = ResponseEntity.ok(list);
		Assertions.assertEquals(expected, controller.listResponseEntity());
	}

	@Test
	public void deleteUserTest() {
		Long userId = 1l;
		Mockito.when(service.deleteUser(userId)).thenReturn(userId);
		Assertions.assertEquals(ResponseEntity.ok(userId), controller.delete(userId));
	}

	@Test
	public void deleteUserTestError() {
		Long userId = 1l;
		Mockito.when(service.deleteUser(userId)).thenReturn(null);
		Assertions.assertEquals(ResponseEntity.badRequest().body(null), controller.delete(userId));
	}

	@Test
	public void updateTest() {
		UserEntity user = new UserEntity();
		user.setUserId(1L);
		user.setPassword("123");
		user.setFirstName("a");
		user.setLastName("b");
        user.setEmailId("sahoorajdeepak3@gmail.com");

		Mockito.when(service.updateUserEntity(user)).thenReturn(user);
		Assertions.assertEquals(ResponseEntity.ok(user), controller.update(user));
	}

	@Test
	public void searchTest() throws UserNotFoundException {
		UserEntity user = new UserEntity();
		user.setUserId(1L);
		user.setPassword("123");
		user.setFirstName("a");
		user.setLastName("b");
        user.setEmailId("sahoorajdeepak3@gmail.com");

		Mockito.when(service.searchUser(1L)).thenReturn(user);
		Assertions.assertEquals(ResponseEntity.ok(user), controller.search(1L));
	}
	@Test
	public void searchUser_UserNotFoundExceptionTest() {
		Long userId = 1L;
		Mockito.when(service.searchUser(userId)).thenReturn(null);

		// Assert that UserNotFoundException is thrown
		UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class, () -> {
			controller.search(userId);
		});

		// Assert the exception message
		String expectedMessage = "User Is Not Available";
		String actualMessage = exception.getMessage();
		Assertions.assertEquals(expectedMessage, actualMessage);
	}

}