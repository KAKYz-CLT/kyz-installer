package br.com.kakyz.kyz_installer.service;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import br.com.kakyz.kyz_installer.enums.DatabaseStatus;
import br.com.kakyz.kyz_installer.enums.Status;
import br.com.kakyz.kyz_installer.dto.StatusResponse;

@Service 
public class StatusService {

    private final DataSource dataSource;

    public StatusService(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    private DatabaseStatus verificarBanco() {

        try (
            Connection conexao = dataSource.getConnection();
            Statement statement = conexao.createStatement();
            
        ) {
            
            statement.setQueryTimeout(5);
            try ( ResultSet resultado = statement.executeQuery("SELECT 1")) {
                if (
                resultado.next() 
                && resultado.getInt(1) == 1

            ) {
                return DatabaseStatus.CONNECTED;

            }
                return DatabaseStatus.ERROR;
            }

        } catch (SQLTimeoutException erro) {
            // falta terminar
            return DatabaseStatus.TIMEOUT;

        } catch (SQLException erro) {
            String sqlState = erro.getSQLState();

            if (
                sqlState != null
                && sqlState.startsWith("08")

            ) {
                // Subistituindo o DISCONNECted pois nao consigo verificar
                // se o banco esta de fato desligado aindakkkkkkkk
                return DatabaseStatus.CONNECTIONERROR;

            }

            return DatabaseStatus.ERROR;

        }
    }

    public StatusResponse obterStatus() {
        return new StatusResponse(
            "KYZ-Installer",
            Status.ONLINE,
            verificarBanco(),
            "1.0.0"
        );
    }
}
