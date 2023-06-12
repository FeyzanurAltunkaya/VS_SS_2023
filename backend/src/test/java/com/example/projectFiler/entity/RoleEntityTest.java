package com.example.projectFiler.entity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RoleEntityTest {

    @Test
    public void testRoleEntity() {
        // Erstelle eine Instanz der RoleEntity-Klasse
        RoleEntity role = new RoleEntity(ERole.ROLE_ADMIN);

        // Überprüfe, ob die ID null ist, da sie automatisch generiert wird
        assertNull(role.getId());

        // Überprüfe, ob der Name korrekt gesetzt wurde
        assertEquals(ERole.ROLE_ADMIN, role.getName());

        // Setze eine ID
        Integer id = 1;
        role.setId(id);

        // Überprüfe, ob die ID korrekt gesetzt wurde
        assertEquals(id, role.getId());
    }
}
