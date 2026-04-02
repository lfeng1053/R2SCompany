package sales.dao;

import sales.client.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {
    private final Connection conn;

    public EmployeeDAO(Connection conn){
        this.conn = conn;
    }


    //Select all
    public ArrayList<Employee> selectAll() throws SQLException{
        if(conn == null){
            return null;
        }
        String select = "select * from employees";
        ArrayList<Employee> employees = new ArrayList<>();

        try(Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(select);){
                while (rs.next()){
                    Employee employee = new Employee();

                    employee.setId(rs.getInt("Employee_ID"));
                    employee.setLastName(rs.getString("Last_Name"));
                    employee.setFirstName(rs.getString("First_Name"));
                    employee.setBirthdate(rs.getString("Birth_Date"));
                    employee.setSupervisor(rs.getInt("Supervisor_ID"));

                    employees.add(employee);

                }
        } catch (SQLException e){
            throw new SQLException("Can not display employee" + e.getMessage());
        }
        return employees;
    }

    //Insert
    public boolean insert(Employee employee) throws SQLException{
        if(conn == null){
            return false;
        }

        String insert = "insert into employees(Last_Name, First_Name, Birth_Date, Supervisor_ID)" + "values(?,?,?,?)";
        int index = 1;
        try(PreparedStatement ps = conn.prepareStatement(insert);){
            ps.setString(index++, employee.getLastName());
            ps.setString(index++, employee.getFirstName());
            ps.setString(index++, employee.getBirthdate());
            ps.setInt(index++, employee.getSupervisor());

            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch(SQLException e){
            throw new SQLException("Can not insert employee" + e.getMessage());
        }
        return false;
    }

    //Update
    public boolean update(int id, Employee employee) throws SQLException{
        if(conn == null){
            return false;
        }

        String update = "update employees set Last_Name = ?, First_Name = ?, Birth_Date = ?, Supervisor_ID = ? where Employee_ID = ?";
        int index = 1;
        try(PreparedStatement ps = conn.prepareStatement(update);){
            ps.setString(index++, employee.getLastName());
            ps.setString(index++, employee.getFirstName());
            ps.setString(index++, employee.getBirthdate());
            ps.setInt(index++, employee.getSupervisor());
            ps.setInt(index++, id);

            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e ){
            throw new SQLException("Can not update employee" + e.getMessage());
        }
        return false;
    }
    //Delete
    public boolean delete(int id) throws SQLException{
        if(conn == null){
            return false;
        }
        String delete = "delete from employees where Employee_ID = ?";
        int index = 1;
        try(PreparedStatement ps = conn.prepareStatement(delete)){
            ps.setInt(index++,id);

            if(ps.executeUpdate() > 0){
                return true;
            }

        } catch (SQLException e){
            throw new SQLException("Can not delete employee" + e.getMessage());
        }
        return false;
    }



}
