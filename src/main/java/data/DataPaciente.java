package data;


import java.util.LinkedList;
import java.sql.*;

import entities.Documento;
import entities.ObraSocial;
import entities.Paciente;
import entities.Persona;


public class DataPaciente {
	
	private DataObraSocial dos = new DataObraSocial();

	public LinkedList<Paciente> getAll(){
		
		LinkedList<Paciente> pacientes = new LinkedList<Paciente>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from paciente");
			if (rs != null) {
				while (rs.next()) {
					Paciente p = new Paciente();
					Documento d = new Documento();

					p.setLegajo(rs.getInt("legajo"));
					d.setTipo(rs.getString("tipo_doc"));
					d.setNro(rs.getInt("nro_doc"));
					p.setDocumento(d);
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setEmail(rs.getString("email"));
					p.setTelefono(rs.getInt("telefono"));
					p.setDomicilio(rs.getString("domicilio"));
					p.setFechaNacimiento(rs.getDate("fecha_nac"));
					p.setSexo(rs.getString("sexo"));

					p.setObrasSociales(dos.getByPaciente(p));
					
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
	
	
	public Paciente getByLegajo(Paciente paciente) {
		Paciente p = new Paciente();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select * from paciente where legajo=?");
			stmt.setInt(1, paciente.getLegajo());
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
					p.setTelefono(rs.getInt("telefono"));
					p.setDomicilio(rs.getString("domicilio"));
					p.setFechaNacimiento(rs.getDate("fecha_nac"));
					p.setSexo(rs.getString("sexo"));
					p.setObrasSociales(dos.getByPaciente(p));
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

	public void add(Paciente p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into paciente (tipo_doc, nro_doc, nombre, apellido, email, password,"
							+ " telefono, domicilio, fecha_nac, sexo)"
							+ " values (?,?,?,?,?,?,?,?,?,?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, p.getDocumento().getTipo());
			stmt.setInt(2, p.getDocumento().getNro());
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getPassword());
			stmt.setInt(7, p.getTelefono());
			stmt.setString(8, p.getDomicilio());
			stmt.setDate(9, p.getFechaNacimiento());
			stmt.setString(10, p.getSexo());
			
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
		
		addObrasToPaciente(p);
    }

	
	
	public void update(Paciente p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;

		//Actualizo los datos de la Persona
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update paciente set (tipo_doc, nro_doc, nombre, apellido, email, password, telefono, domicilio, fecha_nac, sexo) values(?,?,?,?,?,?,?,?,?,?) where legajo=?"
							);
			stmt.setString(1, p.getDocumento().getTipo());
			stmt.setInt(2, p.getDocumento().getNro());
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getPassword());
			stmt.setInt(7, p.getTelefono());
			stmt.setString(8, p.getDomicilio());
			stmt.setDate(9, p.getFechaNacimiento());
			stmt.setString(10, p.getSexo());
			stmt.setInt(11, p.getLegajo());
			
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
		
		//Borro sus obras sociales viejas
		
		deleteObrasFromPaciente(p);
		
		
		//Guardo las nuevas obras sociales
		
		addObrasToPaciente(p);
	}
	
	
	public void delete(Paciente p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;

		deleteObrasFromPaciente(p);	
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from paciente where legajo=?"
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
	
	
	public void deleteObrasFromPaciente(Paciente p) {
		PreparedStatement stmt= null;

		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from paciente_obra where legajo_paciente=?"
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
	
	public void addObrasToPaciente(Paciente p){
		PreparedStatement stmt= null;
		LinkedList<ObraSocial> obras = new LinkedList<ObraSocial>(p.getObrasSociales());
		
		try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement("insert into paciente_obra (legajo_paciente, id_obra) values (?,?);");

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
	
	
	public Paciente getByDocumento(Paciente paciente) {
		Paciente p = new Paciente();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select * from paciente where tipo_doc=? and nro_doc=? limit 1");
			stmt.setString(1, paciente.getDocumento().getTipo());
			stmt.setInt(2, paciente.getDocumento().getNro());
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
					p.setTelefono(rs.getInt("telefono"));
					p.setDomicilio(rs.getString("domicilio"));
					p.setFechaNacimiento(rs.getDate("fecha_nac"));
					p.setSexo(rs.getString("sexo"));
					p.setObrasSociales(dos.getByPaciente(p));
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
	
	public Paciente getByEmailYPass(Persona persona) {
		Paciente p = new Paciente();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select * from paciente where email=? and password=?");
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
					p.setTelefono(rs.getInt("telefono"));
					p.setDomicilio(rs.getString("domicilio"));
					p.setFechaNacimiento(rs.getDate("fecha_nac"));
					p.setSexo(rs.getString("sexo"));
					p.setObrasSociales(dos.getByPaciente(p));
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
