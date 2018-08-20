package nyout.interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AvatarDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	protected JPanel painel = null;
	protected JLabel avtLeaoLb = null;
	protected JLabel avtZebraLb = null;
	protected JLabel avtGrfaLb = null;
	protected JLabel avtHptmLb = null;
	private JLabel perguntaLb = null;
			
	protected JRadioButton leaoRB = null;
	protected JRadioButton zebraRB = null;
	protected JRadioButton grfaRB = null;
	protected JRadioButton hptmRB = null;
	

	protected static JButton botaoOK = null;
	
	public AvatarDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initialize();
        this.setTitle("Escolha do Avatar");
 }// construtor NyAvatarGui
	
	
	private void initialize() {
		this.setSize(225, 400);
		this.setResizable(false);
		this.setContentPane(getPainel());
	    this.addWindowListener(new java.awt.event.WindowAdapter() {
	    	public void windowClosing(java.awt.event.WindowEvent evt) {
	    		acaoBotaoOK();
	    	}
	    });
	}//initialize


	public JPanel getPainel() {
		if(painel == null){
			perguntaLb = new JLabel();
//			perguntaLb.setBounds(new Rectangle(19, 12, 205, 25));
			perguntaLb.setText("Escolha seu Avatar:");
			perguntaLb.setVerticalTextPosition(JLabel.CENTER);
			perguntaLb.setHorizontalTextPosition(JLabel.CENTER);
			
			avtLeaoLb = getLabelAvatar(0);
			avtZebraLb = getLabelAvatar(1);
			avtGrfaLb = getLabelAvatar(2);
			avtHptmLb = getLabelAvatar(3);
			
			avtLeaoLb.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                    	leaoRB.setSelected(true);
                            }
                        }
            );
			avtZebraLb.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                    	zebraRB.setSelected(true);
                            }
                        }
            );
			avtGrfaLb.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                    	grfaRB.setSelected(true);
                            }
                        }
            );
			avtHptmLb.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                    	hptmRB.setSelected(true);
                            }
                        }
            );
			
			ButtonGroup grupoBotoesRadio = new ButtonGroup();
			leaoRB = this.getBtRadioLeao();
			zebraRB = this.getBtRadioZebra();
			grfaRB = this.getBtRadioGirafa();
			hptmRB = this.getBtRadioHipopotamo();
			grupoBotoesRadio.add(leaoRB);
			grupoBotoesRadio.add(zebraRB);
			grupoBotoesRadio.add(grfaRB);
			grupoBotoesRadio.add(hptmRB);
			
			JPanel painelBotoes = new JPanel(new GridLayout(4,2));
			painelBotoes.add(avtLeaoLb);
			painelBotoes.add(avtZebraLb);
			
			painelBotoes.add(leaoRB);
			painelBotoes.add(zebraRB);
			
			painelBotoes.add(avtGrfaLb);
			painelBotoes.add(avtHptmLb);
			
			painelBotoes.add(grfaRB);
			painelBotoes.add(hptmRB);
			
			painel = new JPanel();
			painel.setLayout(new BorderLayout());
			painel.add(perguntaLb, BorderLayout.NORTH);
			painel.add(painelBotoes,BorderLayout.CENTER);
			painel.add(getBotaoOK(), BorderLayout.SOUTH);
			
			
		}//if
		return painel;
	}//getPainel
	
	
	public JLabel getLabelAvatar(int i){
		JLabel labelAvatar = new JLabel();

		switch (i) {
		case 0: 
			labelAvatar.setIcon(Imagens.casa1leao);
			break;
		case 1: 
			labelAvatar.setIcon(Imagens.casa1zbra);
			break;
		case 2: 
			labelAvatar.setIcon(Imagens.casa1grfa);
			break;
		case 3: 
			labelAvatar.setIcon(Imagens.casa1hptm);
			break;

		default:
			break;
		}//switch

		return labelAvatar;		
		
	}//getLabelPergunta

	
	public JRadioButton getBtRadioLeao(){
		if(leaoRB == null){
			leaoRB = new JRadioButton();
			leaoRB.setSelected(true);
			leaoRB.setEnabled(true);
			leaoRB.setText("Leão");
		}//if
		
		return leaoRB;
	}//getBtRadiogrfa
	
	
	public JRadioButton getBtRadioZebra(){
		if(zebraRB == null){
			zebraRB = new JRadioButton();
			zebraRB.setSelected(true);
			zebraRB.setEnabled(true);
			zebraRB.setText("Zebra");
		}//if
		
		return zebraRB;
	}//getBtRadiozebra
	
	
	public JRadioButton getBtRadioGirafa(){
		if(grfaRB == null){
			grfaRB = new JRadioButton();
			grfaRB.setSelected(true);
			grfaRB.setEnabled(true);
			grfaRB.setText("Girafa");
		}//if
		
		return grfaRB;
	}//getBtRadioGirafa
	
	
	public JRadioButton getBtRadioHipopotamo(){
		if(hptmRB == null){
			hptmRB = new JRadioButton();
			hptmRB.setSelected(true);
			hptmRB.setEnabled(true);
			hptmRB.setText("Hipopótamo");
		}//if
		
		return hptmRB;
	}//getBtRadioHipopotamo
	
	
	public JButton getBotaoOK(){
		if(botaoOK ==null){
			botaoOK = new JButton();
			botaoOK.setText("OK");
			botaoOK.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					acaoBotaoOK();
				}
			});
		}//if
		
		return botaoOK;
		
	}//getBotaoOK
	
	
	public void acaoBotaoOK() {
        setVisible(false);
        dispose();
	}//acaoBotaoOK
	
	
	public void definePergunta(String pergunta){
		perguntaLb.setText(pergunta);		
	}//definePergunta
	
	
	public boolean leaoSelecionado(){
		return leaoRB.isSelected();
	}//LeaoSelecionado
	
	
	public boolean zebraSelecionado(){
		return zebraRB.isSelected();
	}//LeaoSelecionado
	
	
	public boolean girafaSelecionado(){
		return grfaRB.isSelected();
	}//LeaoSelecionado
	
	
	public boolean hipopotamoSelecionado(){
		return hptmRB.isSelected();
	}//LeaoSelecionado
	
	
	public void setPainel(JPanel painel) {
		this.painel = painel;
	}//setPainel
	
	
	public int informaAvatarSelecionado(){
		int nroAvatar = 0;
		
		if(leaoSelecionado()){
			nroAvatar = 1;
		}else if(zebraSelecionado()){
			nroAvatar = 2;
		}else if(girafaSelecionado()){
			nroAvatar = 3;
		}else if(hipopotamoSelecionado()){
			nroAvatar = 4;
		}//if-else
		
		return nroAvatar;
		
	}//informaAvatar
	
	
	public static int informaAvatarDialogo(String pergunta){		
		AvatarDialog gui = new AvatarDialog(new javax.swing.JFrame(), true);
		gui.definePergunta(pergunta);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		return (gui.informaAvatarSelecionado());
		
	}//informaAvatarDialogo
	
	

}//class NyAvatarGui
