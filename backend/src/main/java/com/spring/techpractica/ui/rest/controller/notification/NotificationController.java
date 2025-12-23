package com.spring.techpractica.ui.rest.controller.notification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class NotificationController {

    public ResponseEntity<?> getNotificationByUserId(@RequestParam String userId,
                                                     @RequestParam String lastSeen) {
        return ResponseEntity.ok("");
    }
}
