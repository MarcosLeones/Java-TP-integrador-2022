package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entities.Documento;
import entities.Especialidad;
import entities.ObraSocial;
import entities.Persona;
import entities.Profesional;



public class DataPersona {

	private DataObraSocial dos = new DataObraSocial();
	private DataEspecialidad de = new DataEspecialidad();
	
	public ArrayList<Persona> getProfesionales(Especialidad esp, ObraSocial os){
		
		ArrayList<Persona> profesionales = new ArrayList<Persona>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT * FROM persona p"
									+ " INNER JOIN persona_obra po ON p.legajo=po.legajo_persona "
									+ " INNER JOIN profesional_especialidad pe ON p.legajo=pe.legajo_profesional "
									+ " WHERE id_especialidad=? and id_obra=? and rol='profesional'");
			stmt.setInt(1, esp.getId());
			stmt.setInt(2, os.getId());
			rs=stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Persona p = new Persona();
					Documento d = new Documento();

					p.setLegajo(rs.getInt("legajo"));
					d.setTipo(rs.getString("tipo_doc"));
					d.setNro(rs.getInt("nro_doc"));
					p.setDocumento(d);
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setEmail(rs.getString("email"));
					p.setTelefono(rs.getString("telefono"));
					//p.setDomicilio(rs.getString("domicilio"));
					//p.setFechaNacimiento(rs.getDate("fecha_nac").toLocalDate());
					//p.setSexo(rs.getString("sexo"));
					p.setRol(rs.getString("rol"));
					p.setObrasSociales(dos.getByPersona(p));
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

	public Persona getByLegajo(int legajo) {
		Persona p = new Persona();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select * from persona where legajo=?");
			stmt.setInt(1, legajo);
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
					p.setTelefono(rs.getString("telefono"));
					p.setDomicilio(rs.getString("domicilio"));
					p.setFechaNacimiento(rs.getDate("fecha_nac").toLocalDate());
					p.setSexo(rs.getString("sexo"));
					p.setRol(rs.getString("rol"));
					p.setObrasSociales(dos.getByPersona(p));
					if (p.getRol().equals("profesional")) p.setEspecialidades(de.getByProfesional(p));
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
	
	
	public ArrayList<Persona> getAll() {
		ArrayList<Persona> pacientes = new ArrayList<Persona>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from persona");
			if (rs != null) {
				while (rs.next()) {
					Persona p = new Persona();
					Documento d = new Documento();

					p.setLegajo(rs.getInt("legajo"));
					d.setTipo(rs.getString("tipo_doc"));
					d.setNro(rs.getInt("nro_doc"));
					p.setDocumento(d);
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setEmail(rs.getString("email"));
					p.setTelefono(rs.getString("telefono"));
					p.setDomicilio(rs.getString("domicilio"));
					p.setFechaNacimiento(rs.getDate("fecha_nac").toLocalDate());
					p.setSexo(rs.getString("sexo"));
					p.setRol(rs.getString("rol"));
					p.setObrasSociales(dos.getByPersona(p));
					if (p.getRol().equals("profesional")) p.setEspecialidades(de.getByProfesional(p));
					
					pacientes.add(p);
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
		
		return pacientes;
	}
	

	public void add(Persona p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into persona (tipo_doc, nro_doc, nombre, apellido, email, password,"
							+ " telefono, domicilio, fecha_nac, sexo, rol)"
							+ " values (?,?,?,?,?,?,?,?,?,?,?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, p.getDocumento().getTipo());
			stmt.setInt(2, p.getDocumento().getNro());
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getPassword());
			stmt.setString(7, p.getTelefono());
			stmt.setString(8, p.getDomicilio());
			stmt.setObject(9, p.getFechaNacimiento());
			stmt.setString(10, p.getSexo());
			stmt.setString(11, p.getRol());
			
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
		
		addObrasToPersona(p);
		if (p.getRol().equals("profesional")) addEspecialidadesToPersona(p);
    }

	
	private void addObrasToPersona(Persona p) {
		PreparedStatement stmt= null;
		ArrayList<ObraSocial> obras = new ArrayList<ObraSocial>(p.getObrasSociales());
		
		try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement("insert into persona_obra (legajo_persona, id_obra) values (?,?);");

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
	
	private void addEspecialidadesToPersona(Persona p) {
		PreparedStatement stmt= null;
		ArrayList<Especialidad> especialidades = new ArrayList<Especialidad>(p.getEspecialidades());
		
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

	
	 public void deleteObrasFromPersona(Persona p) {
		PreparedStatement stmt= null;

		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from persona_obra where legajo_persona=?"
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
	
	public void deleteEspecialidadesFromPersona(Persona p) {
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
	
		
	public void update(Persona p) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;

		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update persona set tipo_doc=?, nro_doc=?, nombre=?, apellido=? " 
							+", email=?, password=?, telefono=?, domicilio=?, fecha_nac=?, sexo=?, rol=?"
							+ "  where legajo=?");
			stmt.setString(1, p.getDocumento().getTipo());
			stmt.setInt(2, p.getDocumento().getNro());
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getPassword());
			stmt.setString(7, p.getTelefono());
			stmt.setString(8, p.getDomicilio());
			stmt.setObject(9, p.getFechaNacimiento());
			stmt.setString(10, p.getSexo());
			stmt.setString(11, p.getRol());
			stmt.setInt(12, p.getLegajo());	
			
			stmt.executeUpdate();			
			
			deleteObrasFromPersona(p);	
			addObrasToPersona(p);
		
			deleteEspecialidadesFromPersona(p);
			addEspecialidadesToPersona(p);
			
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
	

	public void delete(Persona p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;

		deleteObrasFromPersona(p);
		deleteEspecialidadesFromPersona(p);
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from persona where legajo=?"
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
	 

}
