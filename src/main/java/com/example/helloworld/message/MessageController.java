package com.example.helloworld.message;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4040")
@RestController
@RequestMapping("api/messages")
public class MessageController {
  private final MessageService service;

  public MessageController(MessageService service) {
    this.service = service;
  }

  @GetMapping("/public")
  public Message getPublicMessage() {
    return service.getPublicMessage();
  }

  @GetMapping("/protected")
  public Message getProtectedMessage() {
    return service.getProtectedMessage();
  }

  @GetMapping("/admin")
  public Message getAdminMessage() {
    return service.getAdminMessage();
  }
}
