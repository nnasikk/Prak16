package com.company;

public class Terminal {
    public static Object monitor = new Object();
    public Person person = new Person("John", 20000);
    public Work_ATM work_atm_first = new Work_ATM(2000,3000, person, 100);
    public Work_ATM work_atm_second = new Work_ATM(1500, 500, person, 50);
    public Work_ATM work_atm_third = new Work_ATM(1000, 700, person, 200);

    public Terminal(){}
    public void start_work()
    {
        Thread ATM1 = new Thread(work_atm_first);
        Thread ATM2 = new Thread(work_atm_second);
        Thread ATM3 = new Thread(work_atm_third);
        ATM1.setName("ATM1");
        ATM2.setName("ATM2");
        ATM3.setName("ATM3");

        ATM1.start();
        ATM2.start();
        ATM3.start();
    }
}