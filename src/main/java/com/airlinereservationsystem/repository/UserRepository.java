package com.airlinereservationsystem.repository;

import com.airlinereservationsystem.entity.Ticket;
import com.airlinereservationsystem.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserCredential, String> {

    @Query("SELECT u.userRole FROM UserCredential u WHERE u.email = :email")
    String findUserRoleByEmail(@Param("email") String email);

    @Query("SELECT u.totalBalance FROM UserCredential u WHERE u.email = :email")
    Double findTotalBalanceByEmail(@Param("email") String email);

    @Query("SELECT t FROM Ticket t WHERE t.user.email = :email")
    List<Ticket> findTicketsByUserEmail(@Param("email") String email);

    @Query("SELECT u.name FROM UserCredential u WHERE u.email = :email")
	String findNameByEmail(@Param("email") String email);

}
