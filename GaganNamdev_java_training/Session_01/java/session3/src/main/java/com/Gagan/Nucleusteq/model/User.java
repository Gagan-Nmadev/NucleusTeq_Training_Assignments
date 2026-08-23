package com.Gagan.Nucleusteq.model;

public class User {

    private Long id;
    private String name;
    private Integer age;
    private String role;

    public User(Long id, String name, Integer age, String role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public User() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getAge() { return age; }
    public String getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(Integer age) { this.age = age; }
    public void setRole(String role) { this.role = role; }
}