package com.namvh.restapihandleexception.service;

import com.namvh.restapihandleexception.model.dto.UserDto;
import com.namvh.restapihandleexception.model.request.CreateUserRequest;
import com.namvh.restapihandleexception.model.request.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserDto> getListUser();

    public UserDto getUserById(int id);

    public UserDto createUser(CreateUserRequest req);

    public UserDto updateUser(UpdateUserRequest req, int id);

    public boolean deleteUser(int id);
}
