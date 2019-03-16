package PettyCash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import Servlet.DB_Connection;
import Servlet.DB_Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PettyCashController {

    // Variable Declaration
    PettyCash pc = new PettyCash();
    public ArrayList<PettyCash> pettycashes = new ArrayList<PettyCash>() {
    };
    
    /*<------------------------------------------------------------------------------>
     Function     : addPettyCash
     Parameters   : String emp_id
                    String description
                    String amount
     Descrition : addPettyCash is a function to insert set of value in to Database
                  by using Employee Id, Description and Amount of PettyCash
    <------------------------------------------------------------------------------>*/
    public void addPettyCash(String emp_id, String description, String amount) {
        DB_Connection obj_DB_Connection = new DB_Connection() {
        };
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        String create_datetime = getDate();
        String code = getCode();
        String status = "Request";
        String update_datetime = getDate();
        try {
            String query = "INSERT into pettycash(Code,Emp_id,Description,Amount,Status,Create_datetime,Update_datetime) values (?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, code);
            ps.setString(2, emp_id);
            ps.setString(3, description);
            ps.setString(4, amount);
            ps.setString(5, status);
            ps.setString(6, create_datetime);
            ps.setString(7, update_datetime);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { /* ignored */

                }
            }
        }
        pc.setCode(code);
    }
    
    /*<------------------------------------------------------------------------------>
     Function     : readPettyCash
     Parameters   : none
     Descrition : readPettyCash is a function to collect set of value from Database
                  to user
    <------------------------------------------------------------------------------>*/

    public void readPettyCash() {
        DB_Connection obj_DB_Connection = new DB_Connection() {
        };
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM pettycash";
            ps = connection.prepareStatement(query);
            //ps.setString(1, sl_no);
            System.out.println(ps);
            rs = ps.executeQuery();
            pettycashes.clear();
            while (rs.next()) {
                PettyCash pettycash = new PettyCash();
                pettycash.setCode(rs.getString("Code"));
                pettycash.setEmp_id(rs.getString("Emp_id"));
                pettycash.setDescription(rs.getString("Description"));
                pettycash.setAmount(rs.getString("Amount"));
                pettycash.setStatus(rs.getString("Status"));
                pettycash.setCreate_datetime(rs.getString("Create_datetime"));
                pettycash.setUpdate_datetime(rs.getString("Update_datetime"));

                pettycashes.add(pettycash);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */

                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { /* ignored */

                }
            }
        }
    }
    
    /*<------------------------------------------------------------------------------>
     Function     : editPettyCash
     Parameters   : String code
                    String emp_id
                    String description
                    String amount
     Descrition : editPettyCash is a function to update data inside database identify
                  the data by code(Petty Cash's Code)and update Employee Id, Description
                  and Amount.
    <------------------------------------------------------------------------------>*/

    public void editPettyCash(String code, String emp_id, String description, String amount) {
        DB_Connection obj_DB_Connection = new DB_Connection() {
        };
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        String update_datetime = getDate();
        String status = "Request";
        pettycashes.clear();
        if (checkStatus(code)) {
            try {
                String query = "UPDATE pettycash SET Emp_id=?,Description=?,Amount=?,Status=?,Update_datetime=? WHERE Code=?";
                ps = connection.prepareStatement(query);
                ps.setString(1, emp_id);
                ps.setString(2, description);
                ps.setString(3, amount);
                ps.setString(4, status);
                ps.setString(5, update_datetime);
                ps.setString(6, code);
                System.out.println(ps);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
            }
        } else {
            PettyCash pettycash = new PettyCash();
            pettycash.setMessage("Unupdatable");
            pettycashes.add(pettycash);
        }
    }
    
    /*<------------------------------------------------------------------------------>
     Function     : deletePettyCash
     Parameters   : String code
     Descrition : deletePettyCash is a function to delete data inside database identify
                  the data by code(Petty Cash's Code)
    <------------------------------------------------------------------------------>*/
    public void deletePettyCash(String code) {
        DB_Connection obj_DB_Connection = new DB_Connection() {
        };
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        PettyCash pettycash = new PettyCash();
        pettycashes.clear();
        if (checkStatus(code)) {
            try {
                String query = "DELETE FROM pettycash WHERE code=?";
                ps = connection.prepareStatement(query);
                ps.setString(1, code);
                System.out.println(ps);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
            }

            pettycash.setMessage("Success");
            pettycashes.add(pettycash);

        } else {

            pettycash.setMessage("Undeletable");
            pettycashes.add(pettycash);
        }

    }
    
    /*<------------------------------------------------------------------------------>
     Function     : getPettyCashDetail
     Parameters   : String code
     Descrition : getPettyCashDetail is a function to collect specific data inside database identify
                  the data by code(Petty Cash's Code)
    <------------------------------------------------------------------------------>*/

    public void getPettyCashDetail(String code) {
        DB_Connection obj_DB_Connection = new DB_Connection() {
        };
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        pettycashes.clear();
        if (checkStatus(code)) {
            try {
                String query = "SELECT * FROM pettycash WHERE code=?";
                ps = connection.prepareStatement(query);
                ps.setString(1, code);
                System.out.println(ps);
                rs = ps.executeQuery();
                System.out.println(ps);
                while (rs.next()) {
                    PettyCash pettycash = new PettyCash();
                    pettycash.setCode(rs.getString("Code"));
                    pettycash.setEmp_id(rs.getString("Emp_id"));
                    pettycash.setDescription(rs.getString("Description"));
                    pettycash.setAmount(rs.getString("Amount"));
                    pettycash.setStatus(rs.getString("Status"));
                    pettycash.setCreate_datetime(rs.getString("Create_datetime"));
                    pettycash.setUpdate_datetime(rs.getString("Update_datetime"));

                    pettycashes.add(pettycash);
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
            }
        } else {
            PettyCash pettycash = new PettyCash();
            pettycash.setMessage("Uneditable");
            pettycashes.add(pettycash);
        }
    }
    
    /*<------------------------------------------------------------------------------>
     Function     : cancelPettyCash
     Parameters   : String code
     Descrition : cancelPettyCash is a function to update status of petty cash inside database 
                  identify the data by code(Petty Cash's Code)and update status to "Cancel"
    <------------------------------------------------------------------------------>*/

    public void cancelPettyCash(String code) {
        DB_Connection obj_DB_Connection = new DB_Connection() {
        };
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        String update_datetime = getDate();
        String status = "Cancel";
        pettycashes.clear();
        if (checkStatus(code)) {
            try {
                String query = "UPDATE pettycash SET Status=?,Update_datetime=? WHERE Code=?";
                ps = connection.prepareStatement(query);
                ps.setString(1, status);
                ps.setString(2, update_datetime);
                ps.setString(3, code);
                System.out.println(ps);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
            }
            PettyCash pettycash = new PettyCash();
            pettycash.setMessage("Success");
            pettycashes.clear();
            pettycashes.add(pettycash);
        } else {
            PettyCash pettycash = new PettyCash();
            pettycash.setMessage("Uncancelable");
            pettycashes.clear();
            pettycashes.add(pettycash);
        }
    }
    
     /*<------------------------------------------------------------------------------>
     Function     : checkStatus
     Parameters   : String code
     Descrition : checkStatus is a function to check the status inside database.
                  if the status is Request or Not Approve the fucntion will return true
                  if the status is other return false
    <------------------------------------------------------------------------------>*/

    public boolean checkStatus(String code) {
        DB_Connection obj_DB_Connection = new DB_Connection() {
        };
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        pettycashes.clear();
        try {
            String query = "SELECT Status FROM pettycash WHERE code=?";
            ps = connection.prepareStatement(query);
            ps.setString(1, code);
            System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("Status").equals("Request")) {
                    return true;
                } else if (rs.getString("Status").equals("Not Approve")) {
                    return true;
                } else {
                    return false;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */

                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { /* ignored */

                }
            }
        }
        return false;
    }

    /*<------------------------------------------------------------------------------>
     Function     : approvePettyCash
     Parameters   : String code
     Descrition : approvePettyCash is a function to update status of petty cash inside database 
                  identify the data by code(Petty Cash's Code)and update status to "Approve"
    <------------------------------------------------------------------------------>*/
    public void approvePettyCash(String code) {
        DB_Connection obj_DB_Connection = new DB_Connection() {
        };
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        String update_datetime = getDate();
        String status = "Approve";
        pettycashes.clear();
        if (checkStatus(code)) {
            try {
                String query = "UPDATE pettycash SET Status=?,Update_datetime=? WHERE Code=?";
                ps = connection.prepareStatement(query);
                ps.setString(1, status);
                ps.setString(2, update_datetime);
                ps.setString(3, code);
                System.out.println(ps);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
            }
        } else {
            PettyCash pettycash = new PettyCash();
            pettycash.setMessage("Uncancelable");
            pettycashes.clear();
            pettycashes.add(pettycash);
        }
    }

    
    /*<------------------------------------------------------------------------------>
     Function     : notApprovePettyCash
     Parameters   : String code
     Descrition : notApprovePettyCash is a function to update status of petty cash inside database 
                  identify the data by code(Petty Cash's Code)and update status to "Not Approve"
    <------------------------------------------------------------------------------>*/
    public void notApprovePettyCash(String code) {
        DB_Connection obj_DB_Connection = new DB_Connection() {
        };
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        String update_datetime = getDate();
        String status = "Not Approve";
        pettycashes.clear();
        if (checkStatus(code)) {
            try {
                String query = "UPDATE pettycash SET Status=?,Update_datetime=? WHERE Code=?";
                ps = connection.prepareStatement(query);
                ps.setString(1, status);
                ps.setString(2, update_datetime);
                ps.setString(3, code);
                System.out.println(ps);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */

                    }
                }
            }
        } else {
            PettyCash pettycash = new PettyCash();
            pettycash.setMessage("Uncancelable");
            pettycashes.clear();
            pettycashes.add(pettycash);
        }
    }
    
 

    public String getDate() {
        String create_datetime = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return create_datetime = formatter.format(date);
    }
    
    /*<------------------------------------------------------------------------------>
     Function     : getCode
     Parameters   : none
     Descrition : getCode is a function to identify Code of petty cash in "PC-Year-Number"
                  format. By the Number will collect the lastest Code +1 to be the new Code
    <------------------------------------------------------------------------------>*/

    public String getCode() {
        String code = "";
        ResultSet rs = null;
        int counter = 0;

        //Get Year for Code
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String formateddate = formatter.format(date);

        //Get the lattest number and +1 to be a new code
        DB_Connection obj_DB_Connection = new DB_Connection() {
        };
        Connection connection = obj_DB_Connection.get_connection();
        PreparedStatement ps = null;
        String create_datetime = getDate();
        try {
            String query = "SELECT Id from pettycash ORDER BY Id DESC LIMIT 1";
            ps = connection.prepareStatement(query);

            System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                counter = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) { /* ignored */

                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { /* ignored */

                }
            }
        }

        String count = "0000";
        counter++;
        count = String.format("%04d", counter);

        return code = "PC-" + formateddate + "-" + count;

    }

}
