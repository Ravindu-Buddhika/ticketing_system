package org.example.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.example.entity.AuditLogs;
import org.example.repository.AuditLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation; // Spring version එක
import org.springframework.transaction.annotation.Transactional; // Spring version එක
import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect{

    @Autowired
    private AuditLogsRepository auditLogRepository;

    @AfterThrowing(pointcut = "execution(* org.example.service.SeatService.holdSeat(..))", throwing = "ex")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logFailedAttempt(Exception ex) {
        System.out.println(">>> AOP TRIGGERED! Exception: " + ex.getMessage()); // මේක වැටෙනවද බලන්න

        AuditLogs log = new AuditLogs();
        log.setAction("ACTION_FAILED");
        log.setDetails(ex.getMessage());
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.saveAndFlush(log);
    }
}
