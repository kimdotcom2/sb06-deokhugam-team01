package com.sprint.sb06deokhugamteam01.controller;

import com.sprint.sb06deokhugamteam01.dto.notification.CursorPageResponseNotificationDto;
import com.sprint.sb06deokhugamteam01.dto.notification.PageNotificationRequest;
import com.sprint.sb06deokhugamteam01.service.notification.NotificationService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @GetMapping
    public ResponseEntity<CursorPageResponseNotificationDto> getNotifications(@ModelAttribute
        PageNotificationRequest request) {
        return ResponseEntity.ok(notificationService.getNotifications(request, null));
    }

    @PatchMapping("/read-all")
    public ResponseEntity<Void> markAllAsRead(@RequestHeader("Deokhugam-Request-User-ID") UUID userId) {
        log.info("Mark all notifications as read request: userId={}", userId);
        notificationService.updateAll(userId);
        return ResponseEntity.ok().build();
    }
}
