package com.weitao.socket.chap03;

public class ShallowCopy {
    public static void main(String[] args) {
       /* Subject subject = new Subject("yuwen");

        Student studentA = new Student();
        studentA.setSubject(subject);
        studentA.setName("Lynn");
        studentA.setAge(20);

        Student studentB = (Student)studentA.clone();
        studentB.setName("Lily");
        studentB.setAge(18);
        Subject subjectB = studentB.getSubject();
        subjectB.setName("lishi");

        System.out.println("studentA:" + studentA.toString());
        System.out.println("studentB:" + studentB.toString());*/
        //studentA:[Student: 610998173,subject:[Subject: 2047329716,name:lishi],name:Lynn,age:20]
        //studentB:[Student: 648129364,subject:[Subject: 2047329716,name:lishi],name:Lily,age:18]


        Subject subject = new Subject("yuwen");

        Student studentA = new Student();
        studentA.setSubject(subject);
        studentA.setName("Lynn");
        studentA.setAge(20);

        Student studentB = studentA;
        studentB.setName("Lily");
        studentB.setAge(18);

        Subject subjectB = studentB.getSubject();
        subjectB.setName("lishi");
        System.out.println("studentA:" + studentA.toString());
        System.out.println("studentB:" + studentB.toString());
        //copy https://www.jianshu.com/p/94dbef2de298
    }
}
