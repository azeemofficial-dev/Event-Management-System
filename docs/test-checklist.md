# Test Checklist

## Pre-Merge Testing (Each Feature Branch)

### Authentication (Azeem)
- [ ] User can register with valid details
- [ ] Duplicate email registration is rejected
- [ ] User can log in with correct credentials
- [ ] Invalid credentials show error message
- [ ] Logged-in user sees their email in the navbar
- [ ] User can log out successfully
- [ ] Admin role is correctly assigned from seed data

### Event Management (Azeem)
- [ ] Public events list page loads with seed data
- [ ] Event search works correctly
- [ ] Event detail page shows all fields
- [ ] Admin can create a new event
- [ ] Admin can edit an existing event
- [ ] Admin can delete an event
- [ ] Non-admin users cannot access admin event pages

### Registration (Bhumika)
- [ ] Logged-in user can register for an event
- [ ] Duplicate registration is prevented
- [ ] Full event shows "Event Full" badge
- [ ] User can cancel registration
- [ ] "My Registrations" page shows user's registrations
- [ ] Registration count updates after register/cancel
- [ ] Non-logged-in users see "Login to Register"

### Admin Dashboard (Jiya)
- [ ] Dashboard shows correct total events count
- [ ] Dashboard shows correct total registrations count
- [ ] Registrations per event table is accurate
- [ ] Attendee list for an event loads correctly
- [ ] Attendee search/filter works
- [ ] Non-admin users cannot access dashboard

### UiPath Automation (Jiya)
- [ ] Bot runs without errors in UiPath Studio
- [ ] Bot produces correct output (report or registrations)
- [ ] README instructions are clear and complete

## Integration Testing (Tuesday — Azeem)
- [ ] All branches merge cleanly into main
- [ ] Application starts without errors
- [ ] Seed data loads correctly (admin + sample events)
- [ ] Full user journey: register → login → browse → register for event → view my registrations → cancel
- [ ] Full admin journey: login → dashboard → create event → view attendees → delete event
- [ ] H2 console is accessible at /h2-console
- [ ] No console errors in browser
