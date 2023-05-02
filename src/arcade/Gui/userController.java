/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Gui;

import arcade.Entities.user;
import arcade.Utils.MyConnection;
import static arcade.Utils.MyConnection.MyConnection;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class userController implements Initializable {

    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private Button updatebutton;

    @FXML
    private TableColumn<user, String> actions;

    @FXML
    private TextField emailtf;
    private TextField lasttf;
    @FXML
    private TextField pwdtf;
    @FXML
    private TextField usernametf;
    private TextField firsttf;
    @FXML
    private ComboBox roletf;
    @FXML
    private TableView<user> tabuser;
    @FXML
    private TableColumn<user, String> colemail;
    @FXML
    private TableColumn<user, String> colusername;
    private TableColumn<user, String> colfirst;
    private TableColumn<user, String> collast;
    @FXML
    private TableColumn<user, String> colpwd;

    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<user, String> colrole;
    @FXML
    private TextField imagetf;
    @FXML
    private Button btnajouter1;
    @FXML
    private TableColumn<?, ?> colimage;
    @FXML
    private Button stat;
    @FXML
    private PieChart chartpie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.add("admin");
        list.add("user");
        list1.add("Admin");
        list1.add("User");

        roletf.setItems(list1);
        showusers();
        //showRec();
        //searchRec();
    }

    public ObservableList<user> getuserList() {
        ObservableList<user> compteList = FXCollections.observableArrayList();
        Connection conn = MyConnection();
        String query = "SELECT * FROM user";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            user user;

            while (rs.next()) {

                user = new user(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("avatar"), rs.getString("role"));
                compteList.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return compteList;
    }

    /*/////////////////////////////////////////////////Controle de saisie/////////////////////////////////////////////////////////////////////*/
    private boolean validateFields() {
        if (emailtf.getText().isEmpty()) {
            showAlert("Error", "Email field is required.");
            return false;
        } else {
            String email = emailtf.getText().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!email.matches(emailRegex)) {
                showAlert("Error", "Please enter a valid email address.");
                return false;
            }
        }

        if (imagetf.getText().isEmpty()) {
            showAlert("Error", "Tel field is required.");
            return false;
        }
        if (pwdtf.getText().isEmpty()) {
            showAlert("Error", "Password is required.");
            return false;
        } else if (pwdtf.getText().length() < 8) {
            showAlert("Error", "Password must be at least 8 characters long.");
            return false;
        }

        if (usernametf.getText().isEmpty()) {
            showAlert("Error", "respo field is required.");
            return false;
        }

        return true;
    }

    public void showusers() {
        ObservableList<user> list = getuserList();
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colpwd.setCellValueFactory(new PropertyValueFactory<>("password"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("avatar"));

        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));

        tabuser.setItems(list);
        Callback<TableColumn<user, String>, TableCell<user, String>> cellFoctory = (TableColumn<user, String> param) -> {
            // make cell containing buttons
            final TableCell<user, String> cell = new TableCell<user, String>() {
                @Override
                public void updateItem(String item, boolean empty) {

                    user user = null;
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Alert!");
                                alert.setContentText("This is an alert");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {

                                    try {
                                        PreparedStatement ps = null;
                                        user users;
                                        users = tabuser.getSelectionModel().getSelectedItem();
                                        String query = "DELETE FROM `user` WHERE id =" + users.getId();
                                        Connection conn = MyConnection();
                                        ps = conn.prepareStatement(query);
                                        ps.execute();
                                        showusers();

                                    } catch (SQLException ex) {
                                        Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else if (result.get() == ButtonType.CANCEL) {
                                    showusers();
                                }
                            }
                        });

                        editIcon.setOnMouseClicked((new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {

                                user users = tabuser.getSelectionModel().getSelectedItem();

                                emailtf.setText(String.valueOf(users.getEmail()));
                                usernametf.setText(String.valueOf(users.getUsername()));
                                pwdtf.setText(String.valueOf(users.getMdp()));
                                imagetf.setText(String.valueOf(users.getAvatar()));

                            }

                        }));
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };
            return cell;
        };
        actions.setCellFactory(cellFoctory);
        tabuser.setItems(list);

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onupdate(ActionEvent event) {
        if (event.getSource() == updatebutton) {
            if (validateFields()) {
                String email = emailtf.getText();
                String username = usernametf.getText();
                String pwd = BCrypt.hashpw(pwdtf.getText(), BCrypt.gensalt());
                String avatar = imagetf.getText();
                String role = list.get(list1.indexOf(roletf.getValue()));
                user user = tabuser.getSelectionModel().getSelectedItem();

                String query = "UPDATE user SET email = ?, username = ?, password = ?, avatar = ?, role = ? WHERE id = ?";

                try (Connection conn = MyConnection();
                        PreparedStatement pstmt = conn.prepareStatement(query)) {

                    pstmt.setString(1, email);
                    pstmt.setString(2, username);
                    pstmt.setString(3, pwd);
                    pstmt.setString(4, avatar);
                    pstmt.setString(5, role);
                    pstmt.setInt(6, user.getId());

                    pstmt.executeUpdate();

                    showusers();

                } catch (SQLException e) {
                    System.err.println("Error executing update query: " + e.getMessage());
                }
            }
        }
    }

    private void executeQuery(String query) {
        Connection conn = MyConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    @FXML
    private void browse(ActionEvent event) throws FileNotFoundException {
        FileChooser filechooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

        //filechooser.setInitialDirectory(new File("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Notex\\src\\com\\gn\\module\\evenements\\image"));
        filechooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File file = filechooser.showOpenDialog(new Stage());
        if (file != null) {
            Image image = new Image(new FileInputStream(file));
            imagetf.setText(file.getName());
            // set fit height and width of ImageView to the image size

            // set the image view in your UI, e.g.
        }
    }
    Connection conn = MyConnection();

    @FXML

    private void Stat(ActionEvent event) {
        String sql = "SELECT role, COUNT(*) as count FROM user GROUP BY role";
        try (Connection conn = MyConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery(sql);

            List<PieChart.Data> pieChartData = new ArrayList<>();
            int total = 0;

            while (rs.next()) {
                String categorieP = rs.getString("role");
                int count = rs.getInt("count");
                total += count;
                PieChart.Data data = new PieChart.Data(categorieP + " (" + count + ")", count);
                pieChartData.add(data);
            }

            for (PieChart.Data data : pieChartData) {
                double percentage = (data.getPieValue() / total) * 100;
                data.setName(data.getName() + " - " + String.format("%.2f", percentage) + "%");
            }

            chartpie.setData(FXCollections.observableArrayList(pieChartData));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
