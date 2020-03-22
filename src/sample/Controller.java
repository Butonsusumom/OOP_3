package sample;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import serialization.BinarConverter;
import serialization.JsonConverter;
import serialization.TextConverter;
import tsubulko.entity.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.regex.Pattern;

//-------------------------------VARIABLES AND CONSTANTS-------------------------------------
public class Controller {

    @FXML
    private PieChart pieChart;
    @FXML
    private Button close;
    @FXML
    private AnchorPane analysePanel;
    @FXML
    private AnchorPane mainpanel;
    @FXML
    private Button submitButton1;
    @FXML
    private Button submitButton2;
    @FXML
    private Button savebutton;
    @FXML
    private Button loadbutton;
    @FXML
    private ComboBox saveBox;
    @FXML
    private ComboBox loadBox;
    @FXML
    private Label pos;
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, Integer> id;
    @FXML
    private TableColumn<Person, String> name;
    @FXML
    private TableColumn<Person, String> surname;
    @FXML
    private TableColumn<Person, String> position;
    @FXML
    private AnchorPane addPanel;
    @FXML
    private AnchorPane addManagerPanel;
    @FXML
    private AnchorPane addStudentPanel;
    @FXML
    private AnchorPane addDezPanel;
    @FXML
    private AnchorPane addProgPanel;
    @FXML
    private AnchorPane addTestPanel;
    @FXML
    private TextField date,
            year,
            nameText,
            surnameText,
            email,
            house,
            city,
            street,
            manSal,
            manExp,
            bonus,
            univers,
            facult,
            spec,
            course,
            group,
            progSal,
            progExp,
            progProj,
            dezSal,
            dezExp,
            dezProj,
            testSal,
            testExp,
            testProj;
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox gender;
    @FXML
    private ComboBox positionList;
    @FXML
    private ComboBox manEdu;
    @FXML
    private ComboBox progEdu;
    @FXML
    private ComboBox progCat;
    @FXML
    private ComboBox progSkills;
    @FXML
    private ComboBox dezEdu;
    @FXML
    private ComboBox dezSkills;
    @FXML
    private ComboBox dezType;
    @FXML
    private ComboBox testEdu;
    @FXML
    private ComboBox testType;

    public ObservableList<Person> personData = FXCollections.observableArrayList();
    int idPerson = 0;
    int idEdit = -1;


    //-------------------------------GUI METHODS-------------------------------------
    public Controller() {
    }


    @FXML
    public void onClickMethodSave() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt","*.csv"),
                new FileChooser.ExtensionFilter("Binar Files", "*.dat"),
                new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File selectedFile = fileChooser.showSaveDialog(new Stage());
        if (selectedFile != null) {
            String name = selectedFile.getPath();
            String[] split = name.split("\\.");
            String ext = split[split.length - 1];
            System.out.println(name);
            System.out.println(ext);
            switch (ext) {
                case ("json"):
                    JsonConverter conv = new JsonConverter();
                    conv.serialise(personData, name);
                    break;
                case ("dat"):
                    BinarConverter binar = new BinarConverter();
                    binar.serialise(personData, name);
                    break;
                case ("csv"):
                case ("txt"):
                    TextConverter tex = new TextConverter();
                    tex.serialise(personData, name);
                    break;
            }
        }
    }

    @FXML
    public void onClickMethodLoad() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt","*.csv"),
                new FileChooser.ExtensionFilter("Binar Files", "*.dat"),
                new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            String name = selectedFile.getPath();
            String[] split = name.split("\\.");
            String ext = split[split.length - 1];
            System.out.println(name);
            System.out.println(ext);
            switch (ext) {
                case ("json"):
                    JsonConverter conv = new JsonConverter();
                    personData = conv.deserialise(name);
                    break;
                case ("dat"):
                    BinarConverter binar = new BinarConverter();
                    personData = binar.deserialise(name);
                    break;
                case ("csv"):
                case ("txt"):
                    TextConverter text = new TextConverter();
                    personData = text.deserialise(name);
                    break;
            }
            personTable.setItems(personData);
        }
    }

    @FXML
    public void onClickMethodDelete() {
        Person person = personTable.getSelectionModel().getSelectedItem();
        if (person != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you want to delete item " + person.getId() + " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                deletePerson(person.getId());
                personTable.getItems().remove(person);
            }
        }
    }

    @FXML
    public void onClickMethodAdd() {
        {
            date.setText("");
            year.setText("");
            nameText.setText("");
            surnameText.setText("");
            email.setText("");
            house.setText("");
            city.setText("");
            street.setText("");
            manSal.setText("");
            manExp.setText("");
            bonus.setText("");
            univers.setText("");
            facult.setText("");
            spec.setText("");
            course.setText("");
            group.setText("");
            progSal.setText("");
            progExp.setText("");
            progProj.setText("");
            dezSal.setText("");
            dezExp.setText("");
            dezProj.setText("");
            testSal.setText("");
            testExp.setText("");
            testProj.setText("");

            month.setValue(null);
            gender.setValue(null);
            positionList.setValue(null);
            manEdu.setValue(null);
            progEdu.setValue(null);
            progCat.setValue(null);
            progSkills.setValue(null);
            dezEdu.setValue(null);
            dezSkills.setValue(null);
            dezType.setValue(null);
            testEdu.setValue(null);
            testType.setValue(null);

        }
        submitButton1.setVisible(true);
        submitButton2.setVisible(false);
        addPanel.setVisible(true);
        addStudentPanel.setVisible(false);
        addManagerPanel.setVisible(false);
        addTestPanel.setVisible(false);
        addProgPanel.setVisible(false);
        addDezPanel.setVisible(false);
        positionList.setVisible(true);
        pos.setVisible(true);
    }

    @FXML
    public void onClickMethodEdit() {
        Person person = personTable.getSelectionModel().getSelectedItem();
        if (person != null) {
            idEdit = person.getId();
            submitButton2.setVisible(true);
            submitButton1.setVisible(false);
            addPanel.setVisible(true);
            addManagerPanel.setVisible(person.getPosition().toString().equals("Manager"));
            addStudentPanel.setVisible(person.getPosition().toString().equals("Student"));
            addProgPanel.setVisible(person.getPosition().toString().equals("Programmer"));
            addDezPanel.setVisible(person.getPosition().toString().equals("Designer"));
            addTestPanel.setVisible(person.getPosition().toString().equals("Tester"));
            positionList.setVisible(false);
            pos.setVisible(false);

            {
                date.setText(Integer.toString(person.getDateOfBirth().getDay()));
                year.setText(Integer.toString(person.getDateOfBirth().getYear()));
                nameText.setText(person.getName());
                surnameText.setText(person.getSurname());
                email.setText(person.getEmail());
                house.setText(Integer.toString(person.getAdress().getHouse()));
                city.setText(person.getAdress().getCity());
                street.setText(person.getAdress().getStreet());

                month.setValue(person.getDateOfBirth().getMonth().toString());
                gender.setValue(person.getSex());
            }

            if (personTable.getSelectionModel().getSelectedItem().getPosition().toString() == "Student") {
                if (person instanceof Student) {
                    univers.setText(((Student) person).getUniversity());
                    facult.setText(((Student) person).getFaculty());
                    spec.setText(((Student) person).getSpecialisation());
                    course.setText(Integer.toString(((Student) person).getCourse()));
                    group.setText(((Student) person).getGroup());
                }
            }
            if (personTable.getSelectionModel().getSelectedItem().getPosition().toString() == "Manager") {
                if (person instanceof Manager) {
                    manSal.setText(Double.toString(((Manager) person).getSalary()));
                    manExp.setText(Integer.toString(((Manager) person).getExperiense()));
                    bonus.setText(Double.toString(((Manager) person).getBonus()));
                    manEdu.setValue(((Manager) person).getEducation());
                }
            }
            if (personTable.getSelectionModel().getSelectedItem().getPosition().toString() == "Programmer") {
                if (person instanceof Programmer) {
                    progSal.setText(Double.toString(((Programmer) person).getSalary()));
                    progExp.setText(Integer.toString(((Programmer) person).getExperiense()));
                    progProj.setText(((Programmer) person).getProject());
                    progEdu.setValue(((Programmer) person).getEducation());
                    progCat.setValue(((Programmer) person).getCategory());
                    progSkills.setValue(((Programmer) person).getProgSkills());
                }
            }
            if (personTable.getSelectionModel().getSelectedItem().getPosition().toString() == "Tester") {
                if (person instanceof Tester) {
                    testSal.setText(Double.toString(((Tester) person).getSalary()));
                    testExp.setText(Integer.toString(((Tester) person).getExperiense()));
                    testProj.setText(((Tester) person).getProject());
                    testEdu.setValue(((Tester) person).getEducation());
                    testType.setValue(((Tester) person).getTestType());
                }
            }
            if (personTable.getSelectionModel().getSelectedItem().getPosition().toString() == "Designer") {
                if (person instanceof Designer) {
                    dezSal.setText(Double.toString(((Designer) person).getSalary()));
                    dezExp.setText(Integer.toString(((Designer) person).getExperiense()));
                    dezProj.setText(((Designer) person).getProject());
                    dezEdu.setValue(((Designer) person).getEducation());
                    dezSkills.setValue(((Designer) person).getDezSkills());
                    dezType.setValue(((Designer) person).getDezType());
                }
            }
        } else idEdit = -1;
    }

    @FXML
    public void Close() {
        analysePanel.setVisible(false);
    }

    @FXML
    public void onClickMethodSub1() throws IOException {
        boolean flag = true;
        flag=checkMain(flag);
        Person pers = null;
        if(positionList.getValue()!=null) {
            switch (positionList.getValue().toString()) {
                case ("Manager"):
                    flag=checkManager(flag);
                    if (flag) {
                        pers = new Manager(idPerson++, new Birth(Integer.valueOf(date.getText()), Birth.Month.valueOf(month.getValue().toString()), Integer.valueOf(year.getText())), nameText.getText(), surnameText.getText(), Person.Sex.valueOf(gender.getValue().toString()), email.getText(), new Adress(city.getText(), street.getText(), Integer.valueOf(house.getText())), Person.Position.Manager, Double.valueOf(manSal.getText()), Integer.valueOf(manExp.getText()), Employee.Education.valueOf(manEdu.getValue().toString()), Double.valueOf(bonus.getText()));
                    }
                    break;
                case ("Student"):
                    flag=checkStudent(flag);
                    if (flag) {
                        pers = new Student(idPerson++, new Birth(Integer.valueOf(date.getText()), Birth.Month.valueOf(month.getValue().toString()), Integer.valueOf(year.getText())), nameText.getText(), surnameText.getText(), Person.Sex.valueOf(gender.getValue().toString()), email.getText(), new Adress(city.getText(), street.getText(), Integer.valueOf(house.getText())), Person.Position.Student, univers.getText(), facult.getText(), spec.getText(), Integer.valueOf(course.getText()), group.getText());
                    }
                    break;
                case ("Programmer"):
                    flag=checkProgrammer(flag);
                    if (flag) {
                        pers = new Programmer(idPerson++, new Birth(Integer.valueOf(date.getText()), Birth.Month.valueOf(month.getValue().toString()), Integer.valueOf(year.getText())), nameText.getText(), surnameText.getText(), Person.Sex.valueOf(gender.getValue().toString()), email.getText(), new Adress(city.getText(), street.getText(), Integer.valueOf(house.getText())), Person.Position.Programmer, Double.valueOf(progSal.getText()), Integer.valueOf(progExp.getText()), Employee.Education.valueOf(progEdu.getValue().toString()), progProj.getText(), Programmer.Category.valueOf(progCat.getValue().toString()), Programmer.ProgSkills.valueOf(progSkills.getValue().toString()));
                    }
                    break;
                case ("Tester"):
                    flag=checkTester(flag);
                    if (flag) {
                        pers = new Tester(idPerson++, new Birth(Integer.valueOf(date.getText()), Birth.Month.valueOf(month.getValue().toString()), Integer.valueOf(year.getText())), nameText.getText(), surnameText.getText(), Person.Sex.valueOf(gender.getValue().toString()), email.getText(), new Adress(city.getText(), street.getText(), Integer.valueOf(house.getText())), Person.Position.Tester, Double.valueOf(testSal.getText()), Integer.valueOf(testExp.getText()), Employee.Education.valueOf(testEdu.getValue().toString()), testProj.getText(), Tester.TestType.valueOf(testType.getValue().toString()));
                    }
                    break;
                case ("Designer"):
                    flag=checkDesigner(flag);
                    if (flag) {
                        pers = new Designer(idPerson++, new Birth(Integer.valueOf(date.getText()), Birth.Month.valueOf(month.getValue().toString()), Integer.valueOf(year.getText())), nameText.getText(), surnameText.getText(), Person.Sex.valueOf(gender.getValue().toString()), email.getText(), new Adress(city.getText(), street.getText(), Integer.valueOf(house.getText())), Person.Position.Designer, Double.valueOf(dezSal.getText()), Integer.valueOf(dezExp.getText()), Employee.Education.valueOf(dezEdu.getValue().toString()), dezProj.getText(), Designer.DezSkills.valueOf(dezSkills.getValue().toString()), Designer.DezType.valueOf(dezType.getValue().toString()));
                    }
                    break;
            }

            if (flag) {
                addPanel.setVisible(false);
               // personData.add(pers);
               personTable.getItems().add(pers);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input");
                alert.show();
            }
        }
    }

    @FXML
    public void onClickMethodSub2() {
        boolean flag = true;
        flag=checkMain(flag);
        addPanel.setVisible(false);
        Person man = getPerson(idEdit);
        switch (man.getPosition().toString()) {
            case ("Manager"):
                flag=checkManager(flag);
                if (flag) {
                ((Manager) man).setDateOfBirth(new Birth(Integer.valueOf(date.getText()), Birth.Month.valueOf(month.getValue().toString()), Integer.valueOf(year.getText())));
                ((Manager) man).setName(nameText.getText());
                ((Manager) man).setSurname(surnameText.getText());
                ((Manager) man).setSex(Person.Sex.valueOf(gender.getValue().toString()));
                ((Manager) man).setEmail(email.getText());
                ((Manager) man).setAdress(new Adress(city.getText(), street.getText(), Integer.valueOf(house.getText())));
                ((Manager) man).setSalary(Double.valueOf(manSal.getText()));
                ((Manager) man).setExperiense(Integer.valueOf(manExp.getText()));
                ((Manager) man).setEducation(Employee.Education.valueOf(manEdu.getValue().toString()));
                ((Manager) man).setBonus(Double.valueOf(bonus.getText()));}
                break;
            case ("Student"):
                flag=checkStudent(flag);
                if (flag) {
                ((Student) man).setDateOfBirth(new Birth(Integer.valueOf(date.getText()), Birth.Month.valueOf(month.getValue().toString()), Integer.valueOf(year.getText())));
                ((Student) man).setName(nameText.getText());
                ((Student) man).setSurname(surnameText.getText());
                ((Student) man).setSex(Person.Sex.valueOf(gender.getValue().toString()));
                ((Student) man).setEmail(email.getText());
                ((Student) man).setAdress(new Adress(city.getText(), street.getText(), Integer.valueOf(house.getText())));
                ((Student) man).setUniversity(univers.getText());
                ((Student) man).setFaculty(facult.getText());
                ((Student) man).setSpecialisation(spec.getText());
                ((Student) man).setCourse(Integer.valueOf(course.getText()));
                ((Student) man).setGroup(group.getText());}
                break;
            case ("Programmer"):
                flag=checkProgrammer(flag);
                if (flag) {
                ((Programmer) man).setDateOfBirth(new Birth(Integer.valueOf(date.getText()), Birth.Month.valueOf(month.getValue().toString()), Integer.valueOf(year.getText())));
                ((Programmer) man).setName(nameText.getText());
                ((Programmer) man).setSurname(surnameText.getText());
                ((Programmer) man).setSex(Person.Sex.valueOf(gender.getValue().toString()));
                ((Programmer) man).setEmail(email.getText());
                ((Programmer) man).setAdress(new Adress(city.getText(), street.getText(), Integer.valueOf(house.getText())));
                ((Programmer) man).setSalary(Double.valueOf(progSal.getText()));
                ((Programmer) man).setExperiense(Integer.valueOf(progExp.getText()));
                ((Programmer) man).setEducation(Employee.Education.valueOf(progEdu.getValue().toString()));
                ((Programmer) man).setProject(progProj.getText());
                ((Programmer) man).setCategory(Programmer.Category.valueOf(progCat.getValue().toString()));
                ((Programmer) man).setProgSkills(Programmer.ProgSkills.valueOf(progSkills.getValue().toString()));}
                break;
            case ("Tester"):
                flag=checkTester(flag);
                if (flag) {
                ((Tester) man).setDateOfBirth(new Birth(Integer.valueOf(date.getText()), Birth.Month.valueOf(month.getValue().toString()), Integer.valueOf(year.getText())));
                ((Tester) man).setName(nameText.getText());
                ((Tester) man).setSurname(surnameText.getText());
                ((Tester) man).setSex(Person.Sex.valueOf(gender.getValue().toString()));
                ((Tester) man).setEmail(email.getText());
                ((Tester) man).setAdress(new Adress(city.getText(), street.getText(), Integer.valueOf(house.getText())));
                ((Tester) man).setSalary(Double.valueOf(testSal.getText()));
                ((Tester) man).setExperiense(Integer.valueOf(testExp.getText()));
                ((Tester) man).setEducation(Employee.Education.valueOf(testEdu.getValue().toString()));
                ((Tester) man).setProject(testProj.getText());
                ((Tester) man).setTestType(Tester.TestType.valueOf(testType.getValue().toString()));}
                break;
            case ("Designer"):
               flag=checkDesigner(flag);
                if (flag) {
                ((Designer) man).setDateOfBirth(new Birth(Integer.valueOf(date.getText()), Birth.Month.valueOf(month.getValue().toString()), Integer.valueOf(year.getText())));
                ((Designer) man).setName(nameText.getText());
                ((Designer) man).setSurname(surnameText.getText());
                ((Designer) man).setSex(Person.Sex.valueOf(gender.getValue().toString()));
                ((Designer) man).setEmail(email.getText());
                ((Designer) man).setAdress(new Adress(city.getText(), street.getText(), Integer.valueOf(house.getText())));
                ((Designer) man).setSalary(Double.valueOf(dezSal.getText()));
                ((Designer) man).setExperiense(Integer.valueOf(dezExp.getText()));
                ((Designer) man).setEducation(Employee.Education.valueOf(dezEdu.getValue().toString()));
                ((Designer) man).setProject(dezProj.getText());
                ((Designer) man).setDezSkills(Designer.DezSkills.valueOf(dezSkills.getValue().toString()));
                ((Designer) man).setDezType(Designer.DezType.valueOf(dezType.getValue().toString()));}
                break;
        }
        if (!flag) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input");
            alert.show();
        }
        personTable.refresh();
    }

    @FXML
    public void ChooseAddPanel() {
        if (positionList.getValue() != null) {
            addManagerPanel.setVisible(positionList.getValue().equals("Manager"));
            addStudentPanel.setVisible(positionList.getValue().equals("Student"));
            addProgPanel.setVisible(positionList.getValue().equals("Programmer"));
            addDezPanel.setVisible(positionList.getValue().equals("Designer"));
            addTestPanel.setVisible(positionList.getValue().equals("Tester"));
        }
    }

    @FXML
    public void onClickMethodAnalyse() {
        analysePanel.setVisible(true);
        int st = 0, mn = 0, pg = 0, tst = 0, dz = 0;
        for (Person person :
                personData) {
            switch (person.getPosition().toString()) {
                case ("Student"):
                    st++;
                    break;
                case ("Manager"):
                    mn++;
                    break;
                case ("Programmer"):
                    pg++;
                    break;
                case ("Tester"):
                    tst++;
                    break;
                case ("Designer"):
                    dz++;
                    break;
            }
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Student", st),
                        new PieChart.Data("Manager", mn),
                        new PieChart.Data("Programmer", pg),
                        new PieChart.Data("Tester", tst),
                        new PieChart.Data("Designer", dz));
        pieChart.setData(pieChartData);
    }

    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с четырьмя столбцами.
        id.setCellValueFactory(new PropertyValueFactory("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        surname.setCellValueFactory(new PropertyValueFactory("surname"));
        position.setCellValueFactory(new PropertyValueFactory("position"));
        fillPersonData();
        personTable.getItems().addAll(personData);

        {
            date.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckDate(oldValue, newValue, ChooseDay());
            });
            surnameText.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, surnameText);
            });
            univers.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, univers);
            });
            facult.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, facult);
            });
            spec.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, spec);
            });
            city.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, city);
            });
            street.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, street);
            });
            dezExp.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckInt(oldValue, newValue, dezExp);
            });
            progExp.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckInt(oldValue, newValue, progExp);
            });
            testExp.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckInt(oldValue, newValue, testExp);
            });
            manExp.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckInt(oldValue, newValue, manExp);
            });
            house.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckInt(oldValue, newValue, house);
            });
            manSal.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckDou(oldValue, newValue, manSal);
            });
            progSal.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckDou(oldValue, newValue, progSal);
            });
            testSal.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckDou(oldValue, newValue, testSal);
            });
            dezSal.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckDou(oldValue, newValue, dezSal);
            });
            testSal.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckDou(oldValue, newValue, testSal);
            });
            course.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckCourse(oldValue, newValue, course);
            });
            year.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckYear(oldValue, newValue, year);
            });
        }
    }

    //-------------------------------LIST METHODS-------------------------------------
    public void fillPersonData() {
        personData.add(new Student(idPerson++, new Birth(14, Birth.Month.OCT, 2000), "Ksenia", "Tsubulko", Person.Sex.Female, "tsubulko.ksenia@gmail.com", new Adress("Minsk", "Perehyadnaya", 12), Person.Position.Student, "BSUIR", "KSIS", "SOIT", 2, "851002"));
        personData.add(new Manager(idPerson++, new Birth(18, Birth.Month.DEC, 1973), "Dmitry", "Tsubulko", Person.Sex.Male, "TiMiQ@gmail.com", new Adress("Minsk", "Soltisa", 14), Person.Position.Manager, 6000, 20, Employee.Education.Hight, 1000));
        personData.add(new Tester(idPerson++, new Birth(9, Birth.Month.AUG, 1998), "Alex", "Sememenko", Person.Sex.Male, "alsem@gmail.com", new Adress("Minsk", "Tikotskogo", 34), Person.Position.Tester, 1000, 3, Employee.Education.None, "Mobile game", Tester.TestType.Automated));
        personData.add(new Designer(idPerson++, new Birth(30, Birth.Month.MAY, 1990), "Alena", "Gromova", Person.Sex.Female, "grom@gmail.com", new Adress("Molodechno", "Centralnaya", 2), Person.Position.Designer, 1500, 6, Employee.Education.Middle, "Mobile game", Designer.DezSkills.Illustrator, Designer.DezType.Game));
        personData.add(new Programmer(idPerson++, new Birth(05, Birth.Month.MAR, 1998), "Alex", "Philatov", Person.Sex.Male, "philatov@gmail.com", new Adress("Minsk", "Koshevogo", 4), Person.Position.Programmer, 5000, 13, Employee.Education.Hight, "Washing robot", Programmer.Category.Senior, Programmer.ProgSkills.JAVA));
    }

    public Person getPerson(int id) {
        for (Person person :
                personData) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    public boolean deletePerson(int id) {
        for (Person person :
                personData) {
            if (person.getId() == id) {
                return personData.remove(person);
            }
        }
        return false;
    }

    //-------------------------------CHECK TEXTFIELDS-------------------------------------
    public void CheckDate(String oldValue, String newValue, int countDays) {
        String regDate = "([1-9]+[0-9]*)|(^)";
        Pattern pattern = Pattern.compile(regDate);
        if (!pattern.matcher(newValue).matches())
            date.setText(oldValue);
        else {
            if (!newValue.equals(""))
                if (Integer.parseInt(newValue) <= countDays) {
                } else date.setText(oldValue);
        }
    }

    public void CheckStr(String oldValue, String newValue, TextField d) {
        String regDate = "[a-zA-Z]*";
        Pattern pattern = Pattern.compile(regDate);
        if (!pattern.matcher(newValue).matches())
            d.setText(oldValue);
        else {
            d.setText(newValue);
        }
    }

    public void CheckInt(String oldValue, String newValue, TextField d) {
        String regDate = "([1-9]+[0-9]*)|(^)";
        Pattern pattern = Pattern.compile(regDate);
        if (!pattern.matcher(newValue).matches())
            d.setText(oldValue);
        else {
            d.setText(newValue);
        }
    }

    public void CheckDou(String oldValue, String newValue, TextField d) {
        String regDate = "([0-9]+\\.{0,1}[0-9]*)|(^)";
        Pattern pattern = Pattern.compile(regDate);
        if (!pattern.matcher(newValue).matches())
            d.setText(oldValue);
        else {
            d.setText(newValue);
        }
    }

    public void CheckCourse(String oldValue, String newValue, TextField d) {
        String regDate = "[1-5]|(^)";
        Pattern pattern = Pattern.compile(regDate);
        if (!pattern.matcher(newValue).matches())
            d.setText(oldValue);
        else {
            d.setText(newValue);
        }
    }

    public void CheckYear(String oldValue, String newValue, TextField d) {
        String regDate = "([1-2][0-9]*)|(^)";
        int count2 = 2020;
        int count1 = 1900;
        Pattern pattern = Pattern.compile(regDate);
        if (!pattern.matcher(newValue).matches())
            d.setText(oldValue);
        else {
            if (newValue.equals("")) d.setText("");
            else {
                if (newValue.length() >= 4) {
                    if (Integer.parseInt(newValue) >= count1 && Integer.parseInt(newValue) <= count2)
                        d.setText(newValue);
                    else d.setText(oldValue);
                } else d.setText(newValue);
            }
        }
    }

    public int ChooseDay() {
        if (month.getValue() != null) {
            switch (month.getValue().toString()) {
                case ("JAN"):
                    return 30;
                case ("FEB"):
                    return 29;
                case ("MAR"):
                    return 31;
                case ("APR"):
                    return 30;
                case ("MAY"):
                    return 31;
                case ("JUN"):
                    return 30;
                case ("JUL"):
                    return 31;
                case ("AUG"):
                    return 31;
                case ("SEP"):
                    return 30;
                case ("OCT"):
                    return 31;
                case ("NOV"):
                    return 30;
                case ("DEC"):
                    return 31;
            }
        }
        return 30;
    }

    private boolean checkMain(boolean flag){
        if (date.isVisible()) if (date.getText().length() == 0) flag = false;
        if (year.isVisible()) if (year.getText().length() == 0) flag = false;
        if (nameText.isVisible()) if (nameText.getText().length() == 0) flag = false;
        if (surnameText.isVisible()) if (surnameText.getText().length() == 0) flag = false;
        if (email.isVisible()) if (email.getText().length() == 0) flag = false;
        if (house.isVisible()) if (house.getText().length() == 0) flag = false;
        if (city.isVisible()) if (city.getText().length() == 0) flag = false;
        if (street.isVisible()) if (street.getText().length() == 0) flag = false;
        return flag;
    }

    private boolean checkManager(boolean flag) {
        if (manSal.isVisible()) if (manSal.getText().length() == 0) flag = false;
        if (manExp.isVisible()) if (manExp.getText().length() == 0) flag = false;
        if (bonus.isVisible()) if (bonus.getText().length() == 0) flag = false;
        if (manEdu.isVisible()) if (manEdu.getValue() == null) flag = false;
        return flag;
    }

    private boolean checkStudent(boolean flag) {
        if (univers.isVisible()) if (univers.getText().length() == 0) flag = false;
        if (facult.isVisible()) if (facult.getText().length() == 0) flag = false;
        if (spec.isVisible()) if (spec.getText().length() == 0) flag = false;
        if (course.isVisible()) if (course.getText().length() == 0) flag = false;
        if (group.isVisible()) if (group.getText().length() == 0) flag = false;
        return flag;
    }

    private boolean checkProgrammer(boolean flag) {
        if (progSal.isVisible()) if (progSal.getText().length() == 0) flag = false;
        if (progExp.isVisible()) if (progExp.getText().length() == 0) flag = false;
        if (progProj.isVisible()) if (progProj.getText().length() == 0) flag = false;
        if (progEdu.isVisible()) if (progEdu.getValue() == null) flag = false;
        if (progCat.isVisible()) if (progCat.getValue() == null) flag = false;
        if (progSkills.isVisible()) if (progSkills.getValue() == null) flag = false;
        return flag;
    }

    private boolean checkDesigner(boolean flag) {
        if (dezSal.isVisible()) if (dezSal.getText().length() == 0) flag = false;
        if (dezExp.isVisible()) if (dezExp.getText().length() == 0) flag = false;
        if (dezProj.isVisible()) if (dezProj.getText().length() == 0) flag = false;
        if (dezEdu.isVisible()) if (dezEdu.getValue() == null) flag = false;
        if (dezSkills.isVisible()) if (dezSkills.getValue() == null) flag = false;
        if (dezType.isVisible()) if (dezType.getValue() == null) flag = false;
        return flag;
    }

    private boolean checkTester(boolean flag) {
        if (testSal.isVisible()) if (testSal.getText().length() == 0) flag = false;
        if (testExp.isVisible()) if (testExp.getText().length() == 0) flag = false;
        if (testProj.isVisible()) if (testProj.getText().length() == 0) flag = false;
        if (testEdu.isVisible()) if (testEdu.getValue() == null) flag = false;
        if (testType.isVisible()) if (testType.getValue() == null) flag = false;

        return flag;
    }
}
