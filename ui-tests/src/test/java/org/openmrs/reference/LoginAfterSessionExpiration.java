package org.openmrs.reference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.reference.page.HomePage;

public class LoginAfterSessionExpiration extends ReferenceApplicationTestBase {
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
        Thread.sleep(1800000);
        homePage.clickOn(FIND_PATIENT_APP_ID);
        
    }
}