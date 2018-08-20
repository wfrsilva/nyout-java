package nyout.modelo;

import javax.swing.Icon;

public class Avatar {
//TODO: Realmente util??? Ate agora nao
	protected Avatar leao; 
	protected Avatar girafa;
	protected Avatar hipopotamo;
	protected Avatar zebra;
	
	protected int intAvatar;
	protected Icon iconAvatar;

	public Icon getIconAvatar() {
		return iconAvatar;
	}

	public void setIconAvatar(Icon iconAvatar) {
		this.iconAvatar = iconAvatar;
	}

	public void getLeao() {

		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param leao
	 */
	public void setLeao(int leao) {

		throw new UnsupportedOperationException();
	}

	public void getZebra() {

		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zebra
	 */
	public void setZebra(int zebra) {
		throw new UnsupportedOperationException();
	}

	public int getIntAvatar() {
		return this.intAvatar;
	}

	/**
	 * 
	 * @param intAvatar
	 */
	public void setIntAvatar(int intAvatar) {
		this.intAvatar = intAvatar;
	}

}