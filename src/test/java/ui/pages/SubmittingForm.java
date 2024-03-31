package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SubmittingForm {
    private SelenideElement submittingForm = $(".modal-content");
    private SelenideElement studentName = $x("//td[text()=\"Student Name\"]/following::td[1]");
    private SelenideElement studentEmail = $x("//td[text()=\"Student Email\"]/following::td[1]");
    private SelenideElement gender = $x("//td[text()=\"Gender\"]/following::td[1]");
    private SelenideElement mobile = $x("//td[text()=\"Mobile\"]/following::td[1]");
    private SelenideElement dateOfBirth = $x("//td[text()=\"Date of Birth\"]/following::td[1]");
    private SelenideElement subjects = $x("//td[text()=\"Subjects\"]/following::td[1]");
    private SelenideElement hobbies = $x("//td[text()=\"Hobbies\"]/following::td[1]");
    private SelenideElement picture = $x("//td[text()=\"Picture\"]/following::td[1]");
    private SelenideElement address = $x("//td[text()=\"Address\"]/following::td[1]");
    private SelenideElement stateAndCity = $x("//td[text()=\"State and City\"]/following::td[1]");

    public SelenideElement getSubmittingForm() {
        return submittingForm;
    }
    public String getStudentName() {
        return studentName.getText();
    }

    public String getStudentEmail() {
        return studentEmail.getText();
    }

    public String getGender() {
        return gender.getText();
    }

    public String getMobile() {
        return mobile.getText();
    }

    public String getDateOfBirth() {
        return dateOfBirth.getText();
    }

    public String getSubjects() {
        return subjects.getText();
    }

    public String getHobbies() {
        return hobbies.getText();
    }

    public String getPicture() {
        return picture.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public String getStateAndCity() {
        return stateAndCity.getText();
    }
}
