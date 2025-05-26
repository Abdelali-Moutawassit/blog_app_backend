package abdelali.moutawassit.blogapplication.controller;

import abdelali.moutawassit.blogapplication.dto.MessageRequestDTO;
import abdelali.moutawassit.blogapplication.dto.MessageResponseDTO;
import abdelali.moutawassit.blogapplication.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // Envoyer un message
    @PostMapping("/send")
    public ResponseEntity<MessageResponseDTO> sendMessage(@RequestBody MessageRequestDTO dto) {
        MessageResponseDTO response = messageService.sendMessage(dto);
        return ResponseEntity.ok(response);
    }

    // Obtenir tous les messages entre deux utilisateurs
    @GetMapping("/conversation/{user1Id}/{user2Id}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesBetweenUsers(
            @PathVariable Long user1Id,
            @PathVariable Long user2Id
    ) {
        List<MessageResponseDTO> messages = messageService.getMessagesBetweenUsers(user1Id, user2Id);
        return ResponseEntity.ok(messages);
    }

    // Marquer un message comme lu
    @PutMapping("/read/{messageId}")
    public ResponseEntity<Void> markMessageAsRead(@PathVariable Long messageId) {
        messageService.markAsRead(messageId);
        return ResponseEntity.noContent().build();
    }
}
