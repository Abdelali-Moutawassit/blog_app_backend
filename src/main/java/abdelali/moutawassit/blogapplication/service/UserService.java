package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.UserRequestDTO;
import abdelali.moutawassit.blogapplication.dto.UserResponseDTO;
import abdelali.moutawassit.blogapplication.exception.ResourceNotFoundException;
import abdelali.moutawassit.blogapplication.mapper.UserMapper;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Suppression temporaire de PasswordEncoder
    // private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        User user = UserMapper.toEntity(requestDTO);
        // Ne pas hasher le password pour l’instant (à ne faire qu’en DEV/test !)
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        return UserMapper.toResponseDTO(userRepository.save(user));
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + id));
        return UserMapper.toResponseDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + id));

        existingUser.setFullName(requestDTO.getFullName());
        existingUser.setUsername(requestDTO.getUsername());
        existingUser.setEmail(requestDTO.getEmail());
        existingUser.setBio(requestDTO.getBio());
        existingUser.setProfileImageUrl(requestDTO.getProfileImageUrl());

        if (requestDTO.getPassword() != null && !requestDTO.getPassword().isEmpty()) {
            // Ne pas hasher le password temporairement
            // existingUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
            existingUser.setPassword(requestDTO.getPassword());
        }

        return UserMapper.toResponseDTO(userRepository.save(existingUser));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + id);
        }
        userRepository.deleteById(id);
    }

    public User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + id));
    }
}
