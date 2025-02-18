package tests.testsUi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import tests.BaseTest;
import pages.MainPage;
import pages.SubmittingForm;

public class DemoQaTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("helpers.TestValuesUi#isDemoQaTestData")
    @DisplayName("Заполнение всех полей у формы валидными значениями")
    public void fillingFormIsPositive(String firstName, String lastName, String email, String gender, String mobile, String date, String address, String subject, String hobbies, String state, String city) {
        SubmittingForm submittingForm = new MainPage()
                .enterInfoPerson(firstName, lastName, email, gender, mobile, date, address)
                .enterSubjects(subject)
                .enterHobbies(hobbies)
                .enterState(state)
                .enterCity(city)
                .enterPicture()
                .openSubmittingForm();
        assertTrue(submittingForm.getSubmittingForm().isDisplayed());
        assertEquals(firstName + " " + lastName, submittingForm.getStudentName());
        assertEquals(email, submittingForm.getStudentEmail());
        assertEquals(gender, submittingForm.getGender());
        assertEquals(mobile, submittingForm.getMobile());
        assertEquals(date, submittingForm.getDateOfBirth());
        assertEquals(address, submittingForm.getAddress());
        assertEquals(subject, submittingForm.getSubjects());
        assertEquals(hobbies, submittingForm.getHobbies());
        assertEquals("test.jpg", submittingForm.getPicture());
        assertEquals(state + " " + city, submittingForm.getStateAndCity());
    }

    @ParameterizedTest
    @MethodSource("helpers.TestValuesUi#isDemoQaTestDataRequiredValues")
    @DisplayName("Заполнение формы только обязательными полями")
    public void fillingFormWithRequiredValues(String firstName, String lastName, String gender, String mobile) {
        SubmittingForm submittingForm = new MainPage()
                .enterInfoPersonRequiredValues(firstName, lastName, gender, mobile)
                .openSubmittingForm();
        assertTrue(submittingForm.getSubmittingForm().isDisplayed());
        assertEquals(firstName + " " + lastName, submittingForm.getStudentName());
        assertEquals(gender, submittingForm.getGender());
        assertEquals(mobile, submittingForm.getMobile());
        assertNotNull(submittingForm.getDateOfBirth());
        assertAll(
                () -> submittingForm.getAddress().isEmpty(),
                () -> submittingForm.getHobbies().isEmpty(),
                () -> submittingForm.getStateAndCity().isEmpty(),
                () -> submittingForm.getSubjects().isEmpty(),
                () -> submittingForm.getStudentEmail().isEmpty(),
                () -> submittingForm.getPicture().isEmpty()
        );
    }

    @ParameterizedTest
    @MethodSource("helpers.TestValuesUi#isDemoQaTestDataNotValid")
    @DisplayName("Заполнение полей у формы невалидными значениями")
    public void fillingFormIsNegative(String firstName, String lastName, String email, String gender, String mobile, String date, String address, String subject, String hobbies, String state, String city) {
        SubmittingForm submittingForm = new MainPage()
                .enterInfoPerson(firstName, lastName, email, gender, mobile, date, address)
                .enterSubjects(subject)
                .enterHobbies(hobbies)
                .enterState(state)
                .enterCity(city)
                .enterPicture()
                .openSubmittingForm();
        assertFalse(submittingForm.getSubmittingForm().isDisplayed());
    }

    @Test
    @DisplayName("Заполнение формы пустыми значениями")
    public void fillingFormWithEmptyValues() {
        MainPage mainPage = new MainPage();
        SubmittingForm submittingForm = new SubmittingForm();
        assertFalse(submittingForm.getSubmittingForm().isDisplayed());
        assertEquals("rgba(255, 255, 255, 1)", mainPage.getLastNameElement().getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", mainPage.getFirstNameElement().getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", mainPage.getUserMobileElement().getCssValue("background-color"));
    }
}
