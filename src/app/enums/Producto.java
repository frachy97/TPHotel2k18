package app.enums;

public enum Producto {
	
	AGUA(20), CHOCOLATE(25), CARGADORCELULAR(200), CERVEZA(50), GASEOSA(30),CHAMPAGNE(250);
	
	
	
	static int contador;
	double precio;
	/*
	 * Atributo numero para que sea mas facil a la hora de elegirlo en un menu
	 * Es un id
	 */
	String id;
	
	Producto(double precio) {
		this.id = aumentarcontador();
        this.precio = precio;
    }
	
	/*
	 * busca un producto por su identificador y lo devuelve
	 */
	public static Producto buscarPorID(String identificador)
	{
		Producto aux = null;
		 for(Producto prod : Producto.values())
		    {
		        if(prod.getID()== identificador)
		        {
		        	aux= prod;
		        }
		    }
		 return aux;
	}
	
	public String aumentarcontador()
	{
		return String.valueOf(++contador);

	}
	public double getPrecio() {
		return precio;
	}
	public String getID()
	{
		return id;
	}
		
}
