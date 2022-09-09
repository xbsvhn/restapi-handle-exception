package com.namvh.restapihandleexception.controller;

import com.namvh.restapihandleexception.model.dto.UserDto;
import com.namvh.restapihandleexception.model.request.CreateUserRequest;
import com.namvh.restapihandleexception.model.request.UpdateUserRequest;
import com.namvh.restapihandleexception.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
@RestController
@Api(value = "User APIs")
public class UserController {
    //private static String UPLOAD_DIR = System.getProperty("user.home") + "/upload";

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get list user", response = UserDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not Authen"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("")
    public ResponseEntity<?> getListUser() {
        List<UserDto> result = userService.getListUser();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Get user info by id", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "No user found"),
            @ApiResponse(code = 500, message = "")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDto result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "Create user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Email already exists in the system"),
            @ApiResponse(code = 500, message = "")
    })
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest req) {
        UserDto result = userService.createUser(req);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "Update user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "No user found"),
            @ApiResponse(code = 500, message = "")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequest req, @PathVariable int id) {
        UserDto result = userService.updateUser(req, id);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "Delete user by id", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "No user found"),
            @ApiResponse(code = 500, message = "")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete success");
    }

    /*@ApiOperation(value = "Upload file", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 500, message="Internal Server Error"),
            @ApiResponse(code = 400, message="Bad request")
    })
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@ModelAttribute("uploadForm") UploadFile form) {
        // Create folder to save file if not exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        MultipartFile fileData = form.getFileData();
        String name = fileData.getOriginalFilename();
        if (name != null && name.length() > 0) {
            try {
                // Create file
                File serverFile = new File(UPLOAD_DIR + "/" + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData.getBytes());
                stream.close();
                return ResponseEntity.ok("/file/"+name);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when uploading");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }

    @ApiOperation(value = "Get file")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/file/{filename}")
    public ResponseEntity<?> download(@PathVariable String filename) {
        File file = new File(UPLOAD_DIR + "/" + filename);
        if (!file.exists()) {
            throw new NotFoundException("File not found");
        }

        UrlResource resource;
        try {
            resource = new UrlResource(file.toURI());
        } catch (MalformedURLException e) {
            throw new NotFoundException("File not found");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }*/
}
