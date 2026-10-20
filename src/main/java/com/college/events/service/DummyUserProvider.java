package com.college.events.service;

import com.college.events.model.User;
import com.college.events.model.Role;

/**
 * Provides a hardcoded dummy user so Bhumika can develop registration features
 * before Azeem's authentication is merged.
 *
 * TODO: Remove this class after real login is integrated.
 *
 * @author Bhumika (temporary)
 */
public class DummyUserProvider {

    private DummyUserProvider() {}

    /**
     * Returns a dummy attendee user for development/testing.
     */
    public static User getDummyUser() {
        User dummy = new User();
        dummy.setId(999L);
        dummy.setFullName("Dummy Attendee");
        dummy.setEmail("dummy@college.com");
        dummy.setPassword("encoded-password");
        dummy.setRole(Role.ATTENDEE);
        return dummy;
    }
}
