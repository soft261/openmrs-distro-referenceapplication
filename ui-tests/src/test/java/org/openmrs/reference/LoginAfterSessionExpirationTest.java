package org.openmrs.reference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.AppointmentSchedulingPage;
import org.openmrs.reference.page.ManageProviderSchedulesPage;

public class LoginAfterSessionExpirationTest extends ReferenceApplicationTestBase {
    private HomePage homePage;

    @Test
    public void loginAfterSessionExpires() throws Exception { 
    	goToLoginPage().loginAsNurse();
    	homePage = new HomePage(page);
    	assertPage(homePage);
    	assertTrue(homePage.isFindAPatientAppPresent());
    	assertTrue(homePage.isActiveVisitsAppPresent());
        assertTrue(homePage.isAppointmentSchedulingAppPresent());
    	assertTrue(homePage.isCaptureVitalsAppPresent());
        assertThat(homePage.numberOfAppsPresent(), is(4));
    	AppointmentSchedulingPage appointmentSchedulingPage = homePage.goToAppointmentScheduling();
    	assertPage(appointmentSchedulingPage);
        Thread.sleep(1800100);
        appointmentSchedulingPage.goToManageAppointments();
        assertPage(getLoginPage());
        goToLoginPage().loginAsNurse();
        assertPage(appointmentSchedulingPage);        
    }
} 