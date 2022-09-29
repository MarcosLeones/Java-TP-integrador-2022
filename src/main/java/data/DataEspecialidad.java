package data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.Especialidad;
import entities.Profesional;


public class DataEspecialidad {

	public LinkedList<Especialidad> getAll() {

		LinkedList<Especialidad> especialidades = new LinkedList<Especialidad>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select id, nombre from especialidad");
			if (rs != null) {
				while (rs.next()) {
					Especialidad e = new Especialidad();
					e.setId(rs.getInt("id"));
					e.setNombre(rs.getString("nombre"));

					especialidades.add(e);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return especialidades;
	}
		
		
		
	public Especialidad getById(Especialidad esp) {
		Especialidad e = new Especialidad();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre from especialidad where id=?"
					);
			stmt.setInt(1, esp.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				e.setId(rs.getInt("id"));
				e.setNombre(rs.getString("nombre"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return e;
	}
		
	public void add(Especialidad esp) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into especialidad (nombre) values (?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, esp.getNombre());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                esp.setId(keyResultSet.getInt("id"));
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

	
	public LinkedList<Especialidad> getByProfesional(Profesional profesional){
		LinkedList<Especialidad> especialidades = new LinkedList<Especialidad>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre from especialidad inner join profesional_especialidad where legajo_profesional=?"
					);
			stmt.setInt(1, profesional.getLegajo());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				while(rs.next()) {
					Especialidad e = new Especialidad();
					e.setId(rs.getInt("id"));
					e.setNombre(rs.getString("nombre"));
					
					especialidades.add(e);
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
		
		return especialidades;
	}
	
	
	public void delete(Especialidad esp) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from especialidad where id=?;"
							);
			stmt.setInt(1, esp.getId());
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

	
	public void update(Especialidad esp) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update especialidad set  nombre values ? where id=?;"
							);
			stmt.setString(1, esp.getNombre());
			stmt.setInt(2, esp.getId());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                esp.setId(keyResultSet.getInt("id"));
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
}
