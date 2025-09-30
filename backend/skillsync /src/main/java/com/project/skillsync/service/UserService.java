package com.project.skillsync.service;

import com.project.skillsync.dto.UserDTO;
import com.project.skillsync.exception.DuplicateResourceException;
import com.project.skillsync.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setId(user.getId());
                    dto.setUsername(user.getUsername());
                    dto.setEmail(user.getEmail());

                    return dto;
                });
    }
    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new DuplicateResourceException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

}
