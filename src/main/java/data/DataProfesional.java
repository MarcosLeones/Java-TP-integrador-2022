package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.Documento;
import entities.Especialidad;
import entities.ObraSocial;
import entities.Persona;
import entities.Profesional;

public class DataProfesional {
	
	private DataObraSocial dos = null;
	private DataEspecialidad de = null;
	
	public DataProfesional() {
		dos = new DataObraSocial();
		de = new DataEspecialidad();
	}
	
	public LinkedList<Profesional> getAll(){
		LinkedList<Profesional> profesionales = new LinkedList<Profesional>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from profesional");
			if (rs != null) {
				while (rs.next()) {
					Profesional p = new Profesional();
					Documento d = new Documento();

					p.setLegajo(rs.getInt("legajo"));
					d.setTipo(rs.getString("tipo_doc"));
					d.setNro(rs.getInt("nro_doc"));
					p.setDocumento(d);
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setEmail(rs.getString("email"));

					p.setObrasSociales(dos.getByProfesional(p));
					p.setEspecialidades(de.getByProfesional(p));
					
					profesionales.add(p);
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
		
		return profesionales;
	}
	
	
	public Profesional getByLegajo(Profesional profesional) {
		
		Profesional p = new Profesional();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select * from profesional where legajo=?");
			stmt.setInt(1, profesional.getLegajo());
			rs=stmt.executeQuery();
			
			if (rs != null) {
				if (rs.next()) {
					Documento d = new Documento();
					p.setLegajo(rs.getInt("legajo"));
					d.setTipo(rs.getString("tipo_doc"));
					d.setNro(rs.getInt("nro_doc"));
					p.setDocumento(d);
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setEmail(rs.getString("email"));
				
					p.setObrasSociales(dos.getByProfesional(p));
					p.setEspecialidades(de.getByProfesional(p));
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
		
		return p;
	}

	
	public void add(Profesional p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into profesional (tipo_doc, nro_doc, nombre, apellido, email, password,"
							+ " values (?,?,?,?,?,?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, p.getDocumento().getTipo());
			stmt.setInt(2, p.getDocumento().getNro());
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getPassword());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setLegajo(keyResultSet.getInt(1));
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
		
		addObrasToProfesional(p);
		addEspecialidadesToProfesional(p);
	}
	
	public void addObrasToProfesional(Profesional p) {
		PreparedStatement stmt= null;
		LinkedList<ObraSocial> obras = new LinkedList<ObraSocial>(p.getObrasSociales());
		
		try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement("insert into profesional_obra (legajo_profesional, id_obra) values (?,?);");

            for (ObraSocial o : obras) {
                stmt.setInt(1, p.getLegajo());
                stmt.setInt(2, o.getId());
                
                stmt.addBatch(); 
            }
            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public void addEspecialidadesToProfesional(Profesional p) {
		PreparedStatement stmt= null;
		LinkedList<Especialidad> especialidades = new LinkedList<Especialidad>(p.getEspecialidades());
		
		try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement("insert into profesional_especialidad (legajo_profesional, id_especialidad) values (?,?);");

            for (Especialidad e : especialidades) {
                stmt.setInt(1, p.getLegajo());
                stmt.setInt(2, e.getId());
                
                stmt.addBatch(); 
            }
            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}

	public void deleteObrasFromProfesional(Profesional p) {
		PreparedStatement stmt= null;

		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from profesional_obra where legajo_profesional=?"
							);
			stmt.setInt(1, p.getLegajo());
			
			stmt.executeUpdate();			
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public void deleteEspecialidadesFromProfesional(Profesional p) {
		PreparedStatement stmt= null;

		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from profesional_especialidad where legajo_profesional=?"
							);
			stmt.setInt(1, p.getLegajo());
			
			stmt.executeUpdate();			
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
		
	public void update(Profesional p) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;

		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update profesional set (tipo_doc, nro_doc, nombre, apellido, email, password) values(?,?,?,?,?,?) where legajo=?"
							);
			stmt.setString(1, p.getDocumento().getTipo());
			stmt.setInt(2, p.getDocumento().getNro());
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getPassword());
			stmt.setInt(7, p.getLegajo());	
			
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
		
		deleteObrasFromProfesional(p);	
		addObrasToProfesional(p);
		
		deleteEspecialidadesFromProfesional(p);
		addEspecialidadesToProfesional(p);
	
	}

	public void delete(Profesional p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;

		deleteObrasFromProfesional(p);
		deleteEspecialidadesFromProfesional(p);
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from profesional where legajo=?"
							);
			stmt.setInt(1, p.getLegajo());
			
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
	
	
	public Profesional getByEmailYPass(Persona persona) {
		
		Profesional p = new Profesional();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select * from profesional where email=? and password=?");
			stmt.setString(1, persona.getEmail());
			stmt.setString(2, persona.getPassword());
			rs=stmt.executeQuery();
			
			if (rs != null) {
				if (rs.next()) {
					Documento d = new Documento();
					p.setLegajo(rs.getInt("legajo"));
					d.setTipo(rs.getString("tipo_doc"));
					d.setNro(rs.getInt("nro_doc"));
					p.setDocumento(d);
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setEmail(rs.getString("email"));
				
					p.setObrasSociales(dos.getByProfesional(p));
					p.setEspecialidades(de.getByProfesional(p));
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
		
		return p;
	}

}
