package com.company;

public class Work_ATM implements Runnable{
    Person person;
    public int sum, subtract;
    int sleep;
    public Work_ATM(int sum, int subtract, Person person, int sleep)
    {
        this.sum = sum;
        this.subtract = subtract;
        this.person = person;
        this.sleep = sleep;
    }

    public void run()
    {
        while (true)
        {

            synchronized (Terminal.monitor)
            {
                try {
                    Terminal.monitor.notify();
                    this.Add();
                    this.Subtract();
                    if(person.getBalance() < 0)
                    {
                        person.setBalance(0);
                        throw new Exception();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + person.toString());

                    Terminal.monitor.wait();
                    Thread.sleep(sleep);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    break;
                }
                catch (Exception e) {
                    System.out.println("Balance is not enough");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public void Add()
    {
        person.setBalance(sum + person.getBalance());
    }

    public void Subtract()
    {
        person.setBalance(person.getBalance() - subtract);
    }
}
