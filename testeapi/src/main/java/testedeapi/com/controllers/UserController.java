package testedeapi.com.controllers;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import testedeapi.com.dto.UserUpdateDto;
import testedeapi.com.exception.UserException;
import testedeapi.com.dto.UserRequestDto;
import testedeapi.com.dto.UserResponseDto;
import testedeapi.com.service.UserService;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>>getAllUsers() {
       return ResponseEntity.ok(service.getAllUser());
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDto) {
        try {
        UserResponseDto createdUser = service.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (UserException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updatedUser(@PathVariable Long id, 
                                                       @RequestBody UserUpdateDto userUpdateDto) {
        UserResponseDto updatedUser = service.updateUser(id, userUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
}
