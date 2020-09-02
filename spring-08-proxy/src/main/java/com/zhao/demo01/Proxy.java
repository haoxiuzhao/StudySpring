package com.zhao.demo01;

public class Proxy implements Rent{
    private Host host;

    public Proxy() {

    }

    public Proxy(Host host) {
        this.host = host;
    }
    public void rent(){
        host.rent();
    }
    //看房
    public void seeHouse(){
        System.out.println("中介带看房");
    }
    //收中介费
    public void  fare(){
        System.out.println("收中介费");
    }
}
