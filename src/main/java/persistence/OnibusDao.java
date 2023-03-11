package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Onibus;

public class OnibusDao implements IOnibusDao {
	
	private GenericDao gDao;
	
	public OnibusDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String insereOnibus(Onibus o) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "INSERT INTO onibus VALUES (?, ?, ?, ?)";
		
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, o.getPlaca());
        ps.setString(2, o.getMarca());
        ps.setInt(3, o.getAno());
        ps.setString(4, o.getDescricao());
        ps.execute();
        ps.close();
        c.close();
        
        return "Onibus inserido com sucesso";
	}

	@Override
	public String atualizaOnibus(Onibus o) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "UPDATE onibus SET marca = ?, ano = ?, descricao = ? WHERE placa = ?";
		
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, o.getMarca());
        ps.setInt(2, o.getAno());
        ps.setString(3, o.getDescricao());
        ps.setString(4, o.getPlaca());
        ps.execute();
        ps.close();
        c.close();
        
        return "Onibus atualizado com sucesso";
	}

	@Override
	public String excluiOnibus(Onibus o) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "DELETE onibus WHERE placa = ?";
		
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, o.getPlaca());
        ps.execute();
        ps.close();
        c.close();
        
        return "Onibus excluido com sucesso";
	}

	@Override
	public Onibus consultaOnibus(Onibus o) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "SELECT placa, marca, ano, descricao FROM onibus WHERE placa = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, o.getPlaca());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			o.setPlaca(rs.getString("placa"));
            o.setMarca(rs.getString("marca"));
            o.setAno(rs.getInt("ano"));
            o.setDescricao(rs.getString("descricao"));
		}
		rs.close();
		ps.close();
		c.close();
		
		return o;
	}

	@Override
	public List<Onibus> consultaListOnibus() throws SQLException, ClassNotFoundException {
		List<Onibus> listOnibus = new ArrayList<Onibus>();
		
		Connection c = gDao.getConnection();
		
		String sql = "SELECT placa, marca, ano, descricao FROM Onibus";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Onibus o = new Onibus();
			o.setPlaca(rs.getString("placa"));
            o.setMarca(rs.getString("marca"));
            o.setAno(rs.getInt("ano"));
            o.setDescricao(rs.getString("descricao"));
            
            listOnibus.add(o);
		}
		rs.close();
		ps.close();
		c.close();
		
		return listOnibus;
	}

}
