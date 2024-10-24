package com.example.TodoList.services;

import com.example.TodoList.Todo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int todoscount=0;
    static{
        todos.add(new Todo(++todoscount,"niggahoe","Task 1", LocalDate.now(),false));
        todos.add(new Todo(++todoscount,"niggahoe","Task 2", LocalDate.now(),false));
        todos.add(new Todo(++todoscount,"niggahoe","Task 3", LocalDate.now(),false));
    }

    public static List<Todo> findByUsername(String username) {
        return todos;
    }

    public void addTodo(String username,String description,LocalDate targetDate,Boolean done){
        todos.add(new Todo(++todoscount,username,description,targetDate,done));
    }

    public void deleteByid(int id){
        //todo.getId()==id
        Predicate<? super Todo> predicate = todo -> todo.getId()==id ;
        todos.removeIf(predicate);
    }

    //returns todo of the task which has to be updated by finding through its id
    public Todo findByid(int id){
        //todo.getId()==id
        Predicate<? super Todo> predicate = todo -> todo.getId()==id ;
        Todo todo =todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    //naive method where we first delete the task and then add the updated task
    public void updateTodo(@Valid Todo todo) {
        deleteByid(todo.getId());
        todos.add(todo);


    }
}
