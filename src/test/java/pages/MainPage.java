package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import endpoints.Endpoints;
import lombok.Getter;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class MainPage {
    private SelenideElement firstNameElement = $("#firstName");
    private SelenideElement lastNameElement = $("#lastName");
    private SelenideElement userEmailElement = $("#userEmail");
    private SelenideElement userMobileElement = $("#userNumber");
    private SelenideElement dateOfBirthElement = $("#dateOfBirthInput");
    private SelenideElement userSubjectsElement = $("#subjectsInput");
    private SelenideElement currentAddressElement = $("#currentAddress");
    private SelenideElement subjectsEnglishElement = $x("//div[@class=\"subjects-auto-complete__option subjects-auto-complete__option--is-focused css-1n7v3ny-option\"]");
    private SelenideElement stateElement = $("#state");
    private SelenideElement cityElement = $("#city");
    private SelenideElement buttonSubmit = $("#submit");
    private SelenideElement pictureElement = $("#uploadPicture");
    private ElementsCollection genderElement = $$(".custom-control.custom-radio.custom-control-inline");
    private ElementsCollection hobbiesElement = $$(".custom-control.custom-checkbox.custom-control-inline");
    private ElementsCollection menuCityElement = $$(".css-26l3qy-menu > div > div");
    private ElementsCollection menuStateElement = $$(".css-11unzgr > div");

    public MainPage() {
        open(Endpoints.BASE_URL_DEMO_QA);
    }

    public MainPage enterInfoPerson(String firstName, String lastName, String email, String gender, String mobile, String dateOfBirth, String address) {
        firstNameElement.setValue(firstName);
        lastNameElement.setValue(lastName);
        userEmailElement.setValue(email);
        genderElement.get(choiceGender(gender)).click();
        userMobileElement.sendKeys(mobile);
        dateOfBirthElement.sendKeys(Keys.HOME, Keys.SHIFT, Keys.END);
        dateOfBirthElement.sendKeys(dateOfBirth, Keys.ENTER);
        currentAddressElement.sendKeys(address);
        return this;
    }

    public MainPage enterInfoPersonRequiredValues(String firstName, String lastName, String gender, String mobile) {
        firstNameElement.setValue(firstName);
        lastNameElement.setValue(lastName);
        genderElement.get(choiceGender(gender)).click();
        userMobileElement.sendKeys(mobile);
        return this;
    }

    public MainPage enterSubjects(String subject) {
        userSubjectsElement.sendKeys(subject);
        subjectsEnglishElement.click();
        return this;
    }

    public MainPage enterHobbies(String hobbies) {
        hobbiesElement.get(choiceHobbies(hobbies)).click();
        return this;
    }

    public MainPage enterState(String state) {
        stateElement.click();
        menuStateElement.get(choiceState(state)).click();
        return this;
    }

    public MainPage enterCity(String city) {
        cityElement.click();
        menuCityElement.get(choiceCity(city)).click();
        return this;
    }

    public MainPage enterPicture() {
        String path = "src/main/resources/test.jpg";
        File file = new File(new File(path).getAbsolutePath());
        pictureElement.sendKeys(file.getAbsolutePath());
        return this;
    }

    public SubmittingForm openSubmittingForm() {
        buttonSubmit.click();
        return new SubmittingForm();
    }

    private int choiceGender(String gender) {
        int result = -1;
        switch (gender) {
            case "Male":
                result = 0;
                break;
            case "Female":
                result = 1;
                break;
            case "Other":
                result = 2;
                break;
        }
        return result;
    }

    private int choiceHobbies(String hobbies) {
        int result = -1;
        switch (hobbies) {
            case "Sports":
                result = 0;
                break;
            case "Reading":
                result = 1;
                break;
            case "Music":
                result = 2;
                break;
        }
        return result;
    }

    private int choiceState(String state) {
        int result = -1;
        switch (state) {
            case "NCR":
                result = 0;
                break;
            case "Uttar Pradesh":
                result = 1;
                break;
            case "Haryana":
                result = 2;
                break;
            case "Rajasthan":
                result = 3;
                break;
        }
        return result;
    }

    private int choiceCity(String city) {
        int result = -1;
        if (city == "Jaipur" || city == "Delhi") {
            result = 0;
        } else if (city == "Jaiselmer" || city == "Gurgaon") {
            result = 1;
        } else {
            result = 2;
        }
        return result;
    }
}
