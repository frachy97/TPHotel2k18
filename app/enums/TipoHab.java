package app.enums;

public enum TipoHab {

    INDIVIDUAL(), MATRIMONIAL(), FAMILIAR();
    
    static int contador;
    
    String id;

    TipoHab() {
    	this.id = aumentarContador();
    }

	public String aumentarContador()
	{
		return String.valueOf(++contador);
		 
	}
	/*
	 * busca un producto por su identificador y lo devuelve
	 */
	public static TipoHab buscarPorID(String identificador)
	{
		TipoHab aux = null;
		 for(TipoHab tipo : TipoHab.values())
		    {
		        if(tipo.getID().equals(identificador))
		        {
		        	aux= tipo;
		        }
		    }
		 return aux;
	}



	public String getID()
	{
		return id;
	}
	
}
