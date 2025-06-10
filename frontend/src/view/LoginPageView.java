/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.*;

public class LoginPageView extends JFrame {

    // Componentes que precisam de reposicionamento dinâmico
    private JPanel painelDireito;
    private JLabel cadastroLabel, tituloLogin, subtitulo, emailLabel, senhaLabel, esqueceuSenha, ouLabel;
    private JButton btnCadastro, botaoEntrar, facebookBtn, googleBtn;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JCheckBox lembrarCheck;

    public LoginPageView() {
        setTitle("Login - Alerta de Alagamentos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel esquerdo com imagem e cor
        JPanel painelEsquerdo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/imagens/bg-login.png");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelEsquerdo.setPreferredSize(new Dimension(350, 600));
        painelEsquerdo.setLayout(null);
        painelEsquerdo.setBackground(new Color(0, 1, 222));

        JLabel tituloEsquerdo = new JLabel("<html>Juntos para uma<br>rota mais segura.<br>Salve vidas.</html>");
        tituloEsquerdo.setForeground(Color.WHITE);
        tituloEsquerdo.setFont(new Font("SansSerif", Font.BOLD, 18));
        tituloEsquerdo.setBounds(20, 40, 310, 100);
        painelEsquerdo.add(tituloEsquerdo);

        // Painel direito
        painelDireito = new JPanel();
        painelDireito.setBackground(Color.WHITE);
        painelDireito.setLayout(null);

        cadastroLabel = new JLabel("Não possui conta?");
        cadastroLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        painelDireito.add(cadastroLabel);

        btnCadastro = new JButton("Comece Agora");
        btnCadastro.setFont(new Font("SansSerif", Font.PLAIN, 12));
        painelDireito.add(btnCadastro);

        tituloLogin = new JLabel("BEM VINDO!");
        tituloLogin.setFont(new Font("SansSerif", Font.BOLD, 22));
        painelDireito.add(tituloLogin);

        subtitulo = new JLabel("Entre e comece a fazer a diferença.");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitulo.setForeground(Color.GRAY);
        painelDireito.add(subtitulo);

        emailLabel = new JLabel("Email");
        painelDireito.add(emailLabel);

        campoEmail = new JTextField();
        painelDireito.add(campoEmail);

        senhaLabel = new JLabel("Senha");
        painelDireito.add(senhaLabel);

        campoSenha = new JPasswordField();
        painelDireito.add(campoSenha);

        esqueceuSenha = new JLabel("Esqueceu a Senha?");
        esqueceuSenha.setForeground(new Color(0, 120, 255));
        esqueceuSenha.setFont(new Font("SansSerif", Font.PLAIN, 12));
        painelDireito.add(esqueceuSenha);

        lembrarCheck = new JCheckBox("Lembrar do dispositivo");
        lembrarCheck.setBackground(Color.WHITE);
        painelDireito.add(lembrarCheck);

        botaoEntrar = new JButton("Entrar");
        painelDireito.add(botaoEntrar);

        // Listener de ação para o botão de login
        botaoEntrar.addActionListener(e -> {
            String email = campoEmail.getText();
            String senha = new String(campoSenha.getPassword());
            fazerLogin(email, senha);
        });


        ouLabel = new JLabel("Ou entre usando:");
        painelDireito.add(ouLabel);

        facebookBtn = new JButton("Facebook");
        painelDireito.add(facebookBtn);

        googleBtn = new JButton("Google");
        painelDireito.add(googleBtn);

        // Adiciona os painéis à janela
        add(painelEsquerdo, BorderLayout.WEST);
        add(painelDireito, BorderLayout.CENTER);

        // Responsividade
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarLayout();
            }
        });

        setVisible(true);
        ajustarLayout(); // Chamada inicial
    }

    // Atualiza posições dos componentes com base no tamanho do painel direito
    private void ajustarLayout() {
    int largura = painelDireito.getWidth();
    int altura = painelDireito.getHeight();

    // Define a largura máxima dos campos e a margem mínima
    int larguraCampos = Math.min(300, largura - 40);
    int leftMargin = (largura - larguraCampos) / 2;

    // Altura total aproximada dos componentes
    int alturaTotalConteudo = 420;
    int topMargin = Math.max(20, (altura - alturaTotalConteudo) / 2);

    cadastroLabel.setBounds(largura - 260, 15, 120, 20);
    btnCadastro.setBounds(largura - 150, 10, 130, 30);

    tituloLogin.setBounds(leftMargin, topMargin + 0, larguraCampos, 30);
    subtitulo.setBounds(leftMargin, topMargin + 30, larguraCampos, 20);

    emailLabel.setBounds(leftMargin, topMargin + 70, larguraCampos, 20);
    campoEmail.setBounds(leftMargin, topMargin + 90, larguraCampos, 30);

    senhaLabel.setBounds(leftMargin, topMargin + 130, larguraCampos, 20);
    campoSenha.setBounds(leftMargin, topMargin + 150, larguraCampos, 30);

    esqueceuSenha.setBounds(leftMargin, topMargin + 185, 140, 20);
    lembrarCheck.setBounds(leftMargin - 3, topMargin + 210, 200, 20);

    botaoEntrar.setBounds(leftMargin, topMargin + 240, larguraCampos, 35);

    ouLabel.setBounds(leftMargin, topMargin + 280, larguraCampos, 20);

    facebookBtn.setBounds(leftMargin, topMargin + 310, (larguraCampos / 2) - 10, 30);
    googleBtn.setBounds(leftMargin + (larguraCampos / 2) + 10, topMargin + 310, (larguraCampos / 2) - 10, 30);
}

private void fazerLogin(String email, String senha) {
    try {
        // Codifica os parâmetros para a URL
        String encodedEmail = URLEncoder.encode(email, "UTF-8");
        String encodedSenha = URLEncoder.encode(senha, "UTF-8");

        // Constrói a URL com parâmetros
        String urlStr = String.format("http://localhost:8080/login?email=%s&password=%s", encodedEmail, encodedSenha);
        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int statusCode = conn.getResponseCode();

        if (statusCode == 200) {
            // Sucesso: abre MainView e fecha LoginPageView
            SwingUtilities.invokeLater(() -> {
                new MainView();
            });
            dispose();
        } else if (statusCode == 401) {
            JOptionPane.showMessageDialog(this, "Credenciais inválidas!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro: código HTTP " + statusCode);
        }

        conn.disconnect();

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao conectar: " + ex.getMessage());
    }
}




    public static void main(String[] args) {
        new LoginPageView();
    }
}
