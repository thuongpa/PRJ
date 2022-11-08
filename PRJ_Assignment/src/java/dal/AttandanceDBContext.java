/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attandance;

/**
 *
 * @author Trường Xuân
 */
public class AttandanceDBContext extends DBContext<Attandance> {

    @Override
    public void insert(Attandance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attandance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attandance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attandance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attandance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Attandance getBySesidAndStdid(int sesid, int stdid) {
        Attandance att = null;
        try {
            String sql = "Select * FROM Attandance where sesid=? and stdid=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sesid);
            stm.setInt(2, stdid);
            ResultSet rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    att = new Attandance();
                    att.setPresent(rs.getBoolean("present"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttandanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return att;
    }

    public Map<Integer, Double> getTotalAbsent(int gid) {
        Map<Integer, Double> list = new HashMap<>();
        SessionDBContext ses = new SessionDBContext();
        try {
            String sql = "select s.stdid, sum(case present when 0 then 1 else 0 end) as [Absent]\n"
                    + "from Session ses inner join [Group] g on ses.gid = g.gid\n"
                    + "inner join Subject sub on sub.subid = g.subid\n"
                    + "inner join Student_Group sg on sg.gid = g.gid\n"
                    + "inner JOIN [Student] s ON s.stdid = sg.stdid\n"
                    + "LEFT JOIN Attandance a ON s.stdid = a.stdid AND ses.sesid = a.sesid\n"
                    + "where g.gid = ?\n"
                    + "group by s.stdid	";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int absent = rs.getInt("Absent");
                int stdid = rs.getInt("stdid");
                double result = absent*100/ses.getTotalSlotByGid(gid);
                list.put(stdid, result);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AttandanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
}

