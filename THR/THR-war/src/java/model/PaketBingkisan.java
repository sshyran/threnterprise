package model;

import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Database;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Didik
 */
public class PaketBingkisan {
    private int idp;
    private String paket_name;
    private String description;
    private int price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getPaket_name() {
        return paket_name;
    }

    public void setPaket_name(String paket_name) {
        this.paket_name = paket_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPaket_asJSON() {
        String json = "";

        ArrayList<PaketBingkisan> aPB = getPaket();

        Gson gson = new Gson();
        json = gson.toJson(aPB);
        return json;
    }

    public String getPaket_asJSON(String idp) {
        String json = "";
        ArrayList<PaketBingkisan> bingkisan = getPaket(idp);
        json = new Gson().toJson(bingkisan);
        return json;
    }

    public ArrayList<PaketBingkisan> getPaket() {
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketBingkisan> temp = new ArrayList<PaketBingkisan>();
        String sql;
        try {
            sql = "SELECT * FROM paket_bingkisan";
            Database.setConnection();
            rs = Database.executingQuery(sql);
            while (rs.next()) {
                PaketBingkisan pb = new PaketBingkisan();
                pb.setIdp(rs.getInt("idp"));
                pb.setPaket_name(rs.getString("paket_name"));
                pb.setDescription(rs.getString("description"));
                pb.setPrice(rs.getInt("price"));
                temp.add(pb);
            }
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
        return temp;
    }

    public ArrayList<PaketBingkisan> getPaket(String idp) {
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketBingkisan> temp = new ArrayList<PaketBingkisan>();
        String sql;
        try {
            sql = "SELECT * FROM paket_bingkisan WHERE idp=" + idp;
            Database.setConnection();
            rs = Database.executingQuery(sql);
            while (rs.next()) {
                PaketBingkisan pb = new PaketBingkisan();
                pb.setIdp(rs.getInt("idp"));
                pb.setPaket_name(rs.getString("paket_name"));
                pb.setDescription(rs.getString("description"));
                pb.setPrice(rs.getInt("price"));
                temp.add(pb);
            }
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
        return temp;
    }

    public ArrayList<PaketBingkisan> getSearchResult(String h, String op, String n, String d) {
        String query = "SELECT * FROM paket_bingkisan";
        if (!h.equals("") && !op.equals("")) {
            query = query.concat(" WHERE price " + op + h);
        }
        if (!n.equals("")) {
            query = query.concat(" AND paket_name like \"%" + n + "%\"");
        }
        if (!d.equals("")) {
            query = query.concat(" AND description like \"%" + d + "%\"");
        }
        Database.setConnection();
        ResultSet rs;
        rs = Database.executingQuery(query);
        ArrayList<PaketBingkisan> temp = new ArrayList<PaketBingkisan>();
        try {
            while (rs.next()) {
                PaketBingkisan pj = new PaketBingkisan();
                pj.setIdp(rs.getInt("idp"));
                pj.setPaket_name(rs.getString("paket_name"));
                pj.setDescription(rs.getString("description"));
                pj.setPrice(rs.getInt("price"));
                temp.add(pj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaketBingkisan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    public void setPaket(String nama, String desk, String harga) {
        String sql;
        try {
            Database.setConnection();
            sql = "INSERT INTO paket_bingkisan (`paket_name`,`description`,`price`) VALUES('" + nama + "','" + desk + "','" + harga + "')";
            Database.updatingQuery(sql);
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
    }

    public int lastID() {
        int i = 0;
        String sql;
        ResultSet rs;
        try {
            Database.setConnection();
            sql = "select idp from paket_bingkisan order by idp desc limit 1";
            rs = Database.executingQuery(sql);
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
        return i;
    }

    public void deleteP(String id) {
        String sql = null;
        try {
            sql = "DELETE FROM paket_bingkisan WHERE idp='" + id + "'";
            Database.setConnection();
            Database.updatingQuery(sql);
            System.out.println(sql);
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
    }

    public PaketBingkisan getPaketbyid(String id) {
        ResultSet rs;
        PaketBingkisan pb = new PaketBingkisan();
        String Sql;
        Sql = "SELECT * FROM paket_bingkisan WHERE idp='" + id + "'";
        Database.setConnection();
        rs = Database.executingQuery(Sql);
        try {
            while (rs.next()) {
                pb.setIdp(rs.getInt("idp"));
                pb.setPaket_name(rs.getString("paket_name"));
                pb.setDescription(rs.getString("description"));
                pb.setPrice(rs.getInt("price"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pb;
    }

    public String updatePaket(String idp, String name, String desc, String price) {
        String sql = null;
        try {
            sql = "UPDATE paket_bingkisan SET  paket_name =  '" + name + "', description = '" + desc + "', "
                    + "price = '" + price + "' WHERE idp =" + idp;
            Database.setConnection();
            Database.updatingQuery(sql);
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
        return sql;
    }

    public String getFilterAsJSON(String mark, String price, String name, String desc) throws SQLException {
//        //Query string building
//        String query = "SELECT * FROM paket_bingkisan";
//        if (!mark.equals("") && !price.equals("")) {
//            query = query.concat(" WHERE price " + mark + price);
//        }
//        if (!name.equals("")) {
//            query = query.concat(" AND paket_name like \"%" + name + "%\"");
//        }
//        if (!desc.equals("")) {
//            query = query.concat(" AND description like \"%" + desc + "%\"");
//        }
//        Database.setConnection();
//        ResultSet rs;
//        rs = Database.executingQuery(query);
//        ArrayList<PaketBingkisan> temp = new ArrayList<PaketBingkisan>();
//        while (rs.next()) {
            PaketBingkisan pj = new PaketBingkisan();
//            pj.setIdp(rs.getInt("idp"));
//            pj.setPaket_name(rs.getString("paket_name"));
//            pj.setDescription(rs.getString("description"));
//            pj.setPrice(rs.getInt("price"));
//            temp.add(pj);
//        }
        String json = new Gson().toJson(pj.getSearchResult(price, mark, name, desc));
        return json;
    }

    public static void main(String[] args) throws SQLException {
        PaketBingkisan pj = new PaketBingkisan();
//        ArrayList<PaketBingkisan> apj = new ArrayList<PaketBingkisan>();
//        //apj = pj.getSearchResult("100000", "<", "", "");
//        apj = pj.getPaket("3");
//        
//        for(int i=0;i<apj.size();++i){
//            System.out.println(apj.get(i).getPaket_name());
//        }

        System.out.println(pj.getFilterAsJSON(">", "500000", "", ""));
//        String res = null;
//        
//        PaketBingkisan pb = new PaketBingkisan();
//        res = pb.updatePaket("35","AAAA","asdsadsad","50000");
//        
//        String json = pb.getPaket_asJSON();
//        System.out.println(json);
    }
}
