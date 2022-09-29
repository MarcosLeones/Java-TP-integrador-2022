package data;

import entities.ObraSocial;
import entities.Paciente;
import entities.Persona;
import entities.Profesional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class DataObraSocial {

	public ArrayList<ObraSocial> getAll(){
	
	ArrayList<ObraSocial> obras = new ArrayList<ObraSocial>();
	Statement stmt=null;
	ResultSet rs=null;
	
	try {
		stmt= DbConnector.getInstancia().getConn().createStatement();
		rs= stmt.executeQuery("select id, nombre from obra_social");
		if(rs!=null) {
			while(rs.next()) {
				ObraSocial o = new ObraSocial();
				o.setId(rs.getInt("id"));
				o.setNombre(rs.getString("nombre"));
				
				obras.add(o);
			}
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		
	} finally {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	return obras;
	}
	
	
	
	public ObraSocial getById(ObraSocial obra) {
		ObraSocial o = new ObraSocial();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre from obra_social where id=?"
					);
			stmt.setInt(1, obra.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				o.setId(rs.getInt("id"));
				o.setNombre(rs.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return o;
	}
	
	public void add(ObraSocial obra) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into obra_social (nombre) values (?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, obra.getNombre());

			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                obra.setId(keyResultSet.getInt(1));
            }

			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public ArrayList<ObraSocial> getByPersona(Persona persona){
		ArrayList<ObraSocial> obras = new ArrayList<ObraSocial>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre from obra_social os "
					+ " inner join persona_obra po on os.id=po.id_obra "
					+ " where legajo_persona=?"
					);
			stmt.setInt(1, persona.getLegajo());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				while(rs.next()) {
					ObraSocial o = new ObraSocial();
					o.setId(rs.getInt("id"));
					o.setNombre(rs.getString("nombre"));
					
					obras.add(o);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return obras;
	}
	
	public void update(ObraSocial obra) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update obra_social set nombre=? where id=?;"
							);
			stmt.setString(1, obra.getNombre());
			stmt.setInt(2, obra.getId());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                obra.setId(keyResultSet.getInt("id"));
            }

			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	
	public void delete(ObraSocial obra) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from obra_social where id=?;"
							);
			stmt.setInt(1, obra.getId());
			stmt.executeUpdate();
			
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
}