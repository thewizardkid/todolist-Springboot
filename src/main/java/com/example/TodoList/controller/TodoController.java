package com.example.TodoList.controller;

import com.example.TodoList.Todo;
import com.example.TodoList.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
//Session attribute stores the value in the http session
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //when the user views the to-do dashboard
    @GetMapping(value = "/list-todos")
    public String ListAllTodos(ModelMap model){
        List<Todo> todos = todoService.findByUsername("niggahoe");
        model.addAttribute("name","niggahoe");
        //We are passing the list of todos as an attribute to the table in listTodos jsp
        model.addAttribute("todos", todos);
        return "ListTodos";
    }

    //Return newpage jsp when user clicks to add new todo
    @GetMapping(value = "/add-todo")
    public String showNewTodoPage(ModelMap model){
        String username = (String)model.get("name");
        Todo todo = new Todo(0, username, "Enter Task Description", LocalDate.now(), false);
        model.put("todo",todo);
        return "todo";
    }

    @PostMapping(value = "/add-todo")
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String username= (String)model.get("name");
        todoService.addTodo(username,todo.getDescription(), LocalDate.now(),false);
        return "redirect:list-todos";
    }

    @GetMapping(value = "/delete-todo")
    public String deleteTodo(@RequestParam int id){
        todoService.deleteByid(id);
        return "redirect:list-todos";
    }

    @GetMapping(value = "/update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model){
        String username = (String)model.get("name");
        Todo todo = todoService.findByid(id);
        model.put("todo",todo);
        return "todo";
    }

    @PostMapping(value = "/update-todo")
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String username= (String)model.get("name");
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }



}
