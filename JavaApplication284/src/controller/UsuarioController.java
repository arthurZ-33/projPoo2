package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Usuario;
import utils.Util;

public class UsuarioController {

    public boolean autenticar(String usuario, String senha) {
        String sql = "SELECT * from tbusuario"
                + " WHERE email = ? and senha = ?"
                + " and ativo = true";

        GerenciadorConexao gerenciador = new GerenciadorConexao();

        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {
            comando = gerenciador.prepararComando(sql);

            comando.setString(1, usuario);
            comando.setString(2, senha);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            gerenciador.fecharConexao(comando, resultado);
        }
        return false;
    }

    public boolean inserir(Usuario usu) {
        String sql = "INSERT INTO TBUSUARIO (nome, email, senha, datanasc, ativo, imagem)"
                + "Values (?, ?, ?, ?, ?, ?)";

        GerenciadorConexao gerenciador = new GerenciadorConexao();

        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {
            comando = gerenciador.prepararComando(sql);

            comando.setString(1, usu.getNome());
            comando.setString(2, usu.getEmail());
            comando.setString(3, usu.getSenha());
            comando.setDate(4, new java.sql.Date(usu.getDataNascimento().getTime()));
            comando.setBoolean(5, usu.isAtivo());
            comando.setBytes(6, usu.getImagem());

            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            gerenciador.fecharConexao(comando);
        }
        return false;
    }

    public List<Usuario> consultar(int opcaoFiltro, String filtro) {
        String sql = "SELECT * from  TBUSUARIO";

        switch (opcaoFiltro) {
            case 0://código igual
                sql = "SELECT * FROM TBUSUARIO WHERE usuario =" + filtro;
                break;
            case 1: //nome contendo
                sql = "SELECT * FROM TBUSUARIO WHERE nome like '%" + filtro + "%'";
                break;
            case 2:// email contendo
                sql = "SELECT * FROM TBUSUARIO WHERE email like '%" + filtro + "%'";
                break;
        }

        GerenciadorConexao gerenciador = new GerenciadorConexao();

        PreparedStatement comando = null;
        ResultSet resultado = null;

        //crio a lista de usuario, vazia ainda
        List<Usuario> lista = new ArrayList<>();

        try {
            comando = gerenciador.prepararComando(sql);

            resultado = comando.executeQuery();

            while (resultado.next()) {
                Usuario usu = new Usuario();

                usu.setPkUsuario(resultado.getInt("usuario"));
                usu.setNome(resultado.getString("nome"));
                usu.setEmail(resultado.getString("email"));
                usu.setSenha(resultado.getString("senha"));//É a hash
                usu.setDataNascimento(resultado.getDate("datanasc"));
                usu.setAtivo(resultado.getBoolean("ativo"));

                lista.add(usu);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            gerenciador.fecharConexao(comando, resultado);
        }
        return lista;
    }

    public boolean alterar(Usuario usu) {
        String sql = "UPDATE TBUSUARIO SET"
                + "nome = ?, " //1
                + "email = ?,"//2
                + "datanasc = ?, "//3
                + "ativo = ?, "//4
                + "WHERE pkUsuario = ?, ";
        if (usu.getSenha() != null) {
            sql += " ,senha = ?";//5
        }

        sql += "Where pkUsuario = ?";

        GerenciadorConexao gerenciador = new GerenciadorConexao();

        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {
            comando = gerenciador.prepararComando(sql);

            comando.setString(1, usu.getNome());
            comando.setString(2, usu.getEmail());
            comando.setDate(3, new java.sql.Date(usu.getDataNascimento().getTime()));
            comando.setBoolean(4, usu.isAtivo());
            if (usu.getSenha() != null) {
                comando.setString(5, usu.getSenha());
                comando.setInt(6, usu.getPkUsuario());
            } else {
                comando.setInt(5, usu.getPkUsuario());
            }
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            gerenciador.fecharConexao(comando);
        }
        return false;
    }

    public boolean deletar(int pkUsuario) {

        String sql = "DELETE FROM TBUSUARIO"
                + "WHERE pkUsuario = ? ";

        GerenciadorConexao gerenciador = new GerenciadorConexao();

        PreparedStatement comando = null;

        try {
            comando = gerenciador.prepararComando(sql);

            comando.setInt(1, pkUsuario);

            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            gerenciador.fecharConexao(comando);
        }
        return false;
    }
}
