package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SideMenuComponent extends JPanel {

    private JLabel lblMapa;
    private JLabel lblAlertas;
    private JLabel lblPerfil;

    public SideMenuComponent() {
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(245, 245, 245));
        setPreferredSize(new Dimension(220, 600));
        setLayout(new BorderLayout());

        // Painel superior com foto + nome
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JLabel photoLabel;
        try {
            photoLabel = new JLabel(new ImageIcon(getClass().getResource("/img/output-onlinepngtools (2).png")));
        } catch (NullPointerException e) {
            photoLabel = new JLabel("🧑"); // fallback
            photoLabel.setFont(new Font("SansSerif", Font.PLAIN, 40));
            photoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        JLabel nameLabel = new JLabel("Avatar");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        topPanel.add(photoLabel);
        topPanel.add(nameLabel);

        // Painel central com botões
        Box centerBox = Box.createVerticalBox();

        lblMapa = new JLabel();
        lblAlertas = new JLabel();
        lblPerfil = new JLabel();

        JPanel panelMapa = criarItemMenu("/img/mapa.png", "Mapa", lblMapa);
        JPanel panelAlertas = criarItemMenu("/img/alertas.png", "Alertas", lblAlertas);
        JPanel panelPerfil = criarItemMenu("/img/perfil.png", "Perfil", lblPerfil);

        centerBox.add(Box.createVerticalGlue());
        centerBox.add(panelMapa);
        centerBox.add(Box.createVerticalStrut(15));
        centerBox.add(panelAlertas);
        centerBox.add(Box.createVerticalStrut(15));
        centerBox.add(panelPerfil);
        centerBox.add(Box.createVerticalGlue());

        // Adiciona os painéis ao layout principal
        add(topPanel, BorderLayout.NORTH);
        add(centerBox, BorderLayout.CENTER);
    }

    private JPanel criarItemMenu(String iconPath, String labelText, JLabel labelRef) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
            }
        };

        panel.setBackground(new Color(230, 230, 230));
        panel.setMaximumSize(new Dimension(180, 50));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel iconLabel;
        try {
            iconLabel = new JLabel(new ImageIcon(getClass().getResource(iconPath)));
        } catch (NullPointerException e) {
            iconLabel = new JLabel("❌");
        }

        labelRef.setText(labelText);
        labelRef.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelRef.setForeground(Color.DARK_GRAY);

        panel.add(iconLabel);
        panel.add(labelRef);
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                panel.setBackground(new Color(210, 210, 210));
                panel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                panel.setBackground(new Color(230, 230, 230));
                panel.repaint();
            }
        });

        return panel;
    }

    public JLabel getMapaLabel() {
        return lblMapa;
    }

    public JLabel getAlertasLabel() {
        return lblAlertas;
    }

    public JLabel getPerfilLabel() {
        return lblPerfil;
    }

    public void setLabelsVisible(boolean visible) {
        lblMapa.setVisible(visible);
        lblAlertas.setVisible(visible);
        lblPerfil.setVisible(visible);
    }
}
