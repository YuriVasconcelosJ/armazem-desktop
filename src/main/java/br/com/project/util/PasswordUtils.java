package br.com.project.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtils {

    private PasswordUtils() {}

    /**
     * Gera um hash a partir do texto puro
     * 
     * @param plainTextPassword senha em texto puro informado pelo usuário
     * @return hash gerado a partir da senha
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

    }

    /**
     * Verifica se a senha em texto puro corresponde ao hash armazenado.
     * 
     * @param plainTextPassword senha em texto puro informado pelo usuário
     * @param hashedPassword hash previamente armazenado no banco de dados
     * @return true se a senha corresponde ao hash; false caso contrário
     */
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
    
}
