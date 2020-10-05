package br.com.sga.service.exception;

/**
 * @author sga
 *
 */
public class ServiceException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	/**
	 * @param mensagem
	 */
	public ServiceException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * @param mensagem
	 * @param causa
	 */
	public ServiceException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
