/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.todoList.TodoList;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author yiu
 */
public class Mapper implements RowMapper<ToDoList> {

    @Override
    public ToDoList mapRow(ResultSet rs, int i) throws SQLException {

        ToDoList data = new ToDoList();
        data.setID(rs.getInt("id"));
        data.setData(rs.getString("TODODATA"));
        data.isCompleted(rs.getBoolean("COMP"));
        data.setStatus(rs.getString("STATUS"));
        data.setUserID(rs.getInt("USERID"));

        return data;

    }

}
