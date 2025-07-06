package com.example.authservice.repository;

import com.example.authservice.constant.ERole;
import com.example.authservice.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.BindParam;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = """
                SELECT role.name, role.description
                FROM role
                INNER JOIN user_roles as ur ON ur.roles_name = role.name
                WHERE ur.user_id = :id
            """,  nativeQuery = true)
    List<Role> findByUserId(@Param("id") Long id);
}
