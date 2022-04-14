package org.eni.encheres.bll;

public class BLLException extends Exception{
	private static final long serialVersionUID = 1L;
	public BLLException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BLLException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BLLException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Couche BLL - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}
}
