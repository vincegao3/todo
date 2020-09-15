/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.todoList.TodoList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yiu
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/api")    
public class RestfulController {
            
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @GetMapping("/getList")                  
    public List<ToDoList> getList() {
        List<ToDoList> datalist = jdbcTemplate.query("SELECT * FROM TEST.TODOLIST",new Mapper());
        return datalist;
    }
    
    @GetMapping("/database")
    public void db(){
         try {
            jdbcTemplate.execute("DROP TABLE TODOLIST");
        } catch (Exception e) {
            System.out.print(e);
        }
        jdbcTemplate.execute("CREATE TABLE TODOLIST(id INTEGER NOT NULL, TODODATA VARCHAR(255), COMP BOOLEAN NOT NULL, Status VARCHAR(255))");
    }
    
    @PostMapping()
    public List<ToDoList> createTodoList(@RequestBody(required = false) List<ToDoList> userTodos) {
        jdbcTemplate.execute("TRUNCATE TABLE TEST.TODOLIST");
        jdbcTemplate.batchUpdate("INSERT INTO TEST.TODOLIST (id, tododata, comp, status,userID) VALUES (?,?,?,?,?)",
            new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, userTodos.get(i).getID());
                ps.setString(2, userTodos.get(i).getData());
                ps.setBoolean(3, userTodos.get(i).getCompleted());
                ps.setString(4, userTodos.get(i).getStatus());
                ps.setInt(5, userTodos.get(i).getUserID());
            }

            @Override
            public int getBatchSize() {
                return userTodos.size();//To change body of generated methods, choose Tools | Templates.
            }
 
        });
 
        return userTodos;
    }
    @PostMapping("/signup")
    public void createNewUser(@RequestBody(required = false) User newUserData){
        
    }
    @PostMapping("/login")
    public void login(@RequestBody(required = false) User loginUser){}
}
