package com.spring.techpractica.application.notification;

import com.spring.techpractica.core.notification.entity.Notification;
import com.spring.techpractica.core.user.User;
import org.springframework.stereotype.Service;

@Service
public class CreateNotificationUseCase {



    public Notification execute(User user, String title, String content) {
        Notification notification = new Notification(title, content, user, false);

    }
}