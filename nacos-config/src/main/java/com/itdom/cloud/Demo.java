package com.itdom.cloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Person[] personArray = {new Person("zhansgan"), new Person("lisi"), new Person("wangwu")};
        Person[] people = new Person[personArray.length];
        System.arraycopy(personArray, 0, people, 0, personArray.length);
        List<Person> personList = Arrays.asList(personArray);
        List<Person> personList2 = Arrays.asList(people);
        System.out.println(people.toString());
        System.out.println(personArray.toString());
        System.out.println(personList.toString());
        System.out.println(personList2.toString());

    }

    static class Person {
        private String username;

        public Person(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

//       @Override
//       public String toString() {
//           return "Person{" +
//                   "username='" + username + '\'' +
//                   '}';
//       }


    }
}
