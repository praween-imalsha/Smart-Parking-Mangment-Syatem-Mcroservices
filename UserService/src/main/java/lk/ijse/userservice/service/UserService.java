package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO save(UserDTO dto);
    UserDTO update(UserDTO dto);
    List<UserDTO> getAll();
    UserDTO getById(int id);
}
