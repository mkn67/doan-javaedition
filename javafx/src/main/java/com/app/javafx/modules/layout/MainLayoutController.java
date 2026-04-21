package com.app.javafx.modules.layout;

import com.app.javafx.core.security.SessionManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainLayoutController {

    @FXML
    private Label lblUser;

    @FXML
    private VBox contentArea;

    @FXML
    private Button btnKhachHang;

    @FXML
    private Button btnNhanSu;

    @FXML
    private Button btnKho;
    @FXML
    public void initialize() {
        loadUserInfo();
        setupRole();
    }

    // ===== LOAD USER =====
    private void loadUserInfo() {
        String username = SessionManager.getInstance().getUsername();
        lblUser.setText("Xin chào, " + username);
    }

    // ===== ROLE BASED UI =====
    private void setupRole() {
        String role = SessionManager.getInstance().getLoaiTk();

        // ví dụ:
        if (!"ADMIN".equalsIgnoreCase(role)) {
            btnNhanSu.setVisible(false);
        }
    }

    // ===== NAVIGATION =====
    @FXML
    private void openKhachHang() {
        loadView("/views/khachhang/KhachHangList.fxml");
    }

    @FXML
    private void openNhanSu() {
        loadView("/views/nhansu/NhanSuList.fxml");
    }

    @FXML
    private void openKho() {
        // ví dụ nếu chưa có
        System.out.println("Kho clicked");
    }

    // ===== LOAD VIEW CORE =====
    private void loadView(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== LOGOUT =====
    @FXML
    private void logout() {
        SessionManager.getInstance().clearSession();

        try {
            Parent login = FXMLLoader.load(
                    getClass().getResource("/views/auth/Login.fxml")
            );
            contentArea.getScene().setRoot(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}