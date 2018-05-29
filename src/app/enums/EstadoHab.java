package app.enums;

public enum EstadoHab {

    LIBRE(), OCUPADA(), RESERVADA();

    static int contador;
	
	String id;
	
	EstadoHab() {
		this.id = aumentarcontador();
   
    }
	/*
	 * busca un producto por su identificador y lo devuelve
	 */
	public static EstadoHab buscarPorID(String identificador)
	{
		EstadoHab aux = null;
		 for(EstadoHab estado: EstadoHab.values())
		    {
		        if(estado.getID()== identificador)
		        {
		        	aux= estado;
		        }
		    }
		 return aux;
	}
	
	
	public String aumentarcontador()
	{
		return String.valueOf(++contador);

	}

	public String getID()
	{
		return id;
	}
}
