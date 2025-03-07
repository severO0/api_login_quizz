package testedeapi.com.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import testedeapi.com.dto.UserDto;
import testedeapi.com.models.UserModel;
import testedeapi.com.service.UserService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserModel>>getAllUsers() {
       return ResponseEntity.ok(service.getAllUser());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserModel userModel) {
        UserDto createdUser = service.createUser(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

  @PutMapping("/{id}")
    public ResponseEntity<UserDto>updatedUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return service.updateUser(id, userDto)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
}
