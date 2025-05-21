package kz.berkut.testing.demoservice.controller;

import jakarta.validation.Valid;
import kz.berkut.testing.demoservice.dto.MessageRequest;
import kz.berkut.testing.demoservice.dto.MessageResponse;
import kz.berkut.testing.demoservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody MessageRequest req,
                                            Authentication auth) {
        messageService.processOutgoing(auth.getName(), req.body());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MessageResponse>> getMessages(Authentication auth) {
        List<MessageResponse> list = messageService.getMessages(auth.getName());
        return ResponseEntity.ok(list);
    }
}
