package br.com.project.controller;

import br.com.project.dto.LoginDto;
import br.com.project.dto.LoginResponse;
import br.com.project.exception.LoginException;
import br.com.project.service.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@FXML
	private Button btnEnter;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtUser;

	@FXML
	private void autenticate(ActionEvent event) {
		// Alterar o sysout por popups
		String user = txtUser.getText().trim();
		String password = txtPassword.getText().trim();

		if (user == null || user.isEmpty() || password == null || password.isEmpty()) {
			// Futuramente chamda de alerta
			System.out.println("Campo de usuário ou senha não devem estar vazios");
			return;
		}

		try {
			LoginResponse result = loginService.authentication(new LoginDto(user, password));
			// Chamada da tela em caso de sucesso!
			// mainScreen(result);
			System.out.println("Fucionou");
		} catch (LoginException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Erro inesperado");
		}
	}

}