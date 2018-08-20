package nyout.interfaceGrafica;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IniciarPanel extends JPanel {

    public IniciarPanel(final JanelaPrincipal janelaPrincipal) {
 
        setLayout(null);

        JLabel tituloLb = new JLabel("<html><font  color=blue size=20>NY-OUT - Back to the Forest</font>", JLabel.CENTER);
        tituloLb.setVerticalAlignment(SwingConstants.CENTER);
        tituloLb.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLb.setBounds(75, 60, 750, 100);
        tituloLb.setVisible(true);
//        tituloLb.setBorder(BorderFactory.createLineBorder(Color.red));
        add(tituloLb);
        setBorder(BorderFactory.createLineBorder(Color.red));
        System.out.println(this.getSize().height);

        final JLabel iniciarLb = new JLabel("<html>Iniciar Jogo</font>", JLabel.CENTER);
        iniciarLb.setFont(new Font("Arial", Font.BOLD, 80));
        iniciarLb.setVerticalAlignment(SwingConstants.CENTER);
        iniciarLb.setHorizontalAlignment(SwingConstants.CENTER);
        iniciarLb.setBounds(60, 325, 770, 220); //325-230
        iniciarLb.setVisible(true);
//        iniciarLb.setBorder(BorderFactory.createLineBorder(Color.red));
        add(iniciarLb);

        final JLabel iniciarL2 = new JLabel("<html>Iniciar Jogo</font>", JLabel.CENTER);
        iniciarL2.setFont(iniciarLb.getFont());
        iniciarL2.setVerticalAlignment(SwingConstants.CENTER);
        iniciarL2.setHorizontalAlignment(SwingConstants.CENTER);
        iniciarL2.setBounds(63, 328, 770, 223);
        iniciarL2.setVisible(true);
        iniciarL2.setForeground(Color.WHITE);
        add(iniciarL2);
        
        iniciarLb.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                janelaPrincipal.iniciarJogo();
            }//mouseClicked
            
        	public void mouseEntered(java.awt.event.MouseEvent e) {
                iniciarLb.setForeground(Color.RED);
                iniciarL2.setForeground(Color.GRAY);
        	}
        	
           	public void mouseExited(java.awt.event.MouseEvent e) {
                iniciarLb.setForeground(Color.BLACK);
                iniciarL2.setForeground(Color.WHITE);
        	}
            
        });
        
        JLabel imgFundoLb = new JLabel();
        imgFundoLb.setIcon(Imagens.telaInicialh900);
        imgFundoLb.setVerticalAlignment(SwingConstants.TOP);
        imgFundoLb.setHorizontalAlignment(SwingConstants.CENTER);
        imgFundoLb.setBorder(BorderFactory.createLineBorder(Color.black));
        imgFundoLb.setBounds(0,0,900,700);
        imgFundoLb.setVisible(true);
        add(imgFundoLb);

    }
}
