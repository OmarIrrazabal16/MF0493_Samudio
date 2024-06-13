package ppal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.ConexionBD;

public class MostrarEditoriales {

	public static void main(String[] args) {
		
		ConexionBD conexion = new ConexionBD();
		System.out.println("Conectando a la base de datos...");
		//Paso 1. Obtener la conexion
		// conexion correcta
		Connection con = conexion.getConexion();
		
		//Objetos necesarios para hacer una consulta
		Statement sentencia = null;
		ResultSet resultado = null;
		
		//Algún procesamiento con la base de datos..
		try {
			//Paso2. Obrener el Statement

			sentencia = con.createStatement();
			
			//Paso 3. Ejecutar la sentencia
			// correcta la select
			resultado = sentencia.executeQuery(" select codEditorial, nombre, anio from editoriales");
			
			//Paso 4. Recorre el resultado
			while(resultado.next()) {
				//datos correctos
				int codEditorial = resultado.getInt("codEditorial");
				String nombre = resultado.getString("nombre");
				int anio = resultado.getInt("anio");
				
				
				System.out.println(codEditorial+"\t"+nombre+"\t"+anio);
			}
			
		} catch (SQLException e) {
			System.out.println("Error consultar los datos. "+e.getMessage());
		} finally {
			try {
				resultado.close();
				sentencia.close();
			} catch (SQLException e) {
				System.out.println("Error al liberar los recursos");
			}
		}
		
		
		//Liberamos la conexión
		System.out.println("Desconectamos la base de datos");
		conexion.deconectar();

	}

}
