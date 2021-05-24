package com.example.helloworld.message;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  @PreAuthorize("hasAuthority('read:admin-messages')")
  public Message getAdminMessage() {
    return service.getAdminMessage();
  }
}
