package com.example.helloworld.message;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private final Message publicMessage =
      new Message("The API doesn't require an access token to share this message.");

  private final Message protectedMessage =
      new Message("The API successfully validated your access token.");

  private final Message adminMessage =
      new Message("The API successfully recognized you as an admin.");

  public Message getPublicMessage() {
    return publicMessage;
  }

  public Message getProtectedMessage() {
    return protectedMessage;
  }

  public Message getAdminMessage() {
    return adminMessage;
  }
}
