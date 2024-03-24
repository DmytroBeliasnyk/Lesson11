package ua.kiev.prog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    // JPQL
    @Query("SELECT c FROM Contact c WHERE c.group = :group")
    List<Contact> findByGroup(@Param("group") Group group, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Contact c WHERE c.group = :group")
    long countByGroup(@Param("group") Group group);

    @Query("SELECT COUNT(c) FROM Contact c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    long countByPattern(@Param("pattern") String pattern);

    @Query("SELECT c FROM Contact c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    List<Contact> findByPattern(@Param("pattern") String pattern, Pageable pageable);

    @Query("UPDATE Contact c SET c.group = :group WHERE c.id = :contactId")
    void updateGroupById(@Param("contactId") long contactId, @Param("group") Group group);

    // List<Contact> findByNameOrEmailOrderById(String name, String email);
    // List<Contact> findByNameAndEmail(String name, String email);
}
