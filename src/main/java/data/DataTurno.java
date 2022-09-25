package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			stmt.setInt(2, turno.getPaciente().getLegajo());
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
			stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT * FROM turno WHERE legajo_paciente=? ");
			stmt.setInt(1, paciente.getLegajo());
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
	

	public void reservarTurno(Turno turno) throws Exception {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;

		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE turno SET legajo_paciente = ?, estado=? " 
					+ " WHERE id_turno=? and estado='disponible'"
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
	
}
