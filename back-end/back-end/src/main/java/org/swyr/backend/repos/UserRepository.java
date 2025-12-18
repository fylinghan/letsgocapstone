package org.swyr.backend.repos;

import org.swyr.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link User} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations
 * such as saving, updating, deleting, and finding users by their primary key.
 * </p>
 *
 * <p>
 * The primary key for {@link User} is the user's email, represented as a {@link String}.
 * </p>
 *
 * This repository is annotated with {@link Repository}, making it a Spring-managed
 * bean that can be injected into services for database operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}