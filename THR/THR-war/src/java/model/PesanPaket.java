package model;


import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import util.Database;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */
public class PesanPaket {
    private int ido;
    private int idp;
    private int idc;
    private int idi;
    private boolean pay_status;
    private int jumlah_paket;
    private Date order_date;
    private String order_dateS;
    private Date due_date;
    private String customer_name;
    private String paket_name;
    private String item_name;
    private int jumlah_item;
    

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getJumlah_paket() {
        return jumlah_paket;
    }

    public void setJumlah_paket(int jumlah_paket) {
        this.jumlah_paket = jumlah_paket;
    }

    public Date getOrder_dateD() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getOrder_dateS() throws ParseException {
        return DateFormater.formatDateToView(order_date.toString());
    }

    public void setOrder_dateS(String order_dateS) throws ParseException {
        this.order_dateS = DateFormater.formatDateToDBFormat(order_dateS);
    }

    public boolean isPay_status() {
        return pay_status;
    }

    public void setPay_status(boolean pay_status) {
        this.pay_status = pay_status;
    }
    
    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPaket_name() {
        return paket_name;
    }

    public void setPaket_name(String paket_name) {
        this.paket_name = paket_name;
    }

    public int getIdi() {
        return idi;
    }

    public void setIdi(int idi) {
        this.idi = idi;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getJumlah_item() {
        return jumlah_item;
    }

    public void setJumlah_item(int jumlah_item) {
        this.jumlah_item = jumlah_item;
    }
    
    public String addPesanPaket()
    {
        Database db = new Database();
        String sql = null;
        try{
            sql="INSERT INTO pesan_paket (idp,idc,jumlah_paket,order_date,due_date) VALUES "
                   /*+ "('aa','las','add','phon','emil','pla','00-00-0000','asd')";*/
                    
                    
                    
                    + "("+this.getIdp()+","+this.getIdc()+","+this.getJumlah_paket()+","
                    + "NOW()" +","+"NOW()"+")";
            db.setConnection();
            db.updatingQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            db.unsetConnection();
        }
        return sql;
    }
    
    public ArrayList<PesanPaket> getPesanPaketbyIdc(String idc){
        ResultSet rs;
        ArrayList<PesanPaket> temp =new ArrayList<PesanPaket>();
        String sql;
        try{
            sql="SELECT * FROM pesan_paket WHERE idc="+idc;
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanPaket c = new PesanPaket();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idc"));
                c.setJumlah_paket(rs.getInt("jumlah_paket"));
                c.setOrder_date(rs.getDate("order_date"));
                c.setDue_date(rs.getDate("due_date"));
                if (rs.getInt("pay_status")==1)
                    c.setPay_status(true);
                else c.setPay_status(false);
                temp.add(c);
                System.out.println(c.getIdc());
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public ArrayList<PesanPaket> getPesanPaket(String orderby){
        ResultSet rs;
        ArrayList<PesanPaket> temp =new ArrayList<PesanPaket>();
        String sql = null;
        try{
            if(orderby.equals("")){
                sql="select ido,pesan_paket.idp,pesan_paket.idc, paket_name, order_date, pay_status, jumlah_paket "
                        + "from pesan_paket, paket_jalan where pesan_paket.idp=paket_jalan.idp";
            }else{
                sql="select ido,pesan_paket.idp,pesan_paket.idc, paket_name, order_date, pay_status, jumlah_paket "
                        + "from pesan_paket, paket_jalan where pesan_paket.idp=paket_jalan.idp order by "+orderby+ " desc";
            }
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanPaket c = new PesanPaket();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idc"));
                c.setPaket_name(rs.getString("paket_name"));
                c.setOrder_date(rs.getDate("order_date"));
                c.setJumlah_paket(rs.getInt("jumlah_paket"));
                if (rs.getInt("pay_status")==1){
                    c.setPay_status(true);
                }else { 
                    c.setPay_status(false); 
                }
                temp.add(c);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public ArrayList<PesanPaket> getPesanPaketbyItem(String orderby){
        ResultSet rs;
        ArrayList<PesanPaket> temp =new ArrayList<PesanPaket>();
        String sql = null;
        try{
            if(orderby.equals("")){
                sql="select distinct ido, item_jalan.idi, pesan_paket.idp, item_jalan.name, sum(jumlah_paket) as jumlah "
                        + "from pesan_paket, item_jalan, ip_jalan where pesan_paket.idp=ip_jalan.idp and ip_jalan.idi=item_jalan.idi group by idi";
            }else{
                sql="select distinct ido, item_jalan.idi, pesan_paket.idp, item_jalan.name, sum(jumlah_paket) as jumlah "
                        + "from pesan_paket, item_jalan, ip_jalan where pesan_paket.idp=ip_jalan.idp and ip_jalan.idi=item_jalan.idi group by idi order by "+orderby+ " desc";
            }
            Database.setConnection();
            System.out.println(sql);
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanPaket c = new PesanPaket();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idi"));
                c.setItem_name(rs.getString("name"));
                c.setJumlah_item(rs.getInt("jumlah"));
                temp.add(c);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public static void main(String[] args) throws ParseException {
        PesanPaket p = new PesanPaket();
        ArrayList<PesanPaket> ap = new ArrayList<PesanPaket>();
        
        ap = p.getPesanPaketbyItem("name");
        for(int i=0; i<ap.size();i++){
            //System.out.println(ap.get(i).getOrder_dateS());
            System.out.println(ap.get(i).getItem_name());                        
        }
        
    }
    
}


