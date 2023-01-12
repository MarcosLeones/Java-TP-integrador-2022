package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import entities.Persona;
import entities.Turno;

public class DataTurno {

	private DataPersona dp = new DataPersona();
	
	public ArrayList<Turno> getTurnosDisponibles(Persona profesional, LocalDate fecha) {
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT * FROM turno"
				+ " WHERE estado='disponible' and legajo_profesional=? and fecha>=? "
				+ " ORDER BY fecha, hora ");
			stmt.setInt(1, profesional.getLegajo());
			stmt.setObject(2, fecha);
			rs=stmt.executeQuery();
		
			if (rs != null) {
				while (rs.next()) {
					Turno t = new Turno();
					Persona p = new Persona();
					p = dp.getByLegajo(rs.getInt("legajo_profesional"));
					t.setProfesional(p);
					t.setId(rs.getInt("id_turno"));
					t.setFecha(rs.getDate("fecha").toLocalDate());
					t.setHora(rs.getTime("hora").toLocalTime());
					t.setEstado(rs.getString("estado"));

					turnos.add(t);
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
		
		return turnos;
	}

	public void updateTurno(Turno turno) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;

		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE turno SET legajo_profesional=?, legajo_paciente=?, fecha=?, hora=?, estado=? " 
					+ " WHERE id_turno=?"
					);
			stmt.setInt(1, turno.getProfesional().getLegajo());			
			//stmt.setInt(2, turno.getPaciente().getLegajo());
			if (turno.getPaciente().getLegajo() == 0) {
				stmt.setNull(2, Types.INTEGER);
			} else {
				stmt.setInt(2, turno.getPaciente().getLegajo());
			}
			stmt.setObject(3, turno.getFecha());
			stmt.setObject(4, turno.getHora());
			stmt.setString(5, turno.getEstado());
			stmt.setInt(6, turno.getId());
			
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

	public ArrayList<Turno> getReservas(Persona paciente) {
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT * FROM turno WHERE legajo_paciente=? order by fecha, hora");
			stmt.setInt(1, paciente.getLegajo());
			rs=stmt.executeQuery();
		
			if (rs != null) {
				while (rs.next()) {
					Turno t = new Turno();
					Persona p;
					p = dp.getByLegajo(rs.getInt("legajo_profesional"));
					t.setProfesional(p);
					p= dp.getByLegajo(paciente.getLegajo());
					t.setPaciente(p);
					t.setId(rs.getInt("id_turno"));
					t.setFecha(rs.getDate("fecha").toLocalDate());
					t.setHora(rs.getTime("hora").toLocalTime());
					t.setEstado(rs.getString("estado"));

					turnos.add(t);
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
		return turnos;
	}
	

	public void reservarTurno(Turno turno) throws Exception {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;

		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE turno SET legajo_paciente = ?, estado=? " 
					+ " WHERE id_turno=? and estado='disponible' and legajo_paciente is null"
					);
		
			stmt.setInt(1, turno.getPaciente().getLegajo());
			stmt.setString(2, turno.getEstado());
			stmt.setInt(3, turno.getId());
			
			int filasAfectadas = stmt.executeUpdate();

			if (filasAfectadas < 1) {
				throw new Exception("Ha ocurrido un problema inesperado, el turno NO ha sido reservado.");
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
	
	
	public void add(ArrayList<Turno> turnos) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		
		try {
			
			StringBuilder query = new StringBuilder("INSERT INTO turno (legajo_profesional, fecha, hora, estado) VALUES ");

			if (turnos.size() <= 1000) {
			    
			    for (int i = 0; i < turnos.size(); i++)
			    {
			    	query.append("(?, ?, ?, ?) ");
			    	if (i != (turnos.size() - 1)) query.append(","); 
			    }
			    //query = new StringBuilder(query.substring(0, query.length() - 1));

			    stmt = DbConnector.getInstancia().getConn().prepareStatement(query.toString());

			    for (int i = 0; i < turnos.size(); i++) {		    	
			    	stmt.setInt((i * 4) + 1, turnos.get(i).getProfesional().getLegajo());			    	
			    	stmt.setObject((i * 4) + 2, turnos.get(i).getFecha());			    	
			    	stmt.setObject((i * 4) + 3, turnos.get(i).getHora());			    	
			    	stmt.setString((i * 4) + 4, turnos.get(i).getEstado());			    	
			    }			    
			    stmt.executeUpdate();		    
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

	public LocalDate getFechaUltimoTurno(Persona profesional) {
		LocalDate fecha = LocalDate.now();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select ifnull(max(fecha), curdate()) as fecha "
					+ " from turno where legajo_profesional=? ");
			stmt.setInt(1, profesional.getLegajo());
			rs=stmt.executeQuery();
			
			if (rs != null) {
				if (rs.next()) {
					fecha = (LocalDate)rs.getDate("fecha").toLocalDate();
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
		return fecha;
	}
	
	
	public ArrayList<Turno> getTurnosCreados(Persona profesional, LocalDate fecha){
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT * FROM turno"
				+ " WHERE estado='creado' and legajo_profesional=? and fecha>=? "
				+ " ORDER BY fecha, hora ");
			stmt.setInt(1, profesional.getLegajo());
			stmt.setObject(2, fecha);
			rs=stmt.executeQuery();
		
			if (rs != null) {
				while (rs.next()) {
					Turno t = new Turno();
					Persona p = new Persona();
					p = dp.getByLegajo(rs.getInt("legajo_profesional"));
					t.setProfesional(p);
					t.setId(rs.getInt("id_turno"));
					t.setFecha(rs.getDate("fecha").toLocalDate());
					t.setHora(rs.getTime("hora").toLocalTime());
					t.setEstado(rs.getString("estado"));

					turnos.add(t);
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
		
		return turnos;
	}
	
	
	public void updateDisponible(ArrayList<Turno> turnos) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;

		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE turno SET estado=? WHERE id_turno=?");
			for (Turno t : turnos) {
				stmt.setString(1, t.getEstado());
				stmt.setInt(2, t.getId());
				stmt.addBatch();
			}
			stmt.executeBatch();			
			
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
	
	public Turno getById(Turno turno) {
		Turno t = new Turno();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from turno where id_turno=?"
					);
			stmt.setInt(1, turno.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				t.setId(rs.getInt("id_turno"));
				t.setFecha(rs.getDate("fecha").toLocalDate());
				t.setHora(rs.getTime("hora").toLocalTime());
				t.setEstado(rs.getString("estado"));
				Persona p = new Persona();
				p = dp.getByLegajo(rs.getInt("legajo_profesional"));
				t.setProfesional(p);
				p = dp.getByLegajo(rs.getInt("legajo_paciente"));
				t.setPaciente(p);
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
		
		return t;
	
	}
}
