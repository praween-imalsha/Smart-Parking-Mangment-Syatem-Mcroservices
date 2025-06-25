package lk.ijse.userservice.controller;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService service;

    @PostMapping
    public UserDTO save(@RequestBody UserDTO dto) {
        return service.save(dto);
    }

    @PutMapping
    public UserDTO update(@RequestBody UserDTO dto) {
        return service.update(dto);
    }

    @GetMapping
    public List<UserDTO> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO byId(@PathVariable int id) {
        return service.getById(id);
    }
}
