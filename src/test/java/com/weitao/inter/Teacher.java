package com.weitao.inter;

public  class Teacher<T> implements Person<T> {

    T teacher;

    public T getTeacher() {
        return teacher;
    }

    public void setTeacher(T teacher) {
        this.teacher = teacher;
    }

    public Teacher(T t){
        this.teacher = t;
    }

    public <T> T printIn(T t){
        System.out.println(t);
        return t;
    }

    @Override
    public T parent() {
        return null;
    }

    @Override
    public String eat() {
        return null;
    }

    public static void main(String[] args) {
        Teacher<String> te = new Teacher<String>("sss");
        System.out.println(te.getTeacher());
        Integer in = te.printIn(1000);

    }
}
