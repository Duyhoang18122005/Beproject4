package com.example.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Payment;
import com.example.backend.entity.User;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);

    List<Payment> findByGamePlayerId(Long gamePlayerId);

    List<Payment> findByStatus(Payment.PaymentStatus status);

    List<Payment> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Payment> findByUserIdAndStatus(Long userId, Payment.PaymentStatus status);

    List<Payment> findByUserIdAndTypeOrderByCreatedAtDesc(Long userId, Payment.PaymentType type);

    List<Payment> findByPlayerIdAndTypeOrderByCreatedAtDesc(Long playerId, Payment.PaymentType type);

    List<Payment> findByPlayerIdAndStatusAndEndTimeAfter(Long playerId, Payment.PaymentStatus status,
            LocalDateTime endTime);

    List<Payment> findByUserIdAndStatusAndEndTimeAfter(Long userId, Payment.PaymentStatus status,
            LocalDateTime endTime);

    List<Payment> findByStatusAndEndTimeBefore(Payment.PaymentStatus status, LocalDateTime endTime);

    List<Payment> findByPlayerIdAndStatus(Long playerId, Payment.PaymentStatus status);

    long count();

    long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    Optional<Payment> findByVnpTxnRef(String vnpTxnRef);

    List<Payment> findByGamePlayerIdAndStatusAndEndTimeAfter(Long gamePlayerId, Payment.PaymentStatus status, LocalDateTime endTime);
    List<Payment> findByUserIdOrderByCreatedAtDesc(Long userId);

    @Query("SELECT p FROM Payment p WHERE p.user.id = :userId OR p.player.id = :userId ORDER BY p.createdAt DESC")
    List<Payment> findByUserIdOrPlayerIdOrderByCreatedAtDesc(@Param("userId") Long userId);

    List<Payment> findByTypeOrderByCreatedAtDesc(Payment.PaymentType type);

    @Query("SELECT p FROM Payment p WHERE p.type = com.example.backend.entity.Payment$PaymentType.DONATE AND p.user.id = :userId ORDER BY p.createdAt DESC")
    List<Payment> findDonateHistoryByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT DATE(created_at) as date, COUNT(id) as total FROM payments WHERE status = 'COMPLETED' AND type = 'HIRE' AND created_at BETWEEN :start AND :end GROUP BY DATE(created_at) ORDER BY DATE(created_at)", nativeQuery = true)
    List<Object[]> findDailyOrderCountBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    Payment findByUserAndPlayerAndTypeAndStatus(User user, User player, Payment.PaymentType type, Payment.PaymentStatus status);
}