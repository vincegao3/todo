/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.todoList.TodoList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ToDoList {
    private int id;
    private String data;
    private boolean completed;
    private String status;
    private int userID;
    
    public int getID(){
        return id;
    }
    public String getData(){
        return data;
    }
    public boolean getCompleted(){
        return completed;
    }
    public String getStatus(){
        return status;
    }
    public int getUserID(){
        return userID;
    }
    
     public void setID(int id){
        this.id = id;
    }
    public void setData(String userInput){
        this.data = userInput;
    }
    public void isCompleted(boolean userInput){
        this.completed = userInput;
    }
    public void setStatus(String userInput){
        this.status = userInput;
    }
     public void setUserID(int userInput){
        this.userID = userInput;
    }
}
