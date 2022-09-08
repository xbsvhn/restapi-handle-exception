package com.namvh.restapihandleexception.service;

import com.namvh.restapihandleexception.entity.User;
import com.namvh.restapihandleexception.exception.DuplicateRecordException;
import com.namvh.restapihandleexception.exception.NotFoundException;
import com.namvh.restapihandleexception.model.dto.UserDto;
import com.namvh.restapihandleexception.model.mapper.UserMapper;
import com.namvh.restapihandleexception.model.request.CreateUserRequest;
import com.namvh.restapihandleexception.model.request.UpdateUserRequest;
import com.namvh.restapihandleexception.repo.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
//    private static List<User> users = new ArrayList<User>();
//
//    static {
//        users.add(new User(1, "Nguyễn Thị Mộng Mơ", "mongmo@gmail.com","0987654321","avatar.img","123"));
//        users.add(new User(2, "Bùi Như Lạc", "laclac@gmail.com","0123456789","avatar1.img","123"));
//        users.add(new User(3, "Phan Thị Lỏng Lẻo", "longleo@gmail.com","0987564664","avatar3.img","123"));
//        users.add(new User(4, "Bành Thị Tèo", "teo@gmail.com","0874845455","avatar9.img","123"));
//    }
    @Autowired
    private UserRepository repo;

    @Override
    public List<UserDto> getListUser() {
        ArrayList<UserDto> result = new ArrayList<UserDto>();

        // Convert users -> result
        for (User user : repo.findAll()) {
            result.add(UserMapper.toUserDto(user));
        }

        return result;
    }

    @Override
    public UserDto getUserById(int id) {
        for (User user : repo.findAll()) {
            if (user.getId() == id) {
                return UserMapper.toUserDto(user);
            }
        }

        throw new NotFoundException("No user found");
    }

    @Override
    public UserDto createUser(CreateUserRequest req) {
        // Check email exist
        for (User user : repo.findAll()) {
            if (user.getEmail().equals(req.getEmail())) {
                throw new DuplicateRecordException("Email already exists in the system");
            }
        }

        // Convert CreateUserReq -> User
        User user = UserMapper.toUser(req);
        user.setId(repo.findAll().size()+1);

        // Insert user
        repo.save(user);

        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(UpdateUserRequest req, int id) {
        for (User user : repo.findAll()) {
            if (user.getId() == id) {
                if (!user.getEmail().equals(req.getEmail())) {
                    // Check new email exist
                    for (User tmp : repo.findAll()) {
                        if (tmp.getEmail().equals(req.getEmail())) {
                            throw new DuplicateRecordException("New email already exists in the system");
                        }
                    }
                    user.setEmail(req.getEmail());
                }
                user.setName(req.getName());
                user.setPhone(req.getPhone());
                user.setAvatar(req.getAvatar());
                user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
                repo.save(user);
                return UserMapper.toUserDto(user);
            }
        }

        throw new NotFoundException("No user found");
    }

    @Override
    public boolean deleteUser(int id) {
        for (User user : repo.findAll()) {
            if (user.getId() == id) {
                repo.delete(user);
                return true;
            }
        }

        throw new NotFoundException("No user found");
    }
}
