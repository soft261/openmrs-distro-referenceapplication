package org.openmrs.reference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.groups.BuildTests;
import org.openmrs.reference.page.AppointmentSchedulingPage;
import org.openmrs.reference.page.ManageProviderSchedulesPage;
import org.openmrs.uitestframework.page.LoginPage;

public class LoginAfterSessionExpirationTest extends ReferenceApplicationTestBase {
    private HomePage homePage;

    @Test
    @Category(BuildTests.class)
    public void loginAfterSessionExpires() throws Exception { 
    	//goToLoginPage().loginAsNurse();
    	homePage = new HomePage(page);
    	login();
    	// Go to a new page
    	AppointmentSchedulingPage appointmentSchedulingPage = homePage.goToAppointmentScheduling();
    	assertPage(appointmentSchedulingPage);
        // Wait for 30 minutes for the session timeout. We added 15 seconds just as a cushion.
    	Thread.sleep(1815000);
    	// Try to click on something
        appointmentSchedulingPage.goToManageAppointments();
        // Should be sent back to the login page
	    LoginPage page = this.getLoginPage();
	    this.assertPage(page);
	    page.loginAsAdmin();
        // Logging in again should restore to the previous page.
        assertPage(appointmentSchedulingPage);        
    }
} 
