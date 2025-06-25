package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Override
    public UserDTO save(UserDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .contact(dto.getContact())
                .build();
        return toDTO(repo.save(user));
    }

    @Override
    public UserDTO update(UserDTO dto) {
        User user = repo.findById(dto.getId()).orElseThrow();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setContact(dto.getContact());
        return toDTO(repo.save(user));
    }

    @Override
    public List<UserDTO> getAll() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public UserDTO getById(int id) {
        return toDTO(repo.findById(id).orElseThrow());
    }

    private UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .contact(user.getContact())
                .build();
    }
}
