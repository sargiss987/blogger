package com.acba.blogger.repository;

import com.acba.blogger.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  User findUserByEmail(String email);

  @Modifying
  @Transactional
  @Query(value = "UPDATE user SET is_enabled=false WHERE id= :id", nativeQuery = true)
  void disableUser(Long id);

  @Modifying
  @Transactional
  @Query(
      value =
          "UPDATE user SET "
              + "first_name = ifNull(:firstName, first_name),"
              + "last_name = ifNull(:lastName, last_name),"
              + "email = ifNull(:email, email),"
              + "user_role_id = ifNull(:roleId, user_role_id),"
              + "password = ifNull(:password, password)"
              + " WHERE id= :id",
      nativeQuery = true)
  void editUser(
      Long id, String firstName, String lastName, String email, Long roleId, String password);
}
